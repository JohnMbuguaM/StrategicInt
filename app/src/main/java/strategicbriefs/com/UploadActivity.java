package strategicbriefs.com;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import javax.xml.transform.Result;

public class UploadActivity extends AppCompatActivity {



    private final int PICK_IMAGE_REQUEST = 1;
    private Button chooseImageBtn, uploadBtn, cancelBtn;
    private EditText nameEditText, descriptionEditText, KeyFriendsEditText, OtherNamesEditText;
    private ImageView chooseImageView;
    private ProgressBar uploadProgressBar;
    private Uri mImageUri;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private StorageTask mUploadTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);


        chooseImageBtn = findViewById(R.id.buttonChooseImage);
        uploadBtn = findViewById(R.id.uploadBtn);
        cancelBtn = findViewById(R.id.cancelBtn);
        nameEditText = findViewById(R.id.nameEditText);
        descriptionEditText = findViewById(R.id.descritionEditText);
        KeyFriendsEditText = findViewById(R.id.keyFriendsEditText);
        OtherNamesEditText = findViewById(R.id.akaEditText);
        chooseImageBtn = findViewById(R.id.buttonChooseImage);
        uploadProgressBar = findViewById(R.id.progressBar);
        chooseImageView = findViewById(R.id.chooseImageView);



        mStorageRef = FirebaseStorage.getInstance().getReference("suspects_Upload");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("suspects_Upload");


        chooseImageBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v) {
                openFileChooser();
            }

        });

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask !=null && mUploadTask.isInProgress()){
                    Toast.makeText(UploadActivity.this, "Your Upload is in progress",
                            Toast.LENGTH_LONG).show();

                }else {
                    UploadFile();

                }
            }
        });
    }


    private void openFileChooser () {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode,data);


        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data !=null
                && data.getData() !=null) {
            mImageUri = data.getData();
            Picasso.get().load(mImageUri).into(chooseImageView);
        }
    }
    private String getFileExtension (Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));

    }

    private void UploadFile () {
        if (mImageUri !=null) {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));

            uploadProgressBar.setVisibility(View.VISIBLE);
            uploadProgressBar.setIndeterminate(true);

            mUploadTask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {


                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            uploadProgressBar.setVisibility(View.VISIBLE);
                            uploadProgressBar.setIndeterminate(false);
                            uploadProgressBar.setProgress(0);

                        }

                    }, 500);


                    Toast.makeText(UploadActivity.this, "Upload Successful ", Toast.LENGTH_LONG).show();

                    Teacher upload = new Teacher(
                            nameEditText.getText().toString().trim(),
                            descriptionEditText.getText().toString(),
                            KeyFriendsEditText.getText().toString(),
                            taskSnapshot.getMetadata().toString(),
                            OtherNamesEditText.getText().toString());






                    String uploadId = mDatabaseRef.push().getKey();
                    mDatabaseRef.child(uploadId).setValue(upload);

                    uploadProgressBar.setVisibility(View.INVISIBLE);
                    openImagesActivity();
                }


            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    uploadProgressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(UploadActivity.this, e.getMessage(),Toast.LENGTH_LONG).show();

                }
            })
             .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>(){
                 @Override
                 public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                     double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                     uploadProgressBar.setProgress((int) progress);

                 }
             });




        }else {
            Toast.makeText(UploadActivity.this, "You have not selected an image", Toast.LENGTH_LONG).show();
        }
    }

    private void openImagesActivity() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }


}

package strategicbriefs.com;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ItemActivity extends AppCompatActivity implements RecyclerAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private ProgressBar mProgressBar;
    private FirebaseStorage mStorage;
    private DatabaseReference mDatabseRef;
    private ValueEventListener mDBListener;

    private List<Teacher> mTeachers;


    private void openDetailActivity (String[] data) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("NAME_KEY", data[0]);
        intent.putExtra("DESCRIPTION_KEY", data[1]);
        intent.putExtra("KeyFRIENDS_KEY", data[2]);
        intent.putExtra("OTHERNAMES_KEY", data[3]);
        intent.putExtra("IMAGE_KEY",  data[4]);

        startActivity(intent);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        mProgressBar=findViewById(R.id.myDataLoaderProgressBar);
        mProgressBar.setVisibility(View.VISIBLE);

        mRecyclerView = findViewById(R.id.mRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));



        mTeachers = new ArrayList<>();
        mAdapter = new RecyclerAdapter(ItemActivity.this, mTeachers);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(ItemActivity.this);

        mStorage = FirebaseStorage.getInstance();
        mDatabseRef = FirebaseDatabase.getInstance().getReference("suspects_uploads");
        mDBListener = mDatabseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mTeachers.clear();

                for (DataSnapshot teacherSnapshot :dataSnapshot.getChildren()) {
                    Teacher upload = teacherSnapshot.getValue(Teacher.class);
                    upload.setKey(teacherSnapshot.getKey());
                    mTeachers.add(upload);
                }
                mAdapter.notifyDataSetChanged();
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ItemActivity.this, databaseError.getMessage(),Toast.LENGTH_LONG).show();
                mProgressBar.setVisibility(View.INVISIBLE);

            }
        });

    }

    public void OnItemClick (int position) {
        Teacher clickedTeacher = mTeachers.get(position);
        String [] teacherData = {clickedTeacher.getName(), clickedTeacher.getDescription(),
                clickedTeacher.getKeyFirends(), clickedTeacher.getOtherNames(),clickedTeacher.getImageURI()};
        openDetailActivity(teacherData);
    }

    @Override
    public void onShowItemClick (int position ){
        Teacher clickedTeacher = mTeachers.get(position);
        String [] teacherData = {clickedTeacher.getName(), clickedTeacher.getDescription(),
                clickedTeacher.getKeyFirends(), clickedTeacher.getOtherNames(),clickedTeacher.getImageURI()};
        openDetailActivity(teacherData);

    }

    @Override
    public void onDeleteItemClick (int position) {
        Teacher selectedItem = mTeachers.get(position);
        final String selectedKey = selectedItem.getKey();

        StorageReference imageRef = mStorage.getReferenceFromUrl(selectedItem.getImageURI());
        imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mDatabseRef.child(selectedKey).removeValue();
                Toast.makeText(ItemActivity.this, "Item Deleted", Toast.LENGTH_LONG).show();
            }
        });

    }

    protected void onDestroy () {
        super.onDestroy();
        mDatabseRef.removeEventListener(mDBListener);
    }



}

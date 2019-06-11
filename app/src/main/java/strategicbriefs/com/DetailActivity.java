package strategicbriefs.com;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailActivity extends AppCompatActivity {

    private TextView nameTextView, desTextView, keyFriendsTextView, dateTextView;
    private ImageView suspectsImageView;


    private void initializeWidgets (){
        nameTextView = findViewById(R.id.suspectNameTextView);
        desTextView = findViewById(R.id.descriptionTextView);
        keyFriendsTextView = findViewById(R.id.keyFrindsTextView);
        dateTextView = findViewById(R.id.dateTextView);
        suspectsImageView = findViewById(R.id.suspectsDetailsImageViewer);

    }

    private String getDateToday (){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
        Date date = new Date();
        String today = dateFormat.format(date);
        return today;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initializeWidgets();

        Intent i = this.getIntent();

        String name = i.getExtras().getString("NAME_KEY");
        String description = i.getExtras().getString("DESCRIPTION_KEY");
        String keyFriends = i.getExtras().getString("KeyFRIENDS_KEY");
        String imageURL = i.getExtras().getString("IMAGE_KEY");

        nameTextView.setText(name);
        desTextView.setText(description);
        keyFriendsTextView.setText(keyFriends);
        dateTextView.setText("DATE: " + getDateToday());
        Picasso.get().load(imageURL).placeholder(R.drawable.image_place_holder)

                .fit()
                .centerCrop()
                .into(suspectsImageView);






    }
}

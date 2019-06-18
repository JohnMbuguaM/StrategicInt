package strategicbriefs.com.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


import strategicbriefs.com.R;

public class DetailsActivity extends AppCompatActivity {

    TextView nameDetailTextView,descriptionDetailTextView,dateDetailTextView,categoryDetailTextView,keyFriendsTextView, linkTextView ;
    ImageView teacherDetailImageView;

    private void initializeWidgets(){
        nameDetailTextView= findViewById(R.id.nameDetailTextView);
        descriptionDetailTextView= findViewById(R.id.descriptionDetailTextView);
        keyFriendsTextView = findViewById(R.id.TextViewKeyFriends);
        linkTextView = findViewById(R.id.TextViewLink);
        dateDetailTextView= findViewById(R.id.dateDetailTextView);
//        categoryDetailTextView= findViewById(R.id.categoryDetailTextView);
        teacherDetailImageView=findViewById(R.id.teacherDetailImageView);
    }
    private String getDateToday(){
        DateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd");
        Date date=new Date();
        String today= dateFormat.format(date);
        return today;
    }
//    private String getRandomCategory(){
//        String[] categories={"ZEN","BUDHIST","YOGA"};
//        Random random=new Random();
//        int index=random.nextInt(categories.length-1);
//        return categories[index];
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initializeWidgets();

        //RECEIVE DATA FROM ITEMSACTIVITY VIA INTENT
        Intent i=this.getIntent();
        String name=i.getExtras().getString("NAME_KEY");
        String description=i.getExtras().getString("DESCRIPTION_KEY");
        String KeyFriends = i.getExtras().getString("KEYFRIENDS_KEY");
        String link = i.getExtras().getString("LINK_KEY");
        String imageURL=i.getExtras().getString("IMAGE_KEY");

        //SET RECEIVED DATA TO TEXTVIEWS AND IMAGEVIEWS
        nameDetailTextView.setText(name);
        descriptionDetailTextView.setText(description);
        keyFriendsTextView.setText(KeyFriends);
        linkTextView.setText(link);

        dateDetailTextView.setText("DATE: "+getDateToday());
//        categoryDetailTextView.setText("CATEGORY: "+getRandomCategory());
        Picasso.get()
                .load(imageURL)
                .placeholder(R.drawable.image_place_holder)
                .fit()
                .centerCrop()
                .into(teacherDetailImageView);

    }

}

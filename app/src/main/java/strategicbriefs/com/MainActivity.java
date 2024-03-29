package strategicbriefs.com;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import strategicbriefs.com.View.DatabaseMain;
import strategicbriefs.com.View.ItemsActivity;

public class MainActivity extends AppCompatActivity {
    private CardView suspectDb;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        suspectDb = findViewById(R.id.suspectDb);


        suspectDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DatabaseMain .class);
                startActivity(intent);
            }
        });
    }
}

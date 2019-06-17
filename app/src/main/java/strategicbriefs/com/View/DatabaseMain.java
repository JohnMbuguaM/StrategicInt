package strategicbriefs.com.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


import strategicbriefs.com.R;

public class DatabaseMain extends AppCompatActivity {
    private Button openTeachersActivityBtn,openUploadActivityBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main2 );

        openTeachersActivityBtn = findViewById (R.id.openTeachersActivityBtn);
        openUploadActivityBtn = findViewById (R.id.openUploadActivityBtn);

        openTeachersActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DatabaseMain.this, ItemsActivity.class);
                startActivity(i);
            }
        });
        openUploadActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DatabaseMain.this, UploadActivity.class);
                startActivity(i);
            }
        });

    }


}

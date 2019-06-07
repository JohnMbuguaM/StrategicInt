package strategicbriefs.com;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class SplashActivity extends AppCompatActivity {




    private ImageView mLogo;
    private TextView title, subTitle;


    private void initializeWidget() {
        mLogo = findViewById(R.id.mLogo);
        title = findViewById(R.id.title);
        subTitle = findViewById(R.id.subTitle);


    }
    private void showSplashAnimation () {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.top_to_bottom);
        mLogo.startAnimation(animation);

        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);
        title.startAnimation(fadeIn);
        subTitle.startAnimation(fadeIn);

    }

    public void goToDashBoard () {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    super.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };
        t.start();

    }

//    protected void attachBaseContext (Context, newBase) {
//        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
//    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        this.initializeWidget();
        this.showSplashAnimation();
        this.goToDashBoard();
    }
}

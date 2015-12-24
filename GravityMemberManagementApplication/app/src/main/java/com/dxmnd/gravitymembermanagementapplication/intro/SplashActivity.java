package com.dxmnd.gravitymembermanagementapplication.intro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.dxmnd.gravitymembermanagementapplication.R;
import com.dxmnd.gravitymembermanagementapplication.activity.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_acitivy);

        Thread mTimer = new Thread() {
            @Override
            public void run() {

                try {
                    sleep(3000);
                    Intent mIntro = new Intent(SplashActivity.this, IntroActivity.class);
                    startActivity(mIntro);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        mTimer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}

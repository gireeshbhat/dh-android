package com.senyum.gireesh.digitalharbor.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.senyum.gireesh.digitalharbor.R;
import com.senyum.gireesh.digitalharbor.helper.SQLiteHandler;
import com.senyum.gireesh.digitalharbor.services.NotificationService;

public class Splash extends Activity {
    private static final int SPLASH_DISPLAY_TIME = 2000;
    private SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        db = new SQLiteHandler(getApplicationContext());

        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (db.isLoggedIn()) {
                    startActivity(new Intent(Splash.this, MainActivity.class));
                } else
                    startActivity(new Intent(Splash.this, Login.class));
            }
        }, SPLASH_DISPLAY_TIME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Intent intent = new Intent(Splash.this, NotificationService.class);
        intent.putExtra("msg", "hello is the msg");
//        startService(intent);
        this.finish();
    }
}

package com.blogbasbas.fortraining.ui;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.blogbasbas.fortraining.R;
import com.blogbasbas.fortraining.helpers.MyFunction;
import com.blogbasbas.fortraining.helpers.SessionManager;

public class SplashscreenActivity extends MyFunction {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        //delay activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //cek session
                new SessionManager(getApplicationContext()).checkLogin();
               //simpleIntent(MainActivity.class);
               finish();
            }
        }, 5000);
    }
}

package com.example.ibaadat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.rbddevs.splashy.Splashy;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Splashy splashy = new Splashy(this).setLogo(R.drawable.al_quran)
                .setTitle("Ibaadat")
                .setBackgroundColor(android.R.color.transparent);

        splashy.setDuration(2500);
        splashy.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
               finish();
            }

        }, 1600);

    }
}
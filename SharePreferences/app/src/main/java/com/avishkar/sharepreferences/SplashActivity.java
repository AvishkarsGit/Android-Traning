package com.avishkar.sharepreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //check user is logged in
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkUserIsLoggedIn();

            }
        },3000);
    }
    private void checkUserIsLoggedIn() {
        SharedPreferences pref = getSharedPreferences("login",MODE_PRIVATE);

        Boolean isLoggedIn  = pref.getBoolean("isLoggedIn",false);
        Intent intent;
        if (isLoggedIn) {
                intent = new Intent(SplashActivity.this,MainActivity.class);
        }
        else {
            intent = new Intent(SplashActivity.this,LoginActivity.class);

        }
        startActivity(intent);
    }
}
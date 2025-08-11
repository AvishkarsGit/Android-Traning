package com.avishkar.loginwithsharedpreferences.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.avishkar.loginwithsharedpreferences.MainActivity;
import com.avishkar.loginwithsharedpreferences.R;

public class LoginActivity extends AppCompatActivity {


    private Button btnLogin;
    private EditText edtUsername, edtPassword;
    private String username= "admin", password="admin@123";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //init components
        initComp();

        //click on button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = ""+edtUsername.getText();
                String pass = ""+edtPassword.getText();

                if (user.equals(username) && pass.equals(password)) {
                    // login success
                    login();
                }
                else {
                    Toast.makeText(LoginActivity.this, "Login failed, invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void initComp(){
        btnLogin = findViewById(R.id.btnLogin);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
    }

    private void login() {
        //shared preferences
        SharedPreferences pref = getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isLoggedIn",true);
        editor.putString("username","Sunil");
        editor.apply();

        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
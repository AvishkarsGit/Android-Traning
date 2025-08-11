package com.avishkar.e_bookstore.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.avishkar.e_bookstore.HomeActivity;
import com.avishkar.e_bookstore.R;
import com.avishkar.e_bookstore.constants.Constant;
import com.avishkar.e_bookstore.helper.DBHelper;

public class LoginActivity extends AppCompatActivity {


    private EditText edtUsername,edtPassword;
    private Button btnLogin;
    private TextView tvRegister;

    //helper
    private DBHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.toolbarRL), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, v.getPaddingBottom());
            return insets;
        });


        //init components
        initComponents();


        //click on register text
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(i);
            }
        });

        //click on login button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();

                if (validateData(username,password)) {
                    // data validate
                   boolean login =  helper.isLoggedIn(username,password);
                   if (login) {
                       //login successful
                        loginUser(login);
                       Toast.makeText(LoginActivity.this, "Login successful...", Toast.LENGTH_SHORT).show();
                   }
                   else
                   {
                       //login failed
                       Toast.makeText(LoginActivity.this, "Invalid username and password", Toast.LENGTH_SHORT).show();
                   }

                }


            }
        });

    }

    private void loginUser(boolean login) {
        SharedPreferences pref = getSharedPreferences(Constant.shared_var_name,MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(Constant.login_flag,login);
        editor.apply();

        Intent intent =  new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();

    }

    private Boolean validateData(String username, String password) {
            if (username.isEmpty()) {
                Toast.makeText(this, "username is required", Toast.LENGTH_SHORT).show();
                return false;
            }
            else if (password.isEmpty()) {
                Toast.makeText(this, "password is required", Toast.LENGTH_SHORT).show();
                return false;
            }
            else {
                return true;
            }
    }

    private void initComponents() {

        //init helper class
        helper = new DBHelper(LoginActivity.this);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);
    }

    //check if user is logged in
    private Boolean checkUserIsLoggedIn() {
        SharedPreferences pref = getSharedPreferences(Constant.shared_var_name,MODE_PRIVATE);
        boolean isLoggedIn  = pref.getBoolean(Constant.login_flag,false);
        return isLoggedIn;
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (checkUserIsLoggedIn()) { //boolean - true or false
            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(i);
            this.finish();
        }
    }
}
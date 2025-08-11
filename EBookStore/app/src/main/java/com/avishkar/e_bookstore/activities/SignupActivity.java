package com.avishkar.e_bookstore.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.avishkar.e_bookstore.HomeActivity;
import com.avishkar.e_bookstore.R;
import com.avishkar.e_bookstore.constants.Constant;
import com.avishkar.e_bookstore.helper.DBHelper;

public class SignupActivity extends AppCompatActivity {

    private EditText edtName,edtUsername,edtPassword,edtConfirmPassword;
    private Button btnSignup;
//    private TextView tvLogin;
    private ImageButton backIb;

    //helper class
    private DBHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //init component
        initComponents();

        //click on back button
        backIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //click on signup button
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                String confirmPassword = edtConfirmPassword.getText().toString();

                if (validateData(name,username,password,confirmPassword)) {
                    // simply register
                   boolean isRegister =  helper.registerUser(name,username,password);
                   if (isRegister) {
                       Toast.makeText(SignupActivity.this, "Account created successfully...", Toast.LENGTH_SHORT).show();
                       Intent i = new Intent(SignupActivity.this, HomeActivity.class);
                       startActivity(i);
                       SharedPreferences pref = getSharedPreferences(Constant.shared_var_name,MODE_PRIVATE);
                       SharedPreferences.Editor editor = pref.edit();
                       editor.putBoolean(Constant.login_flag,true);
                       editor.apply();
                       finish();
                   }
                   else {
                       Toast.makeText(SignupActivity.this, "Failed to create account", Toast.LENGTH_SHORT).show();
                   }
                }
            }
        });
    }

    private Boolean validateData(String name, String username, String password, String confirmPassword) {
        if (name.isEmpty()) {
            Toast.makeText(this, "Name is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (username.isEmpty()) {
            Toast.makeText(this, "username is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (password.isEmpty()) {
            Toast.makeText(this, "password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (password.length() < 8) {
            Toast.makeText(this, "password must be 8 characters long", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (!confirmPassword.equals(password)) {
            Toast.makeText(this, "Password doesn't match with confirm password", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }
    private void initComponents() {

        //init helper class
        helper = new DBHelper(SignupActivity.this);

        edtName =  findViewById(R.id.edtName);
        edtUsername = findViewById(R.id.edtUsernameR);
        edtPassword = findViewById(R.id.edtPasswordR);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnSignup = findViewById(R.id.btnSignup);
//        tvLogin = findViewById(R.id.tvLogin);
        backIb = findViewById(R.id.backIb);
    }
}
package com.example.colistapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.colistapp.MainActivity;
import com.example.colistapp.R;
import com.example.colistapp.constants.Constant;
import com.example.colistapp.databinding.ActivityAuthBinding;
import com.example.colistapp.helpers.DBHelper;

import java.util.regex.Pattern;

public class AuthActivity extends AppCompatActivity {

    //binding
    private ActivityAuthBinding binding;

    // defining our own password pattern
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{4,}" +                // at least 4 characters
                    "$");

    //Database helper
    private DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //init comp
        initComponents();


        //handle click on login tabs
        binding.tabLogin.setOnClickListener(v -> {
            binding.tabLogin.setTextColor(getResources().getColor(R.color.white));
            binding.tabLogin.setBackgroundResource(R.drawable.shape_tab_selected);

            binding.tabRegister.setTextColor(getResources().getColor(R.color.primary));
            binding.tabRegister.setBackgroundColor(getResources().getColor(android.R.color.transparent));

            binding.loginLL.setVisibility(View.VISIBLE);
            binding.registerLL.setVisibility(View.GONE);
        });

        //handle click on register tabs
        binding.tabRegister.setOnClickListener(v -> {

            binding.tabRegister.setTextColor(getResources().getColor(R.color.white));
            binding.tabRegister.setBackgroundResource(R.drawable.shape_tab_selected);

            binding.tabLogin.setTextColor(getResources().getColor(R.color.primary));
            binding.tabLogin.setBackgroundColor(getResources().getColor(android.R.color.transparent));

            binding.loginLL.setVisibility(View.GONE);
            binding.registerLL.setVisibility(View.VISIBLE);
        });


        //handle click, on sign-in button
        binding.btnSignIn.setOnClickListener(v -> {
            //validate data
            validateData();
        });

        //handle click, on sign-up button
        binding.btnSignUp.setOnClickListener(v -> {
            validateDataSignUp();
        });

    }

    private void initComponents() {
        helper = new DBHelper(AuthActivity.this);
    }

    private void validateData(){
        String email = binding.emailEt.getText().toString().trim();
        String pass = binding.passEt.getText().toString().trim();
        if (email.isEmpty()) {
            binding.emailEt.setError("email is required");
            binding.emailEt.requestFocus();
            return;
        } else if (pass.isEmpty()) {

            binding.emailEt.setError(null);
            binding.emailEt.clearFocus();

            binding.passEt.setError("password is required");
            binding.passEt.requestFocus();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.passEt.setError(null);
            binding.passEt.clearFocus();

            binding.emailEt.setError("email is invalid");
            binding.emailEt.requestFocus();
            return;
        }
        else
        {
            loginUser(email,pass);
        }
    }


    private void validateDataSignUp(){
        String name = binding.nameEtSignUp.getText().toString().trim();
        String email = binding.emailEtSignUp.getText().toString().trim();
        String pass = binding.passEtSignUp.getText().toString().trim();
        if (name.isEmpty()){
            binding.nameEtSignUp.setError("name is required");
            binding.nameEtSignUp.requestFocus();
            return;
        }
        else if (email.isEmpty()) {
            binding.nameEtSignUp.setError(null);
            binding.nameEtSignUp.clearFocus();

            binding.emailEtSignUp.setError("email is required");
            binding.emailEtSignUp.requestFocus();
            return;
        } else if (pass.isEmpty()) {

            binding.emailEtSignUp.setError(null);
            binding.emailEtSignUp.clearFocus();

            binding.passEtSignUp.setError("password is required");
            binding.passEtSignUp.requestFocus();

        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.passEtSignUp.setError(null);
            binding.passEtSignUp.clearFocus();

            binding.emailEtSignUp.setError("email is invalid");
            binding.emailEtSignUp.requestFocus();
            return;
        } else if (!PASSWORD_PATTERN.matcher(pass).matches()) {
            binding.emailEtSignUp.setError(null);
            binding.emailEtSignUp.clearFocus();

            binding.passEtSignUp.setError("password is too weak");
            binding.passEtSignUp.requestFocus();
            return;
        }
        else
        {
            createUser(name,email,pass);
        }
    }

    private void createUser(String name, String email, String password) {

        boolean isRegistered = helper.registerUser(name,email,password);
        if (isRegistered) {
            Toast.makeText(this, "Account created...", Toast.LENGTH_SHORT).show();

            setLogin();
            //goto main activity
            Intent i = new Intent(AuthActivity.this,MainActivity.class);
            startActivity(i);
            finish();
        }  else {
            Toast.makeText(this, "Failed to create account", Toast.LENGTH_SHORT).show();
        }


    }

    private void loginUser(String email, String password) {
        boolean isLoggedIn = helper.login(email,password);
        if (isLoggedIn) {
            //set login
            setLogin();
            Toast.makeText(this, "Logged In Successful...", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(AuthActivity.this,MainActivity.class);
            startActivity(i);
            finish();
        }
        else {
            Toast.makeText(this, "Invalid email and password", Toast.LENGTH_SHORT).show();
        }
    }


    private void setLogin() {
        SharedPreferences preferences = getSharedPreferences(Constant.shared_var_name,MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(Constant.login_flag,true);
        editor.apply();
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (checkUserIsLoggedIn()) { //boolean - true or false
            Intent i = new Intent(AuthActivity.this, MainActivity.class);
            startActivity(i);
            this.finish();
        }
    }

    private Boolean checkUserIsLoggedIn() {
        SharedPreferences pref = getSharedPreferences(Constant.shared_var_name,MODE_PRIVATE);
        return pref.getBoolean(Constant.login_flag,false);
    }
}
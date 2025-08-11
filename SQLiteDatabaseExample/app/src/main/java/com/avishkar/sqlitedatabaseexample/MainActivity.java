package com.avishkar.sqlitedatabaseexample;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.avishkar.sqlitedatabaseexample.helper.DatabaseHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private DatabaseHelper helper;
    private EditText edtName,edtEmail,edtPhone;
    private Button btnSave,btnRead,btnChoose;
    private ImageView iv;
    private Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        initDatabase();
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    pickImageFromGallery();
                }
                else {
                    requestStoragePermission.launch(Manifest.permission.READ_MEDIA_IMAGES);
                }
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String email = edtEmail.getText().toString();
                String phone = edtPhone.getText().toString();

                if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Empty fields are not allowed...", Toast.LENGTH_SHORT).show();
                }
                else if (  imageUri == null) {
                    Toast.makeText(MainActivity.this, "choose image", Toast.LENGTH_SHORT).show();
                }
                else {
                    String url = imageUri.toString();
                    helper.addUsers(name,email,phone,url);
                    Toast.makeText(MainActivity.this, "Data saved successfully...   ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,DataActivity.class);
                startActivity(i);
            }
        });



    }



    private void pickImageFromGallery() {
        Intent i = new Intent(Intent.ACTION_PICK);
        i.setType("image/*");
        galleryLauncher.launch(i);
    }


    private ActivityResultLauncher<String> requestStoragePermission = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(),
            new ActivityResultCallback<Boolean>() {
                @Override
                public void onActivityResult(Boolean isGranted) {
                    if (isGranted) {
                        pickImageFromGallery();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "permission denied", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    private ActivityResultLauncher<Intent> galleryLauncher = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK && result.getData()!=null){
                Intent data = result.getData();
                imageUri = data.getData();
                iv.setImageURI(imageUri);
                Toast.makeText(MainActivity.this, ""+imageUri, Toast.LENGTH_SHORT).show();

            }
            else{
                Toast.makeText(MainActivity.this, "not picked", Toast.LENGTH_SHORT).show();
            }
        }
    });

    private void initDatabase() {
        helper = new DatabaseHelper(MainActivity.this);
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhone = findViewById(R.id.edtPhone);
        btnSave = findViewById(R.id.btnSave);
        btnRead = findViewById(R.id.btnRead);
        btnChoose = findViewById(R.id.btnChoose);
        iv = findViewById(R.id.iv);
    }


}
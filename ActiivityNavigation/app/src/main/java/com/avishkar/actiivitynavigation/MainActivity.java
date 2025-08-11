package com.avishkar.actiivitynavigation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    private Button btn,btnOpenWebsite;

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


        btn = findViewById(R.id.btnGoToNext);
        btnOpenWebsite = findViewById(R.id.btnGotoWeb);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //explicit intent
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("name","Alaxzander");
                intent.putExtra("rollno",101);
                startActivity(intent);
            }
        });

        btnOpenWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //implicit intent
                Intent i = new Intent(Intent.ACTION_VIEW);
                String url = "https://www.udemy.com/";

//                string -> URI (uniform resourse indicator)
                Uri uri = Uri.parse(url);
                i.setData(uri);

                startActivity(i);
            }
        });

    }
}
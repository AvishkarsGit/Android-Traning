package com.avishkar.widgetsdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    EditText edtName;
    Button btnDisplay;
    TextView tvName;

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

        edtName = findViewById(R.id.edtName);
        btnDisplay = findViewById(R.id.btnDisplay);
        tvName = findViewById(R.id.tvName);


        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = ""+ edtName.getText();
                if (text.equals("")) {
//                    Toast.makeText(MainActivity.this,"Enter your name",Toast.LENGTH_LONG);
                    Toast.makeText(MainActivity.this, "Enter your name", Toast.LENGTH_SHORT).show();
                }
                else {
                    tvName.setText(text);
                }

            }
        });

    }
}
package com.avishkar.simplecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    private EditText edtFno, edtSno;
    private Button btnAdd, btnSubstract, btnMultiply, btnDivision;
    private TextView tvResult;

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


        //initialize components
        initComp();

        //perform operations
        makeCalculation();


    }

    private void makeCalculation() {


            //addition
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int num1 = Integer.parseInt(String.valueOf(edtFno.getText()));
                    int num2 = Integer.parseInt(String.valueOf(edtSno.getText()));
                    int result = num1 + num2;
                    tvResult.setText("Result : "+result);
                }
            });

            //substraction
            btnSubstract.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int num1 = Integer.parseInt(String.valueOf(edtFno.getText()));
                    int num2 = Integer.parseInt(String.valueOf(edtSno.getText()));
                    int result = num1 - num2;
                    tvResult.setText("Result : "+result);
                }
            });

            //division
            btnDivision.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int num1 = Integer.parseInt(String.valueOf(edtFno.getText()));
                    int num2 = Integer.parseInt(String.valueOf(edtSno.getText()));
                    int result = num1 / num2;
                    tvResult.setText("Result : "+result);
                }
            });

            //multiply
            btnMultiply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int num1 = Integer.parseInt(String.valueOf(edtFno.getText()));
                    int num2 = Integer.parseInt(String.valueOf(edtSno.getText()));
                    int result = num1 * num2;
                    tvResult.setText("Result : "+result);
                }
            });
        }

    private void initComp() {
        edtFno = findViewById(R.id.edtFno);
        edtSno = findViewById(R.id.edtSno);
        btnAdd = findViewById(R.id.btnAdd);
        btnSubstract = findViewById(R.id.btnSubstract);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnDivision = findViewById(R.id.btnDivision);
        tvResult = findViewById(R.id.tvResult);
    }

    public void onClick(View view) {

    }
}
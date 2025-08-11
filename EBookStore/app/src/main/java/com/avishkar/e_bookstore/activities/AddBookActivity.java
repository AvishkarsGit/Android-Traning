package com.avishkar.e_bookstore.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.avishkar.e_bookstore.R;
import com.avishkar.e_bookstore.helper.DBHelper;

public class AddBookActivity extends AppCompatActivity {


    private EditText edtBookName,edtAuthorName,edtBookPrice,edtAuthorContact;
    private Button btnSave;
    private ImageButton backIb;
    private DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_book);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //init components
        initComponents();

        //click on btn save
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String book_name = edtBookName.getText().toString();
                String author_name = edtAuthorName.getText().toString();
                String contact = edtAuthorContact.getText().toString();
                String price = edtBookPrice.getText().toString();

                boolean result = helper.addNewBook(book_name,author_name,price,contact);
                if (result) {
                    Toast.makeText(AddBookActivity.this, "Book Added...", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(AddBookActivity.this, "Failed to add book", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //click on back button
        backIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initComponents() {

        //init helper
        helper = new DBHelper(AddBookActivity.this);

        edtBookName = findViewById(R.id.edtBookName);
        edtAuthorName = findViewById(R.id.edtAuthorName);
        edtBookPrice = findViewById(R.id.edtBookPrice);
        edtAuthorContact = findViewById(R.id.edtAuthorContact);
        btnSave = findViewById(R.id.btnSave);
        backIb = findViewById(R.id.backIb);
    }
}
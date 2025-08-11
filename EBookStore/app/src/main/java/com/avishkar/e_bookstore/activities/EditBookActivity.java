package com.avishkar.e_bookstore.activities;

import android.content.Intent;
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

public class EditBookActivity extends AppCompatActivity {


    private EditText edtBookName,edtAuthorName,edtBookPrice,edtAuthorContact;
    private Button btnEdit;
    private ImageButton backIb,deleteIb;
    private DBHelper helper;
    private int id;
    private String book_name,author_name,price,contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_book);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //init components
        initComponents();

        //click on button edit
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                book_name = edtBookName.getText().toString();
                author_name = edtAuthorName.getText().toString();
                price = edtBookPrice.getText().toString();
                contact = edtAuthorContact.getText().toString();
                boolean isUpdated = helper.editBook(id,book_name,author_name,price,contact);
                if (isUpdated) {
                    Toast.makeText(EditBookActivity.this, "Book is updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(EditBookActivity.this, "Failed to update book!..", Toast.LENGTH_SHORT).show();
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


        //click on delete button
        deleteIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean isDeleted =  helper.deleteBook(id);
               if (isDeleted) {
                   Toast.makeText(EditBookActivity.this, "Book deleted successfully...", Toast.LENGTH_SHORT).show();
                   finish();
               }
               else
               {
                   Toast.makeText(EditBookActivity.this, "Failed to delete book...!!", Toast.LENGTH_SHORT).show();
               }
            }
        });
    }


    private void initComponents() {

        //init helper
        helper = new DBHelper(EditBookActivity.this);

        edtBookName = findViewById(R.id.edtBookNameEdit);
        edtAuthorName = findViewById(R.id.edtAuthorNameEdit);
        edtBookPrice = findViewById(R.id.edtBookPriceEdit);
        edtAuthorContact = findViewById(R.id.edtAuthorContactEdit);
        btnEdit = findViewById(R.id.btnEdit);
        backIb = findViewById(R.id.backIb);
        deleteIb = findViewById(R.id.deleteIb);

        //get values from intent
        Intent fromIntent = getIntent();
        id = fromIntent.getIntExtra("id",-1);
        book_name = fromIntent.getStringExtra("book_name");
        author_name = fromIntent.getStringExtra("book_author");
        price = fromIntent.getStringExtra("price");
        contact = fromIntent.getStringExtra("contact");

        //set to the views
        edtBookName.setText(book_name);
        edtAuthorName.setText(author_name);
        edtBookPrice.setText(price);
        edtAuthorContact.setText(contact);

    }
}
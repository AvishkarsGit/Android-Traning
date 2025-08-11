package com.avishkar.e_bookstore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.avishkar.e_bookstore.activities.AddBookActivity;
import com.avishkar.e_bookstore.activities.LoginActivity;
import com.avishkar.e_bookstore.adapter.BooksAdapter;
import com.avishkar.e_bookstore.constants.Constant;
import com.avishkar.e_bookstore.helper.DBHelper;
import com.avishkar.e_bookstore.model.BooksModel;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {


    //buttons
    private ImageButton logoutIb;
    private Button btnAddNewBook;
    private RecyclerView recyclerBook;
    private ArrayList<BooksModel> booksList;
    private BooksAdapter adapter;
    private DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //init components
        initComponent();

        //click on logout button
        logoutIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
                Toast.makeText(HomeActivity.this, "Logout successful...", Toast.LENGTH_SHORT).show();
            }
        });

        btnAddNewBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, AddBookActivity.class);
                startActivity(i);
            }
        });

        //load data from database
        loadData();

    }

    private void loadData() {
        booksList = helper.getAllBooks();
        adapter = new BooksAdapter(HomeActivity.this,booksList);
        recyclerBook.setAdapter(adapter);

    }

    private void logout() {
        SharedPreferences preferences = getSharedPreferences(Constant.shared_var_name,MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(Constant.login_flag,false);
        editor.apply();

        Intent i = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(i);
        finish();

    }

    private void initComponent() {
        helper = new DBHelper(HomeActivity.this);

        logoutIb = findViewById(R.id.logoutIb);
        btnAddNewBook = findViewById(R.id.btnAddNewBook);

        //arraylist
        booksList = new ArrayList<>();


        // recycler view initialization
        recyclerBook = findViewById(R.id.recyclerBook);
        recyclerBook.setLayoutManager(new LinearLayoutManager(HomeActivity.this));

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.loadData();
    }
}
package com.avishkar.sqlitedatabaseexample;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.avishkar.sqlitedatabaseexample.adapter.UsersAdapter;
import com.avishkar.sqlitedatabaseexample.helper.DatabaseHelper;
import com.avishkar.sqlitedatabaseexample.model.UsersModel;

import java.util.ArrayList;

public class DataActivity extends AppCompatActivity {

    private RecyclerView rvUser;
    private ArrayList<UsersModel> usersList;
    private UsersAdapter adapter;
    private DatabaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_data);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //init
        helper= new DatabaseHelper(DataActivity.this);
        rvUser = findViewById(R.id.rvUser);
        rvUser.setLayoutManager(new LinearLayoutManager(this));
        usersList = new ArrayList<>();


        fetchFromTheDatabase();
    }

    private void fetchFromTheDatabase() {
        usersList = helper.fetchUsers();
        adapter = new UsersAdapter(DataActivity.this,usersList);
        rvUser.setAdapter(adapter);
    }
}
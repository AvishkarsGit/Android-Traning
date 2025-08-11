package com.avishkar.recyclerviewexample;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.avishkar.recyclerviewexample.adapter.CustomAdapter;
import com.avishkar.recyclerviewexample.models.UserModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<UserModel> usersList;
    private CustomAdapter customAdapter;

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


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //init arrayList
        usersList = new ArrayList<>();

        //create dummy data;
//        UserModel model1 = new UserModel("Sunil",101);
//        UserModel model2 = new UserModel("Payal",102);
//        UserModel model3 = new UserModel("Sumit",103);
//        UserModel model4 = new UserModel("John",104);
//        UserModel model5 = new UserModel("Alex",105);


    // add data to array list
        usersList.add(new UserModel("Sunil",101));
        usersList.add( new UserModel("Payal",102));
        usersList.add(new UserModel("Sumit",103));
        usersList.add(new UserModel("John",104));
        usersList.add(new UserModel("Alex",105));
usersList.add(new UserModel("Sunil",101));
        usersList.add( new UserModel("Payal",102));
        usersList.add(new UserModel("Sumit",103));
        usersList.add(new UserModel("John",104));
        usersList.add(new UserModel("Alex",105));
usersList.add(new UserModel("Sunil",101));
        usersList.add( new UserModel("Payal",102));
        usersList.add(new UserModel("Sumit",103));
        usersList.add(new UserModel("John",104));
        usersList.add(new UserModel("Alex",105));
usersList.add(new UserModel("Sunil",101));
        usersList.add( new UserModel("Payal",102));
        usersList.add(new UserModel("Sumit",103));
        usersList.add(new UserModel("John",104));
        usersList.add(new UserModel("Alex",105));
usersList.add(new UserModel("Sunil",101));
        usersList.add( new UserModel("Payal",102));
        usersList.add(new UserModel("Sumit",103));
        usersList.add(new UserModel("John",104));
        usersList.add(new UserModel("Alex",105));
usersList.add(new UserModel("Sunil",101));
        usersList.add( new UserModel("Payal",102));
        usersList.add(new UserModel("Sumit",103));
        usersList.add(new UserModel("John",104));
        usersList.add(new UserModel("Alex",105));
usersList.add(new UserModel("Sunil",101));
        usersList.add( new UserModel("Payal",102));
        usersList.add(new UserModel("Sumit",103));
        usersList.add(new UserModel("John",104));
        usersList.add(new UserModel("Alex",105));
usersList.add(new UserModel("Sunil",101));
        usersList.add( new UserModel("Payal",102));
        usersList.add(new UserModel("Sumit",103));
        usersList.add(new UserModel("John",104));
        usersList.add(new UserModel("Alex",105));
usersList.add(new UserModel("Sunil",101));
        usersList.add( new UserModel("Payal",102));
        usersList.add(new UserModel("Sumit",103));
        usersList.add(new UserModel("John",104));
        usersList.add(new UserModel("Alex",105));
usersList.add(new UserModel("Sunil",101));
        usersList.add( new UserModel("Payal",102));
        usersList.add(new UserModel("Sumit",103));
        usersList.add(new UserModel("John",104));
        usersList.add(new UserModel("Alex",105));
usersList.add(new UserModel("Sunil",101));
        usersList.add( new UserModel("Payal",102));
        usersList.add(new UserModel("Sumit",103));
        usersList.add(new UserModel("John",104));
        usersList.add(new UserModel("Alex",105));
usersList.add(new UserModel("Sunil",101));
        usersList.add( new UserModel("Payal",102));
        usersList.add(new UserModel("Sumit",103));
        usersList.add(new UserModel("John",104));
        usersList.add(new UserModel("Alex",105));
usersList.add(new UserModel("Sunil",101));
        usersList.add( new UserModel("Payal",102));
        usersList.add(new UserModel("Sumit",103));
        usersList.add(new UserModel("John",104));
        usersList.add(new UserModel("Alex",105));
usersList.add(new UserModel("Sunil",101));
        usersList.add( new UserModel("Payal",102));
        usersList.add(new UserModel("Sumit",103));
        usersList.add(new UserModel("John",104));
        usersList.add(new UserModel("Alex",105));
usersList.add(new UserModel("Sunil",101));
        usersList.add( new UserModel("Payal",102));
        usersList.add(new UserModel("Sumit",103));
        usersList.add(new UserModel("John",104));
        usersList.add(new UserModel("Alex",105));
usersList.add(new UserModel("Sunil",101));
        usersList.add( new UserModel("Payal",102));
        usersList.add(new UserModel("Sumit",103));
        usersList.add(new UserModel("John",104));
        usersList.add(new UserModel("Alex",105));
usersList.add(new UserModel("Sunil",101));
        usersList.add( new UserModel("Payal",102));
        usersList.add(new UserModel("Sumit",103));
        usersList.add(new UserModel("John",104));
        usersList.add(new UserModel("Alex",105));
usersList.add(new UserModel("Sunil",101));
        usersList.add( new UserModel("Payal",102));
        usersList.add(new UserModel("Sumit",103));
        usersList.add(new UserModel("John",104));
        usersList.add(new UserModel("Alex",105));
usersList.add(new UserModel("Sunil",101));
        usersList.add( new UserModel("Payal",102));
        usersList.add(new UserModel("Sumit",103));
        usersList.add(new UserModel("John",104));
        usersList.add(new UserModel("Alex",105));

        customAdapter = new CustomAdapter(MainActivity.this,usersList);

        recyclerView.setAdapter(customAdapter);


    }
}
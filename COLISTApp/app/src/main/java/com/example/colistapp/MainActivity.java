package com.example.colistapp;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.colistapp.activities.AddCourseActivity;
import com.example.colistapp.activities.AuthActivity;
import com.example.colistapp.adapters.CourseAdapter;
import com.example.colistapp.constants.Constant;
import com.example.colistapp.databinding.ActivityMainBinding;
import com.example.colistapp.helpers.DBHelper;
import com.example.colistapp.models.CourseModel;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {



    //helper
     private DBHelper helper;


     //binding
    private ActivityMainBinding binding;

    //arraylist
    private ArrayList<CourseModel> courseList;

    //adapter
    private CourseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, v.getPaddingBottom());
            return insets;
        });


       //init component
        initComponent();


        // handle click on logout button
        binding.logoutIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });

        //handle click on add course activity
        binding.btnAddNewCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddCourseActivity.class);
                startActivity(i);
            }
        });

        //retrieve all courses
        getAllCourses();


    }

    private void logoutUser() {

        SharedPreferences preferences = getSharedPreferences(Constant.shared_var_name, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(Constant.login_flag,false);
        editor.apply();

        Toast.makeText(this, "Logout successfully....", Toast.LENGTH_SHORT).show();
        //goto auth activity
        Intent i = new Intent(MainActivity.this, AuthActivity.class);
        startActivity(i);
        finish();
    }

    private void getAllCourses() {

        courseList = helper.getAllCourses();
        for (int i = 0;i<courseList.size();i++) {
            Log.d("COURSE", "title: "+courseList.get(i).getCourseName());
        }
        adapter = new CourseAdapter(MainActivity.this,courseList);
        binding.courseListRV.setAdapter(adapter);

    }

    private void initComponent() {
        helper = new DBHelper(MainActivity.this);
        courseList = new ArrayList<>();
        binding.courseListRV.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onStart() {
        super.onStart();

        getAllCourses();
    }
}
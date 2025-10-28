package com.example.colistapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.colistapp.R;
import com.example.colistapp.databinding.ActivityAddCourseBinding;
import com.example.colistapp.helpers.DBHelper;

public class AddCourseActivity extends AppCompatActivity {


    //binding
    private ActivityAddCourseBinding binding;

    //helper
    private DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityAddCourseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //init component
        initComp();

        //handle click on save
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });
    }

    private void validateData() {
        String courseName = binding.courseNameEt.getText().toString().trim();
        String courseDescription = binding.courseDescriptionEt.getText().toString().trim();
        String courseAuthor = binding.courseInstructorEt.getText().toString().trim();
        String courseAuthorContact = binding.courseInstructorContactEt.getText().toString().trim();
        String coursePrice = binding.coursePriceEt.getText().toString().trim();

        if (courseName.isEmpty()) {
            binding.courseNameEt.setError("Course name is required");
            binding.courseNameEt.requestFocus();
            return;

        }
        else if (courseDescription.isEmpty()) {
            binding.courseNameEt.setError(null);
            binding.courseNameEt.clearFocus();

            binding.courseDescriptionEt.setError("Course description is required");
            binding.courseDescriptionEt.requestFocus();
            return;
        }
        else if (courseAuthor.isEmpty()) {
            binding.courseDescriptionEt.setError(null);
            binding.courseDescriptionEt.clearFocus();

            binding.courseInstructorEt.setError("Course author name is required");
            binding.courseInstructorEt.requestFocus();
            return;
        }
        else if (courseAuthorContact.isEmpty()) {
            binding.courseInstructorEt.setError(null);
            binding.courseInstructorEt.clearFocus();

            binding.courseInstructorContactEt.setError("Course author contact is required");
            binding.courseInstructorContactEt.requestFocus();
            return;
        }
        else if (coursePrice.isEmpty()) {
            binding.courseInstructorContactEt.setError(null);
            binding.courseInstructorContactEt.clearFocus();

            binding.coursePriceEt.setError("Course price is required");
            binding.coursePriceEt.requestFocus();
            return;
        }
        else {
            //validate successfully
            //save to db
            boolean added = helper.addNewCourse(courseName,courseDescription,courseAuthor,coursePrice,courseAuthorContact);
            if (added) {
                Toast.makeText(this, "Course Added Successfully..", Toast.LENGTH_SHORT).show();
                finish();
            }
            else {
                Toast.makeText(this, "Failed to added course!!", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void initComp() {
        helper = new DBHelper(AddCourseActivity.this);
    }


}
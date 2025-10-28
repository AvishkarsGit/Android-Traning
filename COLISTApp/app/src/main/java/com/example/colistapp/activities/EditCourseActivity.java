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
import com.example.colistapp.databinding.ActivityEditCourseBinding;
import com.example.colistapp.helpers.DBHelper;

public class EditCourseActivity extends AppCompatActivity {


    //binding
    private ActivityEditCourseBinding binding;

    //helper
    private DBHelper helper;

    //variables
    private int id;
    private String courseName, courseDescription, coursePrice, courseAuthor,courseAuthorContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityEditCourseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //init component
        initComp();


        //handle click on edit button
        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });


        //handle click on delete button
        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isDeleted = helper.deleteCourse(id);
                if(isDeleted) {
                    Toast.makeText(EditCourseActivity.this, "Course deleted successfully..", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(EditCourseActivity.this, "Failed to delete course", Toast.LENGTH_SHORT).show();
                }
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
            boolean edited = helper.editCourse(id,courseName,courseDescription,courseAuthor,coursePrice,courseAuthorContact);
            if (edited) {
                Toast.makeText(this, "Course updated Successfully..", Toast.LENGTH_SHORT).show();
                finish();
            }
            else {
                Toast.makeText(this, "Failed to added course!!", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void initComp() {
        helper = new DBHelper(EditCourseActivity.this);

        //get all values from intent
        getValues();
    }

    private void getValues() {
        id = getIntent().getIntExtra("course_id",0);
        courseName = getIntent().getStringExtra("course_name");
        courseDescription = getIntent().getStringExtra("course_description");
        courseAuthor = getIntent().getStringExtra("course_author");
        coursePrice = getIntent().getStringExtra("price");
        courseAuthorContact = getIntent().getStringExtra("author_contact");

        //bind values to edittext
        binding.courseNameEt.setText(courseName);
        binding.courseDescriptionEt.setText(courseDescription);
        binding.courseInstructorEt.setText(courseAuthor);
        binding.courseInstructorContactEt.setText(courseAuthorContact);
        binding.coursePriceEt.setText(coursePrice);
    }
}
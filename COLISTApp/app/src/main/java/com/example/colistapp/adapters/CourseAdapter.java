package com.example.colistapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.colistapp.activities.EditCourseActivity;
import com.example.colistapp.holders.CourseHolder;
import com.example.colistapp.models.CourseModel;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseHolder> {


    Context context;
    ArrayList<CourseModel> courseList;

    public CourseAdapter(Context context, ArrayList<CourseModel> courseList) {
        this.context = context;
        this.courseList = courseList;
    }

    @NonNull
    @Override
    public CourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(com.example.colistapp.R.layout.course_row_layout,parent,false);
//        CourseHolder holder = new CourseHolder(view);
        return new CourseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseHolder holder, int index) {
        final CourseModel model = courseList.get(index);

        holder.text_course_name.setText(model.getCourseName());
        holder.text_course_description.setText(model.getCourseDescription());
        holder.text_instructor_name.setText(model.getCourseAuthorName());
        holder.text_instructor_contact.setText(model.getCourseAuthorContact());
        holder.text_course_price.setText(model.getCoursePrice()+".00");

       // click on item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

         //navigate to edit activity
                Intent i = new Intent(context, EditCourseActivity.class);
                i.putExtra("course_id",model.getId());
                i.putExtra("course_name",model.getCourseName());
                i.putExtra("course_description",model.getCourseDescription());
                i.putExtra("course_author",model.getCourseAuthorName());
                i.putExtra("price",model.getCoursePrice());
                i.putExtra("author_contact",model.getCourseAuthorContact());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }
}

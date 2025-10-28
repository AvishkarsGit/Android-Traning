package com.example.colistapp.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.colistapp.R;

public class CourseHolder extends RecyclerView.ViewHolder {


    public TextView text_course_name, text_course_price,text_instructor_name, text_course_description, text_instructor_contact;

    public CourseHolder(@NonNull View itemView) {
        super(itemView);

        text_course_name = itemView.findViewById(R.id.text_course_name);
        text_course_price = itemView.findViewById(R.id.text_course_price);
        text_instructor_name = itemView.findViewById(R.id.text_instructor_name);
        text_course_description = itemView.findViewById(R.id.text_course_description);
        text_instructor_contact = itemView.findViewById(R.id.text_instructor_contact);
    }
}

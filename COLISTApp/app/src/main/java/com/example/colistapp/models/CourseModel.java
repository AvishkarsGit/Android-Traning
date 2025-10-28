package com.example.colistapp.models;

public class CourseModel {
    int id;
    String courseName, courseDescription, courseAuthorName, courseAuthorContact, coursePrice;

    public CourseModel() {
    }

    public CourseModel(int id, String courseName, String courseDescription, String courseAuthorName, String courseAuthorContact, String coursePrice) {
        this.id = id;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.courseAuthorName = courseAuthorName;
        this.courseAuthorContact = courseAuthorContact;
        this.coursePrice = coursePrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getCourseAuthorName() {
        return courseAuthorName;
    }

    public void setCourseAuthorName(String courseAuthorName) {
        this.courseAuthorName = courseAuthorName;
    }

    public String getCourseAuthorContact() {
        return courseAuthorContact;
    }

    public void setCourseAuthorContact(String courseAuthorContact) {
        this.courseAuthorContact = courseAuthorContact;
    }

    public String getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(String coursePrice) {
        this.coursePrice = coursePrice;
    }
}


package com.example.colistapp.constants;

public class Constant { //db name and versions
    public static final String DB_NAME= "E_book";
    public static final int DB_VERSION = 1;

    //table names
    public static final String TABLE_REGISTRATION ="register";
    public static final String TABLE_COURSES = "courses";

    // db column names for registration table
    public static final String COL_ID ="id";
    public static final String COL_NAME = "name";
    public static final String COL_EMAIL = "email";
    public static final String COL_PASSWORD = "password";

    // shared preferences
    public static final String shared_var_name = "E_book";
    public static final String login_flag = "isLoggedIn";

    //db columns for add new book
    public static final String COL_COURSE_ID ="id";
    public static final String COL_COURSE_NAME = "course_name";
    public static final String COL_COURSE_DESCRIPTION = "course_description";
    public static final String COL_COURSE_AUTHOR_NAME = "author_name";
    public static final String COL_COURSE_PRICE= "price";
    public static final String COL_COURSE_AUTHOR_CONTACT= "author_contact";
}


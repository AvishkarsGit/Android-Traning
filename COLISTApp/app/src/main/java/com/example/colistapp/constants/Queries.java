package com.example.colistapp.constants;

public class Queries {

    public static final String CREATE_TABLE =
            "CREATE TABLE "+ Constant.TABLE_REGISTRATION+"("+
                    Constant.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    Constant.COL_NAME + " TEXT,"+
                    Constant.COL_EMAIL + " TEXT,"+
                    Constant.COL_PASSWORD + " TEXT"+
                    ");";



    //create table books ( id integer primary key autoincrement, name varchar(30), author varchar(30), price float , contact varchar(40) );
    public static final String CREATE_BOOK_TABLE =
            "CREATE TABLE "+Constant.TABLE_COURSES+"("+
                    Constant.COL_COURSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    Constant.COL_COURSE_NAME + " TEXT,"+
                    Constant.COL_COURSE_DESCRIPTION + " TEXT,"+
                    Constant.COL_COURSE_AUTHOR_NAME + " TEXT,"+
                    Constant.COL_COURSE_PRICE + " TEXT,"+
                    Constant.COL_COURSE_AUTHOR_CONTACT + " TEXT"+
                    ");";

}





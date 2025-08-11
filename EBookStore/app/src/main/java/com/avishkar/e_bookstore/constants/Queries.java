package com.avishkar.e_bookstore.constants;

public class Queries {

    //create table register(id integer primary key autoincrement, name varchar(40), username varchar(30), password varchar(30), confirm_password varchar(30));
    public static final String CREATE_TABLE =
            "CREATE TABLE "+Constant.TABLE_REGISTRATION+"("+
            Constant.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
            Constant.COL_NAME + " TEXT,"+
            Constant.COL_USERNAME + " TEXT,"+
            Constant.COL_PASSWORD + " TEXT"+
            ");";



    //create table books ( id integer primary key autoincrement, name varchar(30), author varchar(30), price float , contact varchar(40) );
    public static final String CREATE_BOOK_TABLE =
            "CREATE TABLE "+Constant.TABLE_BOOKS+"("+
                    Constant.COL_BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    Constant.COL_BOOK_NAME + " TEXT,"+
                    Constant.COL_AUTHOR_NAME + " TEXT,"+
                    Constant.COL_BOOK_PRICE + " TEXT,"+
                    Constant.COL_AUTHOR_CONTACT + " TEXT"+
                    ");";

}

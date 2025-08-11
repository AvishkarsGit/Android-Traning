package com.avishkar.sqlitedatabaseexample.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.avishkar.sqlitedatabaseexample.model.UsersModel;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "users_data";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "users";
    private static final String COL_ID = "ID";
    private static final String COL_NAME = "NAME";
    private static final String COL_EMAIL = "EMAIL";
    private static final String COL_PHONE = "PHONE";
    private static final String COL_IMAGE = "IMAGE";

    public DatabaseHelper( Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATE TABLE users(Id INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR(50), Email VARCHAR(50), Phone VARCHAR(50));

        String createTableQuery =
                "CREATE TABLE "+ TABLE_NAME + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_NAME +
                       " TEXT," + COL_EMAIL + " TEXT,"+ COL_PHONE+ " TEXT,"+ COL_IMAGE + " TEXT"+ ");";

        db.execSQL(createTableQuery);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);

        onCreate(db);


    }

    public void addUsers(String name, String email, String phone,String imgUrl) {

       SQLiteDatabase database =  this.getWritableDatabase(); //open this database

        ContentValues values = new ContentValues();
        values.put(COL_NAME,name);
        values.put(COL_EMAIL,email);
        values.put(COL_PHONE,phone);
        values.put(COL_IMAGE,imgUrl);

        database.insert(TABLE_NAME,null,values);
    }

    public ArrayList<UsersModel> fetchUsers() {
        SQLiteDatabase db =  this.getReadableDatabase(); //database is opened for read  only

        ArrayList<UsersModel> usersList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);

        while(cursor.moveToNext()) {
            UsersModel model = new UsersModel();
            model.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID)));
            model.setName(cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME)));
            model.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(COL_EMAIL)));
            model.setPhone(cursor.getString(cursor.getColumnIndexOrThrow(COL_PHONE)));
            model.setImg(cursor.getString(cursor.getColumnIndexOrThrow(COL_IMAGE)));
            usersList.add(model);
        }
        return usersList;
    }

}

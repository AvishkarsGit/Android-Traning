package com.avishkar.sqlitedatabase.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="CONTACTS_DB";
    private static final int DB_VERSION=1;
    private static final String TABLE_NAME="contacts";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_PHONE = "contact_no";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATE TABLE contacts (Id INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Phone TEXT);

        String createTable = "CREATE TABLE "+TABLE_NAME+ "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_NAME + " TEXT," + COL_PHONE + " TEXT" + ");";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public void addContacts(String name, String phoneNo) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_NAME,name);
        values.put(COL_PHONE,phoneNo);

        database.insert(TABLE_NAME,null,values);
    }
}

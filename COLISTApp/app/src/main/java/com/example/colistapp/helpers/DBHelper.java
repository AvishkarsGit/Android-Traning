package com.example.colistapp.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.colistapp.constants.Queries;
import com.example.colistapp.constants.Constant;
import com.example.colistapp.models.CourseModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {


    private String string;

    public DBHelper(Context context) {
        super(context, Constant.DB_NAME, null, Constant.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Queries.CREATE_TABLE);
        db.execSQL(Queries.CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constant.TABLE_REGISTRATION);
        db.execSQL("DROP TABLE IF EXISTS " + Constant.TABLE_COURSES);
        onCreate(db);
    }

    public Boolean registerUser(String name, String email, String password) {
        //open the database for writing
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constant.COL_NAME, name);
        values.put(Constant.COL_EMAIL, email);
        values.put(Constant.COL_PASSWORD, password);

        long result = database.insert(Constant.TABLE_REGISTRATION, null, values);
        if (result != -1) {
            return true;
        } else {
            return false;
        }

    }

    public Boolean login(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase(); //open database in readable format

        String loginQuery =
                "SELECT * FROM " + Constant.TABLE_REGISTRATION + " WHERE " + Constant.COL_EMAIL +
                        " = ? " + "and " + Constant.COL_PASSWORD + " = ?";
        //select * from register where username = ? and password = ?;

        Cursor cursor = db.rawQuery(loginQuery, new String[]{email, password});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }

    }


    public Boolean addNewCourse(String course_name,String description, String author_name, String price, String contact) {
        //open the database for writing
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constant.COL_COURSE_NAME, course_name);
        values.put(Constant.COL_COURSE_DESCRIPTION, description);
        values.put(Constant.COL_COURSE_AUTHOR_NAME, author_name);
        values.put(Constant.COL_COURSE_PRICE, price);
        values.put(Constant.COL_COURSE_AUTHOR_CONTACT, contact);

        long result = database.insert(Constant.TABLE_COURSES, null, values);
        return result != -1;

    }

    public ArrayList<CourseModel> getAllCourses() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<CourseModel> arrayList = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Constant.TABLE_COURSES ,null);

        while (cursor.moveToNext()) {
            CourseModel model = new CourseModel();
            model.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Constant.COL_COURSE_ID)));
            model.setCourseName(cursor.getString(cursor.getColumnIndexOrThrow(Constant.COL_COURSE_NAME)));
            model.setCourseDescription(cursor.getString(cursor.getColumnIndexOrThrow(Constant.COL_COURSE_DESCRIPTION)));
            model.setCourseAuthorName(cursor.getString(cursor.getColumnIndexOrThrow(Constant.COL_COURSE_AUTHOR_NAME)));
            model.setCourseAuthorContact(cursor.getString(cursor.getColumnIndexOrThrow(Constant.COL_COURSE_AUTHOR_CONTACT)));
            model.setCoursePrice(cursor.getString(cursor.getColumnIndexOrThrow(Constant.COL_COURSE_PRICE)));

            arrayList.add(model);
        }
        return arrayList;


    }

    public Boolean editCourse(int id, String course_name, String description, String author_name, String price, String contact) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constant.COL_COURSE_NAME, course_name);
        values.put(Constant.COL_COURSE_DESCRIPTION, description);
        values.put(Constant.COL_COURSE_AUTHOR_NAME, author_name);
        values.put(Constant.COL_COURSE_PRICE, price);
        values.put(Constant.COL_COURSE_AUTHOR_CONTACT, contact);
        //id = 2
        int rows = database.update(Constant.TABLE_COURSES, values, Constant.COL_COURSE_ID + " = " + id, null);
        if (rows > 0) {
            return true;
        } else {
            return false;
        }

//        return rows > 0; //simplify if-else

    }

    public Boolean deleteCourse(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(Constant.TABLE_COURSES, Constant.COL_COURSE_ID + " = " + id, null);
        return rows > 0;
    }
}



package com.avishkar.e_bookstore.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.avishkar.e_bookstore.constants.Constant;
import com.avishkar.e_bookstore.constants.Queries;
import com.avishkar.e_bookstore.model.BooksModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context) {
        super(context, Constant.DB_NAME,null,Constant.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Queries.CREATE_TABLE);
        db.execSQL(Queries.CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Constant.TABLE_REGISTRATION);
        db.execSQL("DROP TABLE IF EXISTS "+Constant.TABLE_BOOKS);
        onCreate(db);
    }

    public Boolean registerUser(String name, String username, String password) {
        //open the database for writing
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constant.COL_NAME,name);
        values.put(Constant.COL_USERNAME,username);
        values.put(Constant.COL_PASSWORD,password);

        long result = database.insert(Constant.TABLE_REGISTRATION,null,values);
        if (result != -1) {
            return true;
        }
        else {
            return false;
        }

    }

    public Boolean isLoggedIn(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase(); //open database in readable format

        String loginQuery =
                "SELECT * FROM "+Constant.TABLE_REGISTRATION + " WHERE "+Constant.COL_USERNAME +
                " = ? "+ "and "+Constant.COL_PASSWORD + " = ?";
                //select * from register where username = ? and password = ?;

        Cursor cursor = db.rawQuery(loginQuery,new String[] {username,password});
        if (cursor.getCount() > 0) {
            return true;
        }
        else {
            return false;
        }

    }


    public Boolean addNewBook(String book_name, String author_name, String price, String contact) {
        //open the database for writing
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constant.COL_BOOK_NAME,book_name);
        values.put(Constant.COL_AUTHOR_NAME,author_name);
        values.put(Constant.COL_BOOK_PRICE,price);
        values.put(Constant.COL_AUTHOR_CONTACT,contact);

        long result = database.insert(Constant.TABLE_BOOKS,null,values);
        return result != -1;

    }

    public ArrayList<BooksModel> getAllBooks() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<BooksModel> arrayList =new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+Constant.TABLE_BOOKS + " ORDER BY "+Constant.COL_BOOK_ID+ " DESC "  ,null);

        while (cursor.moveToNext()) {
            BooksModel model =new BooksModel();
            model.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Constant.COL_BOOK_ID)));
            model.setBookName(cursor.getString(cursor.getColumnIndexOrThrow(Constant.COL_BOOK_NAME)));
            model.setAuthorName(cursor.getString(cursor.getColumnIndexOrThrow(Constant.COL_AUTHOR_NAME)));
            model.setAuthorContact(cursor.getString(cursor.getColumnIndexOrThrow(Constant.COL_AUTHOR_CONTACT)));
            model.setPrice(cursor.getString(cursor.getColumnIndexOrThrow(Constant.COL_BOOK_PRICE)));

            arrayList.add(model);
        }
        return arrayList;


    }

    public Boolean editBook(int id, String book_name, String author_name, String price, String contact) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constant.COL_BOOK_NAME,book_name);
        values.put(Constant.COL_AUTHOR_NAME,author_name);
        values.put(Constant.COL_BOOK_PRICE,price);
        values.put(Constant.COL_AUTHOR_CONTACT,contact);
        //id = 2
        int rows = database.update(Constant.TABLE_BOOKS,values,Constant.COL_BOOK_ID + " = "+id,null);
        if (rows > 0) {
            return true;
        }
        else {
            return false;
        }


    }

    public Boolean deleteBook(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(Constant.TABLE_BOOKS,Constant.COL_BOOK_ID+" = "+id,null);
        return rows > 0;
    }

    public ArrayList<BooksModel> searchBooks(String keyword) {
        ArrayList<BooksModel> booksList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+Constant.TABLE_BOOKS+" WHERE "+Constant.COL_BOOK_NAME+" LIKE ? OR "+Constant.COL_AUTHOR_NAME+" LIKE ?",new String[]{"%"+keyword+"%","%"+keyword+"%"});
        if (cursor.moveToFirst()) {
            do {
                BooksModel model = new BooksModel();
                model.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Constant.COL_BOOK_ID)));
                model.setBookName(cursor.getString(cursor.getColumnIndexOrThrow(Constant.COL_BOOK_NAME)));
                model.setAuthorName(cursor.getString(cursor.getColumnIndexOrThrow(Constant.COL_AUTHOR_NAME)));
                model.setAuthorContact(cursor.getString(cursor.getColumnIndexOrThrow(Constant.COL_AUTHOR_CONTACT)));
                model.setPrice(cursor.getString(cursor.getColumnIndexOrThrow(Constant.COL_BOOK_PRICE)));

                booksList.add(model);
            }while (cursor.moveToNext());

        }
        return booksList;
     }





}

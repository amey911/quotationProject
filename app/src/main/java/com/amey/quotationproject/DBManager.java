package com.amey.quotationproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.HashMap;
import java.util.List;

import static com.amey.quotationproject.DatabaseHelper.TABLE_NAME;

public class DBManager {

    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    private List<openData> olist;

    public DBManager(Context c) {
        context = c;

    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }


    public void close() {
        dbHelper.close();
    }

    public void insert(String c_name, String c_location, String c_contact, String c_subject, String c_room, String c_email) {
        ContentValues clientValues = new ContentValues();
        clientValues.put(DatabaseHelper.CLIENT_NAME, c_name);
        clientValues.put(DatabaseHelper.LOCATION, c_location);
        clientValues.put(DatabaseHelper.CONTACT_NUM, c_contact);
        clientValues.put(DatabaseHelper.SUBJECT, c_subject);
        clientValues.put(DatabaseHelper.ROOM_TYPE, c_room);
        clientValues.put(DatabaseHelper.CLIENT_EMAIL, c_email);


        long l = database.insert(TABLE_NAME, null, clientValues);
        Log.e("db", "" + l);
    }


    public int update(String c_name, String c_location, String c_contact, String c_subject, String c_room, String c_email) {
        ContentValues uClientValues = new ContentValues();
        uClientValues.put(DatabaseHelper.CLIENT_NAME, c_name);
        uClientValues.put(DatabaseHelper.LOCATION, c_location);
        uClientValues.put(DatabaseHelper.CONTACT_NUM, c_contact);
        uClientValues.put(DatabaseHelper.SUBJECT, c_subject);
        uClientValues.put(DatabaseHelper.ROOM_TYPE, c_room);
        uClientValues.put(DatabaseHelper.CLIENT_EMAIL, c_email);

        int i = database.update(TABLE_NAME, uClientValues, DatabaseHelper.CLIENT_NAME + "=" + c_name, null);
        return i;

    }


    public void insertItems(String item_name, String item_quantity, String item_rate) {

        ContentValues itemValues = new ContentValues();

        itemValues.put(DatabaseHelper.ITEM_NAME, item_name);
        itemValues.put(DatabaseHelper.QUANTITY, item_quantity);
        itemValues.put(DatabaseHelper.RATE, item_rate);

        long t = database.insert(DatabaseHelper.ITEM_TABLE_NAME, null, itemValues);
        Log.e("db", "" + t);
    }


    public Cursor fetch() {
        String[] columns = new String[]{DatabaseHelper.CLIENT_NAME, DatabaseHelper.LOCATION, DatabaseHelper.CONTACT_NUM, DatabaseHelper.SUBJECT, DatabaseHelper.ROOM_TYPE, DatabaseHelper.CLIENT_EMAIL};
        Cursor cursor = database.query(TABLE_NAME, columns, null, null, null, null, null);


        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }


    public void delete(String c_name) {
//        database.delete(TABLE_NAME, DatabaseHelper.CLIENT_NAME + "=" + c_name, null);
        database.delete(TABLE_NAME, DatabaseHelper.CLIENT_NAME+"=?" , new String[]{c_name});


    }


    public Cursor fetch_items() {
        String[] item_columns = new String[]{DatabaseHelper.ITEM_NAME, DatabaseHelper.QUANTITY, DatabaseHelper.RATE};
        Cursor item_cursor = database.query(DatabaseHelper.ITEM_TABLE_NAME, item_columns, null, null, null, null, null);

        if (item_cursor != null) {
            item_cursor.moveToFirst();


        }
        return item_cursor;
    }


    public Cursor getAllData() {
        SQLiteDatabase database = dbHelper.getWritableDatabase();

            Cursor res = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);





            return res;
        }
    }











package com.amey.quotationproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import static android.content.ContentValues.TAG;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "ClientData ";
    public static final String ITEM_TABLE_NAME = "ItemData ";


    //table columns of Itemdata

    public static final String ITEM_NAME = "itemName ";
    public static final String QUANTITY = "itemQty ";
    public static final String RATE = "itemRate ";



    //table columns of clientdata

    public static final String CLIENT_NAME = "ClientName ";
    public static final String LOCATION = "Location ";
    public static final String CONTACT_NUM = "ContactNum ";
    public static final String SUBJECT = "Subject ";
    public static final String ROOM_TYPE = "RoomType ";
    public static final String CLIENT_EMAIL = "ClientEmail ";

    //databse info

    static final String DB_NAME = "AmeyDesigns_DataBase.DB";

    // database version
    static final int DB_VERSION = 1;


    //creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + CLIENT_NAME + "TEXT," +LOCATION +"TEXT, " + CONTACT_NUM+ "INTEGER, " +SUBJECT+ "TEXT, " +ROOM_TYPE+ "TEXT, " +CLIENT_EMAIL+ "TEXT);";

    private static final String CREATE_ITEM_TABLE = "create table " + ITEM_TABLE_NAME + "( " + ITEM_NAME + "TEXT, " +QUANTITY+ "TEXT, " +RATE+ "TEXT);";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);

        db.execSQL(CREATE_ITEM_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

//    public boolean checkAndCopyDatabase() {
//
//        boolean dbExists = checkAndCopyDatabase();
//        if (dbExists) {
//            Log.d(TAG, "checkAndCopyDatabase: ");
//        }else {
//            this.getReadableDatabase();
//        }
//
//    }





}

package com.pyrospiral.android.tabbedtimetable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by adeshkala on 28/06/15.
 */
public class DBEvent {
    public static final String ROW_ID = "_id";
    public static final String EVENT = "event";
    public static final String DATE= "date";
    public static final String CHAPTER="chapter";
    public static final String TIME="time";


    private static final String TAG = "Event";
    private static final String DATABASE_NAME = "MyDBE";
    private static final String DATABASE_TABLE = "event";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_CREATE =
            "create table "+DATABASE_TABLE+" ("+ROW_ID+" integer primary key autoincrement, "
                    +EVENT+" VARCHAR(255), "+CHAPTER+" VARCHAR(255), "+TIME+" VARCHAR(255), "+DATE+" VARCHAR(255));";
    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBEvent(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            long x;
            try {
                db.execSQL(DATABASE_CREATE);


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS event");
            onCreate(db);
        }
    }


    //---opens the database---
    public DBEvent open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        DBHelper.close();
    }


    //---insert a contact into the database---
    public long insertContact(String event, String chapter,String date,String time) {

        ContentValues initialValues = new ContentValues();

        initialValues.put(EVENT,event);

        initialValues.put(DATE, date);

        initialValues.put(CHAPTER,chapter);

        initialValues.put(TIME,time);

        return db.insert(DATABASE_TABLE, null, initialValues);
    }


    //---retrieves all the contacts---
    public Cursor getAllContacts() {
        return db.query(DATABASE_TABLE, new String[]{ROW_ID,EVENT,DATE,CHAPTER,TIME},
                null, null, null, null,null);
    }



    public void deleteEverything() {
        db.delete(DATABASE_TABLE, null , null);
    }


}
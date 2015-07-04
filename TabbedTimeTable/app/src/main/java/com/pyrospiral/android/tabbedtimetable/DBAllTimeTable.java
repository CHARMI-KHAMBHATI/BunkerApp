package com.pyrospiral.android.tabbedtimetable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by adeshkala on 30/06/15.
 */
public class DBAllTimeTable{

    public static final String ROW_ID = "_id";

    public static final String DIVISION="division";


            // public static final String KEY_EMAIL = "email";

    private static final String TAG = "All_times";
    private static final String DATABASE_NAME = "All_timeee";
    private static final String DATABASE_TABLE = "time_tableall";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_CREATE =
            "create table "+DATABASE_TABLE+" ("+ROW_ID+" integer primary key autoincrement, "
                    +DIVISION+" VARCHAR(255));";
    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;


        public DBAllTimeTable(Context ctx) {
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
        db.execSQL("DROP TABLE IF EXISTS time_tableall");
        onCreate(db);
    }
}


    //---opens the database---
    public DBAllTimeTable open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }


    //---closes the database---
    public void close() {
        DBHelper.close();
    }


    //---insert a contact into the database---
    public long insertContact(String division) {
        ContentValues initialValues = new ContentValues();

        initialValues.put(DIVISION, division);


        return db.insert(DATABASE_TABLE, null, initialValues);
    }
    public boolean deleteContact(String subject) {
        return db.delete(DATABASE_TABLE, DBAdapter.SUBJECT+" = '"+subject+"'", null) > 0;
    }


    //---retrieves all the contacts---
    public Cursor getAllContacts(String division) {
        return db.query(DATABASE_TABLE, new String[]{ROW_ID,DIVISION},
                DBAllTimeTable.DIVISION+" = '"+division+"'", null, null, null,null);
    }

    public Cursor getAllContact() {
        return db.query(DATABASE_TABLE, new String[]{ROW_ID,DIVISION},
                null, null, null, null,null);
    }


    //  ---retrieves a particular contact---
    public Cursor getContact() throws SQLException {
        return db.query(true,DATABASE_TABLE, new String[]{DIVISION},null,
                null, null, null, null,null);

    }




    //---updates a contact---



    public void deleteEverything() {
        db.delete(DATABASE_TABLE, null , null);
    }


}


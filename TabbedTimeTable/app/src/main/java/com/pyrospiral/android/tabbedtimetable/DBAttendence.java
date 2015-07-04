package com.pyrospiral.android.tabbedtimetable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



import android.util.Log;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAttendence {
    public static final String ROW_ID = "_id";
    public static final String SUBJECT = "subj";
    public static final String PRESENT = "present";
    public static final String ABSENT = "absent";
    public static final String TOTAL = "total";



    // public static final String KEY_EMAIL = "email";

    private static final String TAG = "DBAdapterAtten";
    private static final String DATABASE_NAME = "MyDBatten";
    private static final String DATABASE_TABLE = "attendence";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_CREATE =
            "create table "+DATABASE_TABLE+" ("+ROW_ID+" integer primary key autoincrement, "
                    +SUBJECT+" VARCHAR(255), "
                    +PRESENT+" INTEGER, "+ABSENT+" INTEGER, "+TOTAL+" INTEGER );";
    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;


    public DBAttendence(Context ctx) {
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
                 /*
                ContentValues initialValues = new ContentValues();

                initialValues.put(DAY, "MONDAY");
                initialValues.put(LEC_NO, "1");
                initialValues.put(SUBJECT, "CO");
                initialValues.put(TEACHER, "DPR");
                initialValues.put(START_TIME, "12:00");
                initialValues.put(END_TIME, "1:00");
                initialValues.put(ATTENDANCE, "90");

                x=db.insert(DATABASE_TABLE, null, initialValues);

                 */

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS time_table");
            onCreate(db);
        }
    }


    //---opens the database---
    public DBAttendence open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }


    //---closes the database---
    public void close() {
        DBHelper.close();
    }


    //---insert a contact into the database---
    public long insertContact(String subject) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(SUBJECT, subject);
        initialValues.put(PRESENT, 0);

        initialValues.put(ABSENT,0);

        initialValues.put(TOTAL,0);

        //initialValues.put(ASSIGNMENT, false);

        return db.insert(DATABASE_TABLE, null, initialValues);
    }


        //---deletes a particular contact---
        public boolean deleteContact(String subject) {
            return db.delete(DATABASE_TABLE, DBAttendence.SUBJECT+" = '"+subject+"'", null) > 0;
        }


    //---retrieves all the contacts---
    public Cursor getAllContacts() {
        return db.query(DATABASE_TABLE, new String[]{ROW_ID,SUBJECT,PRESENT,ABSENT,TOTAL},
                null, null, null, null,null);
    }


    //  ---retrieves a particular contact---
    public Cursor getContact(String subjects) throws SQLException {
        return db.query(DATABASE_TABLE, new String[]{ROW_ID,SUBJECT,PRESENT,ABSENT,TOTAL},DBAttendence.SUBJECT + "= '" + subjects+"'",
                null, null, null, null,null);

    }



    //---updates a contact---

    public boolean updateContact(long rowId, int present, int absent, int total) {
        ContentValues args = new ContentValues();
        args.put(PRESENT, present);
        args.put(ABSENT, absent);
        args.put(TOTAL, total);
        return db.update(DATABASE_TABLE, args, ROW_ID + "=" + rowId, null) > 0;
    }

    public void deleteEverything() {
        db.delete(DATABASE_TABLE, null, null);
    }


}


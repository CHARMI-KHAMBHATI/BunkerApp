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

public class DBAdapterAssign {
    public static final String ROW_ID = "_id";
    //public static final String DAY = "day";
    //public static final String LEC_NO= "lec_num";
    public static final String SUBJECT = "subj";
    //public static final String TEACHER = "teacher";
    //public static final String START_TIME = "strtime";
    public static final String DUE_DATE = "due_date";
    //public static final String ATTENDANCE = "attendance";
    public static final String  ASSIGNMENT="assignement";

    // public static final String KEY_EMAIL = "email";

    private static final String TAG = "DBAdapterAss";
    private static final String DATABASE_NAME = "MyDBA";
    private static final String DATABASE_TABLE = "assignment";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_CREATE =
            "create table "+DATABASE_TABLE+" ("+ROW_ID+" integer primary key autoincrement, "
                    +SUBJECT+" VARCHAR(255), "
                    +DUE_DATE+" VARCHAR(255), "+ASSIGNMENT+" VARCHAR(255));";
    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;


    public DBAdapterAssign(Context ctx) {
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
            db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE+"");
            onCreate(db);
        }
    }


    //---opens the database---
    public DBAdapterAssign open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }


    //---closes the database---
    public void close() {
        DBHelper.close();
    }


    //---insert a contact into the database---
    public long insertContact(String subject,String end_time,String assignemnt) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(SUBJECT, subject);
        //initialValues.put(TEACHER, teacher);

        //initialValues.put(START_TIME,start_time);

        initialValues.put(DUE_DATE, end_time);

        initialValues.put(ASSIGNMENT, assignemnt);

        return db.insert(DATABASE_TABLE, null, initialValues);
    }


        //---deletes a particular contact---
        public boolean deleteContact(long rowId) {
            return db.delete(DATABASE_TABLE, ROW_ID + "=" + rowId, null) > 0;
        }


    //---retrieves all the contacts---
    public Cursor getAllContacts() {
        return db.query(DATABASE_TABLE, new String[]{ROW_ID,SUBJECT,DUE_DATE,ASSIGNMENT},
                null, null, null, null,null);
    }


    //  ---retrieves a particular contact---
    public Cursor getContact(String days) throws SQLException {
        return db.query(DATABASE_TABLE, new String[]{ROW_ID,SUBJECT,DUE_DATE,ASSIGNMENT},DBAdapterAssign.ASSIGNMENT+"= "+"'"+days+"'",
                null, null, null, null,null);

    }

/*
    //---updates a contact---
    public boolean updateContact(long rowId, String name, String email) {
        ContentValues args = new ContentValues();
        args.put(KEY_NAME, name);
        args.put(KEY_EMAIL, email);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
*/

}


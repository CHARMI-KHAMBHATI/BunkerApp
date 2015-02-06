package com.pyrospiral.android.tabbedtimetable;

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

public class DBAdapterF {
    public static final String ROW_ID = "_id";
    public static final String DAY = "day";
    public static final String LEC_NO= "lec_num";
    public static final String SUBJECT = "subj";
    public static final String TEACHER = "teacher";
    public static final String START_TIME = "strtime";
    public static final String END_TIME = "endtime";
    //public static final String ATTENDANCE = "attendance";
    public static final String  ASSIGNMENT="assignement";
    public static final String  DONE="done";

    // public static final String KEY_EMAIL = "email";

    private static final String TAG = "DBAdapterF";
    private static final String DATABASE_NAME = "MyDBF";
    private static final String DATABASE_TABLE = "time_tableF";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_CREATE =
            "create table "+DATABASE_TABLE+" ("+ROW_ID+" integer primary key autoincrement, "
                    +LEC_NO+" VARCHAR(255), "+DAY+" DOUBLE, "+DONE+" DOUBLE, "+SUBJECT+" VARCHAR(255), "
                    +TEACHER+" VARCHAR(255), "+START_TIME+" DOUBLE, "+END_TIME+" DOUBLE, "+ASSIGNMENT+" BOOLEAN);";
    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;


    public DBAdapterF(Context ctx) {
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

                //2nd



                initialValues.put(DAY, "MONDAY");
                initialValues.put(LEC_NO, "2");
                initialValues.put(SUBJECT, "TCS");
                initialValues.put(TEACHER, "ZAVERI SIR");
                initialValues.put(START_TIME, "13:00");
                initialValues.put(END_TIME, "14:00");
                initialValues.put(ATTENDANCE, "95");

                x=db.insert(DATABASE_TABLE, null, initialValues);

                //3rd



                initialValues.put(DAY, "MONDAY");
                initialValues.put(LEC_NO, "1");
                initialValues.put(SUBJECT, "MATHS");
                initialValues.put(TEACHER, "RANJAN ROY");
                initialValues.put(START_TIME, "15:00");
                initialValues.put(END_TIME, "16:00");
                initialValues.put(ATTENDANCE, "95");

                x=db.insert(DATABASE_TABLE, null, initialValues);

                //4th



                initialValues.put(DAY, "MONDAY");
                initialValues.put(LEC_NO, "1");
                initialValues.put(SUBJECT, "DS");
                initialValues.put(TEACHER, "RUPA");
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
    public DBAdapterF open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }


    //---closes the database---
    public void close() {
        DBHelper.close();
    }


    //---insert a contact into the database---
    public long insertContact(String subject, double start_time, double end_time,String teacher) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(SUBJECT, subject);
        initialValues.put(TEACHER, teacher);

        initialValues.put(START_TIME,start_time);

        initialValues.put(END_TIME, end_time);

        initialValues.put(ASSIGNMENT, false);

        initialValues.put(DAY, 0);

        initialValues.put(DONE, 0);


        return db.insert(DATABASE_TABLE, null, initialValues);
    }
    public boolean deleteContact(String subject) {
        return db.delete(DATABASE_TABLE, DBAdapterF.SUBJECT+" = '"+subject+"'", null) > 0;
    }

    //---retrieves all the contacts---
    public Cursor getAllContacts() {
        return db.query(DATABASE_TABLE, new String[]{ROW_ID,LEC_NO,SUBJECT,TEACHER,START_TIME,END_TIME,DAY,DONE},
                null, null, null, null,START_TIME+ " ASC");
    }


    //  ---retrieves a particular contact---
    public Cursor getContact(String days) throws SQLException {
        return db.query(DATABASE_TABLE, new String[]{ROW_ID,LEC_NO,SUBJECT,TEACHER
                        ,START_TIME,END_TIME},null,
                null, null, null, null,null);

    }

    public Cursor getPosition(String days) throws SQLException {
        return db.query(DATABASE_TABLE, new String[]{ROW_ID,LEC_NO,SUBJECT,TEACHER
                        ,START_TIME,END_TIME},SUBJECT+"= '" + days+"'",
                null, null, null, null,null);

    }


    //---updates a contact---
    public boolean updateContact(int rowId,int x,int value) {
        ContentValues args = new ContentValues();

        args.put(DAY,x);
        args.put(DONE,value);
        return db.update(DATABASE_TABLE, args, ROW_ID + "=" + rowId, null) > 0;
    }

    public boolean updateSubject(String subjects) {
        ContentValues args = new ContentValues();

        //args.put(DAY,x);
        args.put(DONE,1);
        return db.update(DATABASE_TABLE, args, DBAdapterM.SUBJECT + "= '" + subjects+"'", null) > 0;
    }


}




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
    public static final String DESCRIPTION="description";
    public static final String LOCATION="location";
    public static final String TEAMSIZE="teamsize";
    public static final String FEE="fee";
    public static final String LINK="link";
    public static final String COORDI1N="name1";
    public static final String COORDI1NU="num1";
    public static final String COORDI2N="name2";
    public static final String COORDI2NU="num2";
    public static final String FAVS="fav";


    private static final String TAG = "Event";
    private static final String DATABASE_NAME = "MyDBE";
    private static final String DATABASE_TABLE = "event";
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_CREATE =
            "create table "+DATABASE_TABLE+" ("+ROW_ID+" integer primary key autoincrement, "
                    +EVENT+" VARCHAR(255), "+CHAPTER+" VARCHAR(255), "+TIME+" VARCHAR(255), "+DESCRIPTION+" VARCHAR(1255), "
                    +LOCATION+" VARCHAR(255), "+TEAMSIZE+" VARCHAR(255), "+FEE+" VARCHAR(255), "+LINK+" VARCHAR(255), "
                    +COORDI1N+" VARCHAR(255), "+COORDI1NU+" VARCHAR(255), "+COORDI2N+" VARCHAR(255), "
                    +COORDI2NU+" VARCHAR(255), "+FAVS+" INTEGER, "+DATE+" VARCHAR(255));";
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
    public long insertContact(String event, String chapter,String date,String time,String
                              location,String teamsize,String fee,String link,String co1,String co2,String con1,String con2,
                              String desc
                              ) {

        ContentValues initialValues = new ContentValues();

        initialValues.put(EVENT,event);

        initialValues.put(DATE, date);

        initialValues.put(CHAPTER,chapter);

        initialValues.put(TIME,time);

        initialValues.put(LOCATION,location);

        initialValues.put(TEAMSIZE,teamsize);

        initialValues.put(FEE,fee);

        initialValues.put(LINK,link);

        initialValues.put(COORDI1N,co1);

        initialValues.put(COORDI2N,co2);

        initialValues.put(COORDI1NU,con1);

        initialValues.put(COORDI2NU,con2);

        initialValues.put(DESCRIPTION,desc);

        initialValues.put(FAVS,0);



        return db.insert(DATABASE_TABLE, null, initialValues);
    }


    //---retrieves all the contacts---
    public Cursor getAllContacts() {
        return db.query(DATABASE_TABLE, new String[]{ROW_ID,EVENT,DATE,CHAPTER,TIME,LOCATION
                ,TEAMSIZE,FEE,LINK,COORDI1N,COORDI2N,COORDI2NU,COORDI1NU,DESCRIPTION},
                null, null, null, null,null);
    }

    public Cursor getFavEvent(){
        return db.query(DATABASE_TABLE, new String[]{ROW_ID,EVENT,DATE,CHAPTER,TIME,LOCATION
                        ,TEAMSIZE,FEE,LINK,COORDI1N,COORDI2N,COORDI2NU,COORDI1NU,DESCRIPTION},
                DBEvent.FAVS + "=" + 1, null, null, null,null);
    }

    public Cursor isFavEvent(String eventName){
        return db.query(DATABASE_TABLE, new String[]{FAVS},
                DBEvent.FAVS + "=" + 1+" AND "+DBEvent.EVENT + "= '" + eventName+"'", null, null, null,null);
    }


    public boolean makeFav(String eventName,int fav_setting) {
        ContentValues args = new ContentValues();


        args.put(FAVS,fav_setting);
        return db.update(DATABASE_TABLE, args, DBEvent.EVENT + "= '" + eventName+"'", null) > 0;
    }



    public void deleteEverything() {
        db.delete(DATABASE_TABLE, null , null);
    }


}
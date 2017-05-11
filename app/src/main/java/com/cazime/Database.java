package com.cazime;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by gannu on 10-05-2017.
 */

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "cazimedatabase";
    private static int DATABASE_VERSION = 1;
    public static String ID = "_id";
    public static final String TABLE = "SIGNUP";
    public static final String NAME = "SIGNUP";
    public static final String USERNAME = "SIGNUP";
    public static final String DOB = "SIGNUP";
    public static final String PASSWORD = "SIGNUP";
    private static final String CREATE_TABEL = "CREATE TABLE " + TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            NAME + " TEXT NOT NULL, " + USERNAME + " TEXT NOT NULL, " + DOB + " TEXT NOT NULL, " + PASSWORD + " TEXT NOT NULL); ";


    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABEL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void insert(String name, String username, String passsword, String dob) {
        ContentValues values = new ContentValues(4);
        values.put(NAME, name);
        values.put(USERNAME, username);
        values.put(PASSWORD, passsword);
        values.put(DOB, dob);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE, null, values);
        db.close();
    }
}

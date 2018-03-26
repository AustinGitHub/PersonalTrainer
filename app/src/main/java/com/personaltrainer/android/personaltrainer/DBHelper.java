package com.personaltrainer.android.personaltrainer;

/**
 * Created by Austin on 3/25/2018.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 7;

    private static final String DATABASE_NAME = "users.db";

    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String CREATE_TABLE_USER = "CREATE TABLE " + UserInfo.TABLE  + "("
                + UserInfo.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + UserInfo.KEY_name + " TEXT, "
                + UserInfo.KEY_lname + " TEXT ) ";

        db.execSQL(CREATE_TABLE_USER);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + UserInfo.TABLE);


        onCreate(db);

    }

}
package com.personaltrainer.android.personaltrainer;

/**
 * Created by Austin on 3/25/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.HashMap;


public class UserRepo {
    private DBHelper dbHelper;

    public UserRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(UserInfo user) {


        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserInfo.KEY_lname,user.lname);
        values.put(UserInfo.KEY_name, user.name);




        long UserId = db.insert(UserInfo.TABLE, null, values);
        db.close();
        return (int) UserId;
    }

    public void delete(int UserID) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
               db.delete(UserInfo.TABLE, UserInfo.KEY_ID + "= ?", new String[] { String.valueOf(UserID) });
        db.close();
    }

    public void update(UserInfo user) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(UserInfo.KEY_lname,user.lname);
        values.put(UserInfo.KEY_name, user.name);



        db.update(UserInfo.TABLE, values,UserInfo.KEY_ID + "= ?", new String[] { String.valueOf(user.user_ID) });
        db.close();
    }

    public ArrayList<HashMap<String, String>>  getUserList() {

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                UserInfo.KEY_ID + "," +
                UserInfo.KEY_name + ","  +
                UserInfo.KEY_lname +
                " FROM " + UserInfo.TABLE;


        ArrayList<HashMap<String, String>> userList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> user = new HashMap<String, String>();
                user.put("id", cursor.getString(cursor.getColumnIndex(UserInfo.KEY_ID)));
                user.put("name", cursor.getString(cursor.getColumnIndex(UserInfo.KEY_name)));
                userList.add(user);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return userList;

    }

    public UserInfo getUserById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                UserInfo.KEY_ID + "," +
                UserInfo.KEY_name + "," +
                UserInfo.KEY_lname +
                " FROM " + UserInfo.TABLE
                + " WHERE " +
                UserInfo.KEY_ID + "=?";

        int iCount = 0;
        UserInfo user = new UserInfo();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                user.user_ID =cursor.getInt(cursor.getColumnIndex(UserInfo.KEY_ID));
                user.name =cursor.getString(cursor.getColumnIndex(UserInfo.KEY_name));
                user.lname  =cursor.getString(cursor.getColumnIndex(UserInfo.KEY_lname));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return user;
    }

}
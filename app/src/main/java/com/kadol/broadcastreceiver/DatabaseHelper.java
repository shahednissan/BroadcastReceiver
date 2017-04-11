package com.kadol.broadcastreceiver;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Nafi on 4/11/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="Messages.db";
    public static final String MESSAGE_TABLE="message";
    public static final String MESSAGE_COLUMN_ID="id";
    public static final String MESSAGE_COLUMN_SMS="sms";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+MESSAGE_TABLE+" ("+MESSAGE_COLUMN_ID+" integer primary key, "+MESSAGE_COLUMN_SMS+" text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void onDelete(){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+MESSAGE_TABLE);
        onCreate(db);
    }

    public void insert(String message){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(MESSAGE_COLUMN_SMS,message);
        db.insert(MESSAGE_TABLE,null,contentValues);
    }

    public ArrayList<String> sendData(){
        ArrayList<String> arrayList=new ArrayList<>();

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from "+MESSAGE_TABLE,null);

        res.moveToFirst();

        while(res.isAfterLast()==false){
            String message=res.getString(res.getColumnIndex(DatabaseHelper.MESSAGE_COLUMN_SMS));
            arrayList.add(message);

            res.moveToNext();
        }
        res.close();

        return arrayList;
    }
}

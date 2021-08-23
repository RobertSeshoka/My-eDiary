package com.example.myediary;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class programDatabase extends SQLiteOpenHelper {


    public static final String database = "MyDiary.db";
    public static final String table = "tblMyDiary";


    public static final String COLUMN_1 = "ID";
    public static final String COLUMN_2 = "date";
    public static final String COLUMN_3 = "semsterNumber";
    public static final String COLUMN_4 = "topic";
    public static final String COLUMN_5 = "description";
    public static final String COLUMN_6 = "tasks";


    public programDatabase(@Nullable Context context) {
        super(context, database, null, 1);
    }

    //Implemented methods
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + table + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, semsterNumber TEXT, topic TEXT, description TEXT, tasks TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table);
    }

    //Gets all the data from the database table
    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from " + table, null);
        return cursor;

    }

}

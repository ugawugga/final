package com.example.danny.afinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Danny on 2017-10-20.
 */


public class ChatDatabaseHelper extends SQLiteOpenHelper {

    static String DATABASE_NAME = "proj1.db";
    static int VERSION_NUM = 2;
    final static String KEY_ID = "_ID";
    final static String KEY_MESSAGE = "message";
    final static String DISTANCE= "distance";
    final static String LITRES="litres";
    final static String COST="cost";
    final static String DATE="date";
    final static String TABLE_NAME = "tbl";


    public ChatDatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME +
                "( "+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ KEY_MESSAGE+" TEXT, "+DATE+" DATETIME DEFAULT CURRENT_TIMESTAMP," +
                " "+DISTANCE+" DECIMAL(10,4), "+LITRES+" DECIMAL(10,4), "+COST+" DECIMAL(10,4));");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }



}

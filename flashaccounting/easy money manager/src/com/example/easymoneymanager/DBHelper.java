package com.example.easymoneymanager;

import static android.provider.BaseColumns._ID;
import static com.example.easymoneymanager.DbConstants.classes;
import static com.example.easymoneymanager.DbConstants.data;
import static com.example.easymoneymanager.DbConstants.group;
import static com.example.easymoneymanager.DbConstants.list;
import static com.example.easymoneymanager.DbConstants.location;
import static com.example.easymoneymanager.DbConstants.money;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "demo.db";
    private final static int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String INIT_TABLE = "CREATE TABLE " + classes + " (" +
                                  _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                  group + " CHAR, " +
                                  money + " int, " +
                                  list + " CHAR, " +
                                  location + " CHAR, " +
                                  data + " CHAR);"; 
        
        db.execSQL(INIT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String DROP_TABLE = "DROP TABLE IF EXISTS " + classes;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

}
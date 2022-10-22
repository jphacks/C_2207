package com.example.testprog;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "note_memo.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE note_memo (");
        sb.append("_id INTEGER PRIMARY KEY,");
        sb.append("name TEXT,");
        sb.append("note TEXT");
        sb.append(");");
        String sql = sb.toString();

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

}





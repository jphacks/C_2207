package com.example.testprog;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

    public class DatabaseHelper extends SQLiteOpenHelper {

        public static final String DATABASE_NAME = "notememo.db";
        public static final int DATABASE_VERSION = 1;

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        // 初回に一回だけ呼ばれるデータベース
        @Override
        public void onCreate(SQLiteDatabase db) {
            StringBuilder sb = new StringBuilder();
            sb.append("CREATE TABLE notememo(");
            sb.append("_id INTEGER PRIMARY KEY, "); // 重複しないID
            sb.append("name TEXT, ");
            sb.append("note TEXT");
            sb.append(");");

            String sql = toString();

            db.execSQL(sql);


        }

        // データベースのバージョンが更新されたときに使う(empty)
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        }

    }


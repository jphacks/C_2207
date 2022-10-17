package com.example.testprog;

import androidx.appcompat.app.AppCompatActivity; //追加

import android.database.Cursor; //追加
import android.database.sqlite.SQLiteDatabase; //追加
import android.database.sqlite.SQLiteStatement; //追加
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.EditText; //追加
import android.widget.AdapterView; //追加
import android.widget.SimpleCursorAdapter; //追加

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {

    Button btnSave;
    Button btnDelete;
    ListView lvMemoList = null;
    int memoId = -1;
    int sava_select = 0; // メモの追加 0:新規追加, 1:編集での追加

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);
        lvMemoList = findViewById(R.id.lvMemoList);

        memoListDisplay();

        lvMemoList.setOnItemClickListener(new ListItemClickListener()); // クリックしてメモを編集する


        setViews();
        CalendarView calendar = findViewById(R.id.calendar);
        calendar.setOnDateChangeListener(
                new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int date) {
                        String message = year + "/" + (month + 1) + "/" + date;
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    }
                }


        );


    }

    // 追加ボタン
    public void OnAddButtonClick(View view) {
        EditText etTitle = findViewById(R.id.etTitle);
        etTitle.setText("新しいスケジュール"); // メモを追加時にデフォルトでこのタイトルが付く
        EditText etNote = findViewById(R.id.etNote);
        etNote.setText(""); // メモ内容は空白
        btnSave.setEnabled(true); // ボタンの有効

        sava_select = 0; // 新規追加の時に0
    }

    // 保存ボタン
    public void OnSaveButtonClick(View view) {
        EditText etNote = findViewById(R.id.etNote);
        String note = etNote.getText().toString(); // メモの取得

        EditText etTitle = findViewById(R.id.etTitle);
        String title = etTitle.getText().toString(); // タイトルの取得

        DatabaseHelper helper = new DatabaseHelper(MainActivity.this); // インスタンス
        SQLiteDatabase db = helper.getWritableDatabase();

        if(save_select==1){
            // 編集からの保存時
            try {
                String sqlDelete = "DELETE FROM notememo WHERE _id = ?"; // 選択しているメモの削除
                SQLiteStatement stmt = db.compileStatement(sqlDelete);
                stmt.bindLong(1,memoId);
                stmt.executeUpdateDelete(); // 削除

                String sqlInsert = "INSERT INTO notememo (_id, name, note) VALUES (?, ?, ?)";
                stmt = db.compileStatement(sqlInsert);
                stmt.bindLong(1,memoId);
                stmt.bindString(2,title);
                stmt.bindString(3,note);

                stmt.executeInsert();

            }
            finally {
                db.close();
            }
        }
        else {
            // 新規追加時
            try {
                String sqlInsert = "INSERT INTO notememo (name, note) VALUES (?, ?)";
                SQLiteStatement stmt = db.compileStatement(sqlInsert); // データベースに通知する
                stmt.bindString(1,title);
                stmt.bindString(2,note);

                stmt.executeInsert();
            }
            finally {
                db.close();
            }
        }


        etTitle.setText(""); // 保存した後にタイトルを空欄にする
        etNote.setText("");
        btnSave.setEnabled(false);
        btnDelete.setEnabled(false);

        memoListDisplay();

    }

    // メモリストの表示
    private void memoListDisplay() {
        DatabaseHelper helper = new DatabaseHelper(MainActivity.this);
        SQLiteDatabase db = helper.getReadableDatabase(); // 表示

        try {
            String sql = "SELECT _id.name FROM notememo";
            Cursor cursor = db.rawQuery(sql, null);
            String[] from = {"name"};
            int[] to = {android.R.id.text1};
            SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor,from,to,0);
        }
        finally {
            db.close();
        }
    }

    // リストをクリックした際にする処理
    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            memoId = (int)id;
            save_select = 1; // 編集

            btnSave.setEnabled(true);
            btnDelete.setEnabled(true);

            DatabaseHelper helper = new DatabaseHelper(MainActivity.this);
            SQLiteDatabase db = helper.getReadableDatabase();
            try {
                String sql = "SELECT name, note FROM notememo WHERE _id = "+memoId; // メモをクリックしたときにタイトルとメモ内容を表示
                Cursor cursor = db.rawQuery(sql,null);
                String note = "";
                String title = "";
                while(cursor.moveToNext()) {
                    int idxNote = cursor.getColumnIndex("note"); // メモの内容のインデックスを獲得
                    note = cursor.getString(idxNote); // メモの内容のインデックスからメモの内容を獲得する

                    int idxTitle = cursor.getColumnIndex("name");
                    title = cursor.getString(idxTitle);
                }
                EditText etNote = findViewById(R.id.etNote);
                etNote.setText(note);

                EditText etTitle = findViewById(R.id.etTitle);
                etTitle.setText(title);

            }
            finally {
                db.close();
            }

        }


    }

    // メモの削除
    public  void onDeleteButtonClick(View view) {
        try {
            String sqlDelete = "DELETE FROM notememo WHERE _id = ?"; // 選択しているメモの削除
            SQLiteStatement stmt = db.compileStatement(sqlDelete);
            stmt.bindLong(1,memoId);
            stmt.executeUpdateDelete(); // 削除
        }

        finally {
            db.close();
        }
        EditText etTile = findViewById(R.id.etTitle);
        etTile.setText("");
        EditText etNote = findViewById(R.id.etNote);
        etNote.setText("");
        btnDelete.setEnabled(false);
        btnSave.setEnabled(false);

        memoListDisplay();

    }











    private void setViews() {
        Button nextButton = findViewById(R.id.textView);
        nextButton.setOnClickListener(onClick_button);
    }


    private final View.OnClickListener onClick_button = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplication(), SubActivity.class);
            startActivity(intent);
        }
    };
}

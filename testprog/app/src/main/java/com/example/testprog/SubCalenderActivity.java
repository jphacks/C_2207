package com.example.testprog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;


public class SubCalenderActivity extends AppCompatActivity {
    EditText editText;
    Button btnSave;
    Button btnDelete;
    ListView lvMemoList = null;
    int memoId = -1;
    int save_select = 0;    // 0:新規追加　1:編集の場合


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_calender);

        Button backButton = findViewById(R.id.button2);
        backButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });


        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);
        lvMemoList = findViewById(R.id.lvMemoList);

        memoListDisplay();

        lvMemoList.setOnItemClickListener(new ListItemClickListener());


    }


    // 追加ボタン
    public void onAddButtonClick(View view){


        EditText etTitle = findViewById(R.id.etTitle);
        etTitle.setText("new memo");
        EditText etNote = findViewById(R.id.edit_text);


        etNote.setText("");
        btnSave.setEnabled(true);

        save_select = 0;

    }

    // 保存ボタン
    public void onSaveButtonClick(View view){
        EditText etNote = findViewById(R.id.edit_text);
        String note = etNote.getText().toString();

        EditText etTitle = findViewById(R.id.etTitle);
        String title = etTitle.getText().toString();

        DatabaseHelper helper = new DatabaseHelper(SubCalenderActivity.this);
        SQLiteDatabase db = helper.getWritableDatabase();

        if(save_select==1){
            // 編集からの保存時
            try {
                String sqlDelete = "DELETE FROM note_memo WHERE _id = ?";
                SQLiteStatement stmt = db.compileStatement(sqlDelete);
                stmt.bindLong(1,memoId);
                stmt.executeUpdateDelete();

                String sqlInsert = "INSERT INTO note_memo (_id, name, note) VALUES (?, ?, ?)";
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
                String sqlInsert = "INSERT INTO note_memo (name, note) VALUES (?, ?)";
                SQLiteStatement stmt = db.compileStatement(sqlInsert);
                stmt.bindString(1,title);
                stmt.bindString(2,note);

                stmt.executeInsert();
            }
            finally {
                db.close();
            }
        }


        etTitle.setText("");
        etNote.setText("");
        btnSave.setEnabled(false);
        btnDelete.setEnabled(false);

        memoListDisplay();

    }

    // メモリスト表示
    private void memoListDisplay(){

        DatabaseHelper helper = new DatabaseHelper(SubCalenderActivity.this);
        SQLiteDatabase db = helper.getReadableDatabase();
        try {
            String sql = "SELECT _id,name FROM note_memo";
            Cursor cursor = db.rawQuery(sql,null);
            String[] from = {"name"};
            int[] to = {android.R.id.text1};
            SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,cursor,from,to,0);
            lvMemoList.setAdapter(simpleCursorAdapter);
        }
        finally {
            db.close();
        }

    }

    // Listをクリックしたときのリスナークラス
    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            memoId = (int)id;
            save_select = 1; // 編集

            btnSave.setEnabled(true);
            btnDelete.setEnabled(true);

            DatabaseHelper helper = new DatabaseHelper(SubCalenderActivity.this);
            SQLiteDatabase db = helper.getReadableDatabase();
            try {
                String sql = "SELECT name, note FROM note_memo WHERE _id = "+memoId;
                Cursor cursor = db.rawQuery(sql,null);
                String note = "";
                String title = "";
                while(cursor.moveToNext()){
                    int idxNote = cursor.getColumnIndex("note");
                    note = cursor.getString(idxNote);

                    int idxTitle = cursor.getColumnIndex("name");
                    title = cursor.getString(idxTitle);
                }
                EditText etNote = findViewById(R.id.edit_text);
                etNote.setText(note);

                EditText etTitle = findViewById(R.id.etTitle);
                etTitle.setText(title);

            }
            finally {
                db.close();
            }

        }


    }

    // 削除ボタン
    public void onDeleteButtonClick(View view){
        DatabaseHelper helper = new DatabaseHelper(SubCalenderActivity.this);
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            String sqlDelete = "DELETE FROM note_memo WHERE _id = ?";
            SQLiteStatement stmt = db.compileStatement(sqlDelete);
            stmt.bindLong(1,memoId);
            stmt.executeUpdateDelete();
        }
        finally {
            db.close();
        }

        EditText etTile = findViewById(R.id.etTitle);
        etTile.setText("");
        EditText etNote = findViewById(R.id.edit_text);
        etNote.setText("");
        btnDelete.setEnabled(false);
        btnSave.setEnabled(false);

        memoListDisplay();

    }

    public void selectDate(View view) {

        Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                EditText etNote = findViewById(R.id.edit_text);
                etNote.setText(String.format(Locale.JAPAN, "%02d / %02d / %02d", year, month + 1, dayOfMonth));
            }
        },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

}
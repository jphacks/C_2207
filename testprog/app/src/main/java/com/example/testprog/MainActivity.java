package com.example.testprog;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;




public class MainActivity extends AppCompatActivity {

    Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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

         /*
        Button sendkaigi = (Button)findViewById(R.id.btnAdd);
        sendkaigi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //インテントの作成
                Intent intent = new Intent(getApplication(), SubActivity.class);

                intent.putExtra("name","meeting");
                //遷移先の画面起動
                startActivity(intent);
            }
        });

          */

    }
    // オプションメニュー作成時の処理
    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        getMenuInflater().inflate( R.menu.activity_main, menu );
        return true;
    }

    // オプションメニューのアイテム選択時の処理
    @Override
    public boolean onOptionsItemSelected( MenuItem item )
    {
        if( R.id.menuitem_settings == item.getItemId() )
        {
            Intent intent = new Intent( this, MySettingsActivity.class );
            startActivity( intent );
            return true;
        }
        return false;
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        // 設定値の読み込み
        SharedPreferences prefs                   = PreferenceManager.getDefaultSharedPreferences( getApplicationContext() );
        boolean           bFooFunction            = prefs.getBoolean( "foo_function", false );
        boolean           bBarFunction            = prefs.getBoolean( "bar_function", true );
        String            strBazName              = prefs.getString( "baz_name", "Baaaz" );
        String            strQuxType              = prefs.getString( "qux_type", "2" );
        String[]          astrDefaultCorgeOptions = getResources().getStringArray( R.array.default_corge_options );
        Set<String>       setstrCorgeOptions      = prefs.getStringSet( "corge_options", new HashSet<>( Arrays.asList( astrDefaultCorgeOptions ) ) );

        // 設定値の表示
        TextView textviewSettings = findViewById( R.id.textview_settings );
        String strSettings = "Foo function : " + bFooFunction + "\n" +
                "Bar function : " + bBarFunction + "\n" +
                "Baz name : " + strBazName + "\n" +
                "Qux type : " + strQuxType + "\n" +
                "Corge options : " + setstrCorgeOptions + "\n";
        textviewSettings.setText( strSettings );
    }





    private void setViews() {
        Button nextButton = findViewById(R.id.btnAdd);
        nextButton.setOnClickListener(onClick_button);
    }



    private View.OnClickListener onClick_button = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplication(), SubActivity.class);
            startActivity(intent);
        }
    };

/*
    // 追加ボタン
    public void onAddButtonClick(View view){

        EditText etTitle = findViewById(R.id.etTitle);
        etTitle.setText("new memo");
        EditText etNote = findViewById(R.id.etNote);
        etNote.setText("");
        btnSave.setEnabled(true);

        int save_select = 0;

    }

 */






}

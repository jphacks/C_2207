package com.example.testprog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;
import androidx.preference.PreferenceManager;
import android.content.SharedPreferences;
import android.widget.TextView;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;




public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Intent intent = new Intent(MainActivity.this,AsyncTask.class);
        //startActivity(intent);

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

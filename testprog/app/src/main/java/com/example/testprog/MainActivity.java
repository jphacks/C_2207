package com.example.testprog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.testprog.R;


public class MainActivity extends AppCompatActivity {

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


    }

    private void setViews() {
        Button nextButton = findViewById(R.id.textView);
        nextButton.setOnClickListener(onClick_button);
    }


    private View.OnClickListener onClick_button = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplication(), com.example.testprog.SubActivity.class);
            startActivity(intent);
        }
    };
}

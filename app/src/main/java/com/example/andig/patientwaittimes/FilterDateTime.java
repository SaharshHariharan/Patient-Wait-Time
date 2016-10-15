package com.example.andig.patientwaittimes;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

public class FilterDateTime extends AppCompatActivity {

    CalendarView calendar;
    String time;
    int Year;
    int Month;
    int Day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_date_time);

        calendar = (CalendarView) findViewById (R.id.calendar);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {


            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month , int dayOfMonth) {
                Toast.makeText(getApplicationContext(), dayOfMonth + "/" +  (month + 1) + "/" + year, Toast.LENGTH_LONG).show();
                Year = year;
                Month = month;
                Day = dayOfMonth;

            }


        });



    }
}
package com.example.andig.patientwaittimes;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.System.in;

public class FilterDateTime extends AppCompatActivity {

    CalendarView calendar;
    String Time;
    int Year;
    int Month;
    int Day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_date_time);

        calendar = (CalendarView) findViewById (R.id.ccalendar);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {


            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month , int dayOfMonth) {
                Toast.makeText(getApplicationContext(), dayOfMonth + "/" +  (month + 1) + "/" + year, Toast.LENGTH_LONG).show();
                Year = year;
                Month = month;
                Day = dayOfMonth;

            }


        });}

        private EditText time;
        //public void onSubmit (View view) {

           // time = (EditText) findViewById(R.id.editText);
            //Time = time.getText().toString();
           // System.out.println(Time);
       // }


}
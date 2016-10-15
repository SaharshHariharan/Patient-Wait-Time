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
import android.widget.TimePicker;

import com.example.andig.patientwaittimes.db.DML;

import java.text.ParseException;

import static com.example.andig.patientwaittimes.R.id.timePicker;
import static java.lang.System.in;

public class FilterDateTime extends AppCompatActivity {

    CalendarView calendar;

    int hour;
    int min;

    int Year;
    int Month;
    int Day;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_date_time);

        calendar = (CalendarView) findViewById(R.id.calendar);

    }

    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
        Toast.makeText(getApplicationContext(), dayOfMonth + "/" + (month + 1) + "/" + year, Toast.LENGTH_LONG).show();
        Year = year;
        Month = month;
        Day = dayOfMonth;
    }

    public void switcher(View view) {
        if (view.getId() == R.id.button2) {
            DML dml = new DML(getApplicationContext());
            TimePicker timePicker1;
            timePicker1 = (TimePicker) findViewById(R.id.timePicker);
            hour = timePicker1.getCurrentHour();
            min = timePicker1.getCurrentMinute();
            try {
                dml.addAppointment(Year, Month, Day, hour, min, 1, 1);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Intent intent = new Intent(FilterDateTime.this, LoginActivity.class);
            startActivity(intent);
        }
    }


}







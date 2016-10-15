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

    public void switcher (View view){
        if(view.getId() == R.id.button2){
            Intent intent = new Intent (FilterDateTime.this, DataApproved.class);

            TimePicker timePicker1;
            timePicker1 = (TimePicker) findViewById(R.id.timePicker);
            hour = timePicker1.getCurrentHour();
            min = timePicker1.getCurrentMinute();

            intent.putExtra("hour", hour);
            intent.putExtra("Year", Year);
            intent.putExtra("Day", Day);
            intent.putExtra("min", min);
            intent.putExtra("Month", Month);

            startActivity(intent);

        }
    }


}

package com.example.andig.patientwaittimes;

import android.app.Activity;
import android.app.Application;
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

public class FilterDateTime extends AppCompatActivity implements Application.ActivityLifecycleCallbacks{

    CalendarView calendar;

    int hour;
    int min;
    int ID;
    int Year;
    int Month;
    int Day;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_date_time);
        ID = getIntent().getIntExtra("ID", -1);

    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    public void onActivityResumed (Activity FilterDateTime){
        calendar = (CalendarView) findViewById(R.id.calendar);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(getApplicationContext(), dayOfMonth + "/" + (month + 1) + "/" + year, Toast.LENGTH_LONG).show();
                Year = year;
                Month = month;
                Day = dayOfMonth;
            }
        });
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    public void switcher (View view){
        if(view.getId() == R.id.button2){
            Intent intent = new Intent (FilterDateTime.this, DataApproved.class);

            TimePicker timePicker1;
            timePicker1 = (TimePicker) findViewById(R.id.timePicker);
            hour = timePicker1.getCurrentHour();
            min = timePicker1.getCurrentMinute();
            DML dml = new DML(getApplicationContext());
            try {
                dml.addAppointment(Year, Month, Day, hour, min, 1, ID);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            intent.putExtra("hour", hour);
            intent.putExtra("Year", Year);
            intent.putExtra("Day", Day);
            intent.putExtra("min", min);
            intent.putExtra("Month", Month);

            startActivity(intent);

        }
    }


}

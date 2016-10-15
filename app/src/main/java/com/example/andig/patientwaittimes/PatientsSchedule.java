package com.example.andig.patientwaittimes;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

public class PatientsSchedule extends AppCompatActivity {

    CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_schedule);


    calendar = (CalendarView) findViewById (R.id.calendar);
    calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener()){

        }
            @Override
                    public void onSelectedDayChange(CalendarView view, int year, );

        }

}

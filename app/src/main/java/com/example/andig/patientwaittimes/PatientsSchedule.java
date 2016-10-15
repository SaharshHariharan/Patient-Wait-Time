package com.example.andig.patientwaittimes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;
import android.widget.Toast;

public class PatientsSchedule extends AppCompatActivity {

    CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_schedule);


    calendar = (CalendarView) findViewById (R.id.calendar);
    calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {


        @Override
        public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
            Toast.makeText(getApplicationContext(), dayOfMonth + "/" +  month + "/" + year, Toast.LENGTH_LONG).show();
        }


    });

    }
}
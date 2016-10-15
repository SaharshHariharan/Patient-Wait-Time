package com.example.andig.patientwaittimes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

public class PatientsSchedule extends AppCompatActivity {

    CalendarView calendar;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_schedule);

        button = (Button)findViewById(R.id.button);
        calendar = (CalendarView) findViewById (R.id.calendar);

    }

    public void switcher (View view){
        Intent intent = new Intent (PatientsSchedule.this, FilterDateTime.class);
        startActivity(intent);
    }
}
package com.example.andig.patientwaittimes;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

public class PatientsSchedule extends AppCompatActivity {

    //CalendarView calendar;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_schedule);

        button = (Button)findViewById(R.id.button);
        //calendar = (CalendarView) findViewById (R.id.calendar);

    }

    public void switcher (View view){
        if (view.getId() == R.id.button); {
            Intent intent = new Intent (PatientsSchedule.this, FilterDateTime.class);
            startActivity(intent);
        }

    }





}


package com.example.andig.patientwaittimes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

public class PatientsSchedule extends AppCompatActivity {

    Button button;
    Integer ID;
    int Hour;
    int Year;
    int Month;
    int Min;
    int Day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_schedule);

        Hour = getIntent().getIntExtra("hour", -1);
        Year = getIntent().getIntExtra("Year", -1);
        Month = getIntent().getIntExtra("Month", -1);
        Min = getIntent().getIntExtra("min", -1);
        Day = getIntent().getIntExtra("Day", -1);
        ID = getIntent().getIntExtra("ID", -1);
        button = (Button)findViewById(R.id.button);
    }

    public void switcher (View view){
        Intent intent = new Intent (PatientsSchedule.this, FilterDateTime.class);
        intent.putExtra("ID", ID);
        startActivity(intent);
    }
}
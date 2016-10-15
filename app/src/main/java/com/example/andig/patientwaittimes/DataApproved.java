package com.example.andig.patientwaittimes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.TimePicker;

public class DataApproved extends AppCompatActivity {

    String AMPM = "AM";
    int Hour;
    int Year;
    int Month;
    int Min;
    int Day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_approved);

        Hour = getIntent().getIntExtra("hour", 0);
        Year = getIntent().getIntExtra("Year", 0);
        Month = getIntent().getIntExtra("Month", 0);
        Min = getIntent().getIntExtra("min", 0);
        Day = getIntent().getIntExtra("Day", 0);

        if (Hour > 12) {
            Hour = Hour - 12;
            AMPM = "PM";
        }

        TextView Time = (TextView) findViewById(R.id.textView3);
        TextView Date = (TextView) findViewById(R.id.textView8);


        if (Min < 10) {
            Time.setText(Hour + " :0" + Min + AMPM);
            Date.setText(Day + "/" + Month + "/" + Year);
        }else
            Time.setText(Hour + " :" + Min + AMPM);
        Date.setText(Day + "/" + Month + "/" + Year);}


    }



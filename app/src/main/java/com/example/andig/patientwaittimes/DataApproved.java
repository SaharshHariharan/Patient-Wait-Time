package com.example.andig.patientwaittimes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.TimePicker;

public class DataApproved extends AppCompatActivity {

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


        /*TextView tv = (TextView) findViewById(R.id.textView3);
        tv.setText("you have entered"+Hour+"as the integer");*/

    }
}


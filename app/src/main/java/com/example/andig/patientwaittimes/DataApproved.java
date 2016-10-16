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

        Hour = getIntent().getIntExtra("hour", -1);
        Year = getIntent().getIntExtra("Year", -1);
        Month = getIntent().getIntExtra("Month", -1);
        Min = getIntent().getIntExtra("min", -1);
        Day = getIntent().getIntExtra("Day", -1);

        if (Hour > 12) {
            Hour = Hour - 12;
            AMPM = "PM";
        }

        TextView Time = (TextView) findViewById(R.id.textView3);
        TextView Date = (TextView) findViewById(R.id.textView8);

        String hour = (Hour < 10 ? "0" : "") + Hour;
        String minute = (Min < 10 ? "0" : "") + Min;
        Time.setText(hour + ":" + minute);
        System.out.println(Day + "/" + Month + "/" + Year);
        Date.setText(Day + "/" + Month + "/" + Year);

    }


}



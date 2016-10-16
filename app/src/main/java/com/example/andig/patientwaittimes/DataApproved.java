package com.example.andig.patientwaittimes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.andig.patientwaittimes.db.DML;

import java.text.ParseException;

public class DataApproved extends AppCompatActivity {

    String AMPM = "AM";
    int Hour;
    int Year;
    int Month;
    int Min;
    int Day;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_approved);

        /*button = (Button) findViewById(R.id.button3);
        Hour = getIntent().getIntExtra("hour", -1);
        Year = getIntent().getIntExtra("Year", -1);
        Month = getIntent().getIntExtra("Month", -1);
        Min = getIntent().getIntExtra("min", -1);
        Day = getIntent().getIntExtra("Day", -1);*/

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

    public void switcher(View view) {
        if (view.getId() == R.id.button3) {
            Intent intent = new Intent(DataApproved.this, PatientsSchedule.class);
            intent.putExtra("hour", Hour);
            intent.putExtra("Year", Year);
            intent.putExtra("Day", Day);
            intent.putExtra("min", Min);
            intent.putExtra("Month", Month);
            startActivity(intent);
        }
    }
}



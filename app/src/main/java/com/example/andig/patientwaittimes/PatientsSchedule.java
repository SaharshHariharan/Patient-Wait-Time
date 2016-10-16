package com.example.andig.patientwaittimes;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;

import static java.lang.String.valueOf;

public class PatientsSchedule extends ListActivity {
    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ArrayList<String> listItems=new ArrayList<String>();

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<String> adapter;

    //RECORDING HOW MANY TIMES THE BUTTON HAS BEEN CLICKED
    int Hour;
    int Year;
    int Month;
    int Min;
    int Day;
    int ID;
    String Appointment;
    Button BButton;
    Button wait;


    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_patients_schedule);
        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        setListAdapter(adapter);
        Hour = getIntent().getIntExtra("hour", -1);
        Year = getIntent().getIntExtra("Year", -1);
        Month = getIntent().getIntExtra("Month", -1);
        Min = getIntent().getIntExtra("min", -1);
        Day = getIntent().getIntExtra("Day", -1);
        ID = getIntent().getIntExtra("ID", -1);
        BButton = (Button)findViewById(R.id.addBtn);
        wait = (Button)findViewById(R.id.wait);

        if (Hour < 0) {
            Appointment = "No Appointment Booked";
        } else {
            Appointment = valueOf(Month) + "." + valueOf(Day) + " " + valueOf(Year) + "-" + valueOf(Hour) + ":" + valueOf(Min);
        }

        listItems.add(Appointment);
        adapter.notifyDataSetChanged();

    }

    public void switcher (View view){
        if (view.getId() == R.id.addBtn) {
            Intent intent = new Intent (PatientsSchedule.this, FilterDateTime.class);
            startActivity(intent);
        }

        else if (view.getId() == R.id.wait) {
            Intent intent = new Intent (PatientsSchedule.this, Queue.class);
            intent.putExtra("Type", "patient");
            startActivity(intent);
        }
    }

    //METHOD WHICH WILL HANDLE DYNAMIC INSERTION

}


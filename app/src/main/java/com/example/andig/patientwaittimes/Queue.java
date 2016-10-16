package com.example.andig.patientwaittimes;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andig.patientwaittimes.R;

import java.util.ArrayList;
import java.util.Arrays;

public class Queue extends ListActivity {
    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ArrayList<String> listItems=new ArrayList<String>();

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<String> adapter;

    //RECORDING HOW MANY TIMES THE BUTTON HAS BEEN CLICKED
    int clickCounter=0;
    int number = 3;
    int ID;
    String [] Names = {"Tony", "Andi", "Saharsh", "Anthony", "Brandon", "Raj", "Leonard"};


    @Override
    public void onCreate(Bundle icicle) {
        ID = getIntent().getIntExtra("ID", -1);
        super.onCreate(icicle);
        setContentView(R.layout.activity_queue);
        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        setListAdapter(adapter);

        Intent intent = getIntent();
        String typeOfUser = intent.getStringExtra("Type");

        if (typeOfUser.equals("patient")){
            Button removeButton = (Button)findViewById(R.id.remBtn);
            removeButton.setVisibility(View.GONE);
            Button rButton = (Button)findViewById(R.id.addBtn);
            rButton.setVisibility(View.GONE);
            EditText editText = (EditText)findViewById(R.id.editText);
            editText.setVisibility(View.GONE);
        }
        else if (typeOfUser.equals("admin")){
            /*;*/
        }
    }

    //EditText editText = (EditText) findViewById(R.id.editText);

    //METHOD WHICH WILL HANDLE DYNAMIC INSERTION

    public void addItems(View v) {
        listItems.add(Names[clickCounter++]);
        adapter.notifyDataSetChanged();
    }

    public void removeItems (View view) {
        EditText editText = (EditText) findViewById(R.id.editText);
        String name = editText.getText().toString();
        number = Arrays.binarySearch(Names, name);
        listItems.remove(name);
        adapter.notifyDataSetChanged();
    }
    //listItems.remove(Names[clickCounter++]);
}
package com.example.andig.patientwaittimes.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.Arrays;

/**
 * Created by Andi Gu on 10/15/2016.
 */

public class PatientTableDML extends DatabaseOperator {
    private SQLiteDatabase database;

    public PatientTableDML(Context context) {
        super(context);
        database = open();
        fillData();
    }

    public void fillData() {
        database.beginTransaction();
        database.execSQL("INSERT INTO Doctor (name) VALUES ('Test doctor');");
        database.execSQL("INSERT INTO Patient (doctor_id, name, username, password) VALUES\n" +
                "  ((SELECT id FROM Doctor WHERE Doctor.name == 'Test doctor'),\n" +
                "    'Test patient',\n" +
                "    'test',\n" +
                "    'test');");
    }

    public Integer verifyUser(String username, String password) {
        String sql = "SELECT id FROM " + PATIENT_TABLE_NAME + " WHERE username == '" + username + "' AND password == '" + password + "';";
        System.out.println(sql);
        Cursor cursor = database.rawQuery(sql, null);
        System.out.println(cursor.getColumnCount());
        if (cursor.moveToFirst()) {
            return cursor.getInt(0);
        }
        cursor.close();
        return null;
    }

    public void endInteraction() {
        database.endTransaction();
    }
}

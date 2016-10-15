package com.example.andig.patientwaittimes.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.Arrays;

/**
 * Created by Andi Gu on 10/15/2016.
 */

public class PatientDML extends DatabaseOperator {
    private static SQLiteDatabase database;

    public PatientDML(Context context) {
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
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(0);
        }
        cursor.close();
        return null;
    }

    public static Integer getDoctorId(int id) {
        String sql = "SELECT doctor_id FROM " + PATIENT_TABLE_NAME + " WHERE id == " + id;
        Cursor cursor = database.rawQuery(sql, null);
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

package com.example.andig.patientwaittimes.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Andi Gu on 10/15/2016.
 */

public class PatientTableDML extends DatabaseHelper {
    public PatientTableDML(Context context) {
        super(context);
        fillData();
    }

    public void fillData() {
        try (SQLiteDatabase database = this.getWritableDatabase()) {
            database.execSQL("INSERT INTO Doctor (name) VALUES ('Test doctor');\n" +
                    "INSERT INTO Patient (doctor_id, name, username, password) VALUES\n" +
                    "  ((SELECT id FROM Doctor WHERE Doctor.name == 'Test doctor'),\n" +
                    "    'Test patient',\n" +
                    "    'test',\n" +
                    "    'test');" +
                    "");
            System.out.println("success");
        }
    }

    public Integer verifyUser(String username, String password) {
        String sql = "SELECT id FROM " + PATIENT_TABLE_NAME
                + " WHERE username == '" + username + "' AND password == '" + password + "';";
        System.out.println(sql);
        try (SQLiteDatabase database = this.getReadableDatabase()) {
            try (Cursor cursor = database.rawQuery(sql, null)) {
                if (cursor.moveToFirst()) {
                    return cursor.getInt(0);
                }
                return null;
            }
        }
    }
}

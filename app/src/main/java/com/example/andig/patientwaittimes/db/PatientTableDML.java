package com.example.andig.patientwaittimes.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Andi Gu on 10/15/2016.
 */

public class PatientTableDML extends DbOperator {
    public PatientTableDML(Context context) {
        super(context);
    }

    public Integer verifyUser(String username, String password) {
        String sql = "SELECT id FROM " + PATIENT_TABLE_NAME
                +" WHERE username == '" + username + "' AND password == '" + password + "';";
        System.out.println(sql);
        try (SQLiteDatabase database = this.getWritableDatabase(); Cursor cursor = database.rawQuery(sql, null);) {

            if (cursor.moveToFirst()) {
                return cursor.getInt(0);
            }
        }
        return null;
    }
}

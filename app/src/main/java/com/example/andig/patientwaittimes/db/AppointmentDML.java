package com.example.andig.patientwaittimes.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Andi Gu on 10/15/2016.
 */

public class AppointmentDML extends DatabaseOperator {
    private SQLiteDatabase database;

    public AppointmentDML(Context context) {
        super(context);
        database = open();
        fillData();
    }

    public void fillData() {
        database.beginTransaction();
        database.execSQL("INSERT INTO Appointment (start, end, doctor_id, patient_id) VALUES (CURRENT_TIME, CURRENT_TIME, 1, 1);");
    }

    public void addAppointment(int year, int month, int day, int hour, int minute, int patients, int patient_id) throws ParseException {
        Date date = new Date(year, month, day, hour, minute, 0);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, patients * 20);
        Date newTime = cal.getTime();
        SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-DD HH:mm");
        Integer doctorId = PatientDML.getDoctorId(patient_id);
        database.execSQL("INSERT INTO APPOINTMENT (start, end, patiend_id, doctor_id) VALUES" +
                "(" + df.format(date) + ", " + df.format(newTime) + ", " + patient_id + ", " + doctorId + ");");
    }

    public void getAvailableSlots(int year, int month, int day, int length) {

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

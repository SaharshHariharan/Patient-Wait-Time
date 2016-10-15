package com.example.andig.patientwaittimes.db;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class DML extends DatabaseOperator {
    private SQLiteDatabase database;

    public DML(Context context) {
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

    public Integer getDoctorId(int id) {
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

    public void addAppointment(int year, int month, int day, int hour, int minute, int patients, int patient_id) throws ParseException {
        Date date = new Date(year, month, day, hour, minute, 0);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, patients * 20);
        Date newTime = cal.getTime();
        SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-DD HH:mm");
        Integer doctorId = getDoctorId(patient_id);
        String sql = "INSERT INTO APPOINTMENT (start, end, patient_id, doctor_id) VALUES " +
                "('" + df.format(date) + "', '" + df.format(newTime) + "', " + patient_id + ", " + doctorId + ");";
        System.out.println(sql);
        database.execSQL(sql);
    }

    public void updateProgress(int appointmentId) {
        String sql = "UPDATE " + APPOINTMENT_TABLE_NAME + " SET in_progress = 'TRUE' WHERE id == " + appointmentId + ";";
        database.execSQL(sql);
    }

    public void updateApproved(int appointmentId) {
        String sql = "UPDATE " + APPOINTMENT_TABLE_NAME + " SET approved = 'TRUE' WHERE id == " + appointmentId + ";";
        database.execSQL(sql);
    }

    public void deleteAppointment(int appointmentId) {
        String sql = "DELETE FROM " + APPOINTMENT_TABLE_NAME + " WHERE id == " + appointmentId + ";";
        database.execSQL(sql);
    }

    public Integer getAppointmentId(String startTime) {
        String sql = "SELECT id FROM " + APPOINTMENT_TABLE_NAME + " WHERE start == '" + startTime + "';";
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(0);
        }
        cursor.close();
        return null;
    }

    public List<String[]> availableTimes(int year, int month, int day, int patientId, int patients) {
        String date = year + "-" + month + "-" + day;
        String sql = "SELECT TIME(START), TIME(END) FROM " + APPOINTMENT_TABLE_NAME + " WHERE (DATE(start) == " + date + " ORDER BY start;";
        Cursor cursor = database.rawQuery(sql, null);
        List<String[]> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            String end = cursor.getString(1);
            while (cursor.moveToNext()) {
                String newStart = cursor.getString(0);
                if (goodInterval(end, newStart, patients)) {
                    result.add(new String[] {end, newStart});
                }
            }
        }
        cursor.moveToNext();
        cursor.close();
        return result;
    }

    private boolean goodInterval(String endTime, String startTime, int patients) {
        int minimum = patients * 20 * 60;
        String[] endTokens = endTime.split(":");
        String[] startTokens = startTime.split(":");
        int end = Integer.parseInt(endTokens[0]) * 3600 + Integer.parseInt(endTokens[1]) * 60 +
                Integer.parseInt(endTokens[0]);
        int start = Integer.parseInt(startTokens[0]) * 3600 + Integer.parseInt(startTokens[1]) * 60 +
                Integer.parseInt(startTokens[0]);
        return start - end >= minimum;
    }
}

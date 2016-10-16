package com.example.andig.patientwaittimes.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class DML extends DatabaseOperator {
    private SQLiteDatabase database;

    public DML(Context context) {
        super(context);
        database = open();
        destroyAll(database);
        onCreate(database);
        fillData();
    }

    private void fillData() {
        database.execSQL("INSERT INTO Doctor (name, username, password) VALUES ('Test doctor', 'admin', 'admin');");
        database.execSQL("INSERT INTO Patient (doctor_id, name, username, password) VALUES\n" +
                "  ((SELECT id FROM Doctor WHERE Doctor.name == 'Test doctor'),\n" +
                "    'Test patient',\n" +
                "    'test',\n" +
                "    'test');");
        try {
            addAppointment(2000, 10, 10, 10, 10, 2, 1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

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

    public Integer verifyAdmin(String username, String password) {
        String sql = "SELECT id FROM " + DOCTOR_TABLE_NAME + " WHERE username == '" + username + "' AND password == '" + password + "';";
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(0);
        }
        cursor.close();
        return null;
    }

    private Integer getDoctorId(int id) {
        String sql = "SELECT doctor_id FROM " + PATIENT_TABLE_NAME + " WHERE id == " + id;
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(0);
        }
        cursor.close();

        return null;
    }

    public void close() {
        database.close();
    }

    public void addAppointment(int year, int month, int day, int hour, int minute, int patients, int patient_id) throws ParseException {
        Date date = new Date(year, month, day, hour, minute, 0);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, patients * 20);
        Date newTime = cal.getTime();
        Integer doctorId = getDoctorId(patient_id);
        String formattedDate = year + "-" + month + "-" + day + " " + hour + ":" + minute;
        String formattedNewDate = newTime.getYear() + "-";
        formattedNewDate += (newTime.getMonth() < 10 ? "0":"") + newTime.getMonth() + "-";
        formattedNewDate += (newTime.getDay() < 10 ? "0":"") + newTime.getDay() + " ";
        formattedNewDate += (newTime.getHours() < 10 ? "0":"") + newTime.getHours() + ":";
        formattedNewDate += (newTime.getMinutes() < 10 ? "0":"") + newTime.getMinutes();

        String sql = "INSERT INTO APPOINTMENT (start, end, patient_id, doctor_id) VALUES " +
                "('" + formattedDate + "', '" + formattedNewDate + "', " + patient_id + ", " + doctorId + ");";
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
        String sql = "SELECT TIME(START), TIME(END) FROM " + APPOINTMENT_TABLE_NAME + " WHERE (DATE(start) == '" + date + "') ORDER BY start;";
        System.out.println(sql);
        Cursor cursor = database.rawQuery(sql, null);
        List<String[]> result = new ArrayList<>();
        String[] hours = getDoctorHours(getDoctorId(patientId));
        String newStart = hours[0];
        String end;
        System.out.println(cursor.getColumnCount());
        if (cursor.moveToFirst()) {
            System.out.println(cursor.getString(0) + " " + cursor.getString(1));
            end = cursor.getString(1);
            System.out.println(Arrays.toString(cursor.getColumnNames()));
            if (goodInterval(end, newStart, patients)) {
                result.add(new String[] {end, newStart});
            }
            while (cursor.moveToNext()) {
                newStart = cursor.getString(0);
                if (goodInterval(end, newStart, patients)) {
                    result.add(new String[] {end, newStart});
                }
                end = cursor.getString(1);
            }
        }
        end = hours[1];
        if (goodInterval(end, newStart, patients)) {
            result.add(new String[] {end, newStart});
        }

        cursor.close();

        return result;
    }

    private String[] getDoctorHours(int doctorId) {
        String sql = "SELECT start, end FROM Doctor WHERE id == " + doctorId + ";";
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            return new String[] {cursor.getString(0), cursor.getString(1)};
        }
        cursor.close();

        return null;
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

    private void approve(Integer appointmentId) {
        String sql = "UPDATE TABLE " + APPOINTMENT_TABLE_NAME + "SET approved='True'" +
                " WHERE id == " + appointmentId;
        database.execSQL(sql);
    }

    private void inProgress(Integer appointmentId) {
        String sql = "UPDATE TABLE " + APPOINTMENT_TABLE_NAME + "SET in_progress ='True'" +
                " WHERE id == " + appointmentId;
        database.execSQL(sql);
    }


    public SQLiteDatabase open() {
        if (database != null) {
            database.close();
        }
        database = getWritableDatabase();
        return database;
    }

    public void reset() {
        destroyAll(database);
        onCreate(database);
        fillData();
    }

    public List<String> getAppointments(int patientId) {
        String sql = "SELECT * FROM " + APPOINTMENT_TABLE_NAME + " WHERE patient_id == " + patientId + " ORDER BY start";
        Cursor cursor = database.rawQuery(sql, null);
        List<String> result = new ArrayList<>();
        String temp;
        if (cursor.moveToFirst()) {
            temp = cursor.getString(cursor.getColumnIndex("start")) + " to " +  cursor.getString(cursor.getColumnIndex("end"));
            result.add(temp);
            while (cursor.moveToNext()) {
                temp = cursor.getString(cursor.getColumnIndex("start")) + " to " +  cursor.getString(cursor.getColumnIndex("end"));
                result.add(temp);
            }
        }
        cursor.close();
        return result;
    }
}

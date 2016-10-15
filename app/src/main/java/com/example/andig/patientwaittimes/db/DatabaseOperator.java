package com.example.andig.patientwaittimes.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Andi Gu on 10/15/2016.
 */

public class DatabaseOperator extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "test-db";
    private static final String DOCTOR_TABLE_NAME = "Doctor";
    static final String PATIENT_TABLE_NAME = "Patient";
    private static final String APPOINTMENT_TABLE_NAME = "Appointment";
    private static final String CREATE_DOCTOR_TABLE = "CREATE TABLE Doctor (\n" +
            "  id   INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "  name VARCHAR NOT NULL\n" +
            ");";
    private static final String CREATE_PATIENT_TABLE = "CREATE TABLE Patient (\n" +
            "  id        INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "  doctor_id INTEGER        ,\n" +
            "  name      VARCHAR        NOT NULL,\n" +
            "  username  VARCHAR UNIQUE NOT NULL,\n" +
            "  password  CHAR(20)       NOT NULL,\n" +
            "  FOREIGN KEY (doctor_id) REFERENCES Doctor (id)\n" +
            ");";
    private static final String CREATE_APPOINTMENT_TABLE = "CREATE TABLE Appointment (\n" +
            "  start       DATETIME NOT NULL,\n" +
            "  end         DATETIME NOT NULL,\n" +
            "  doctor_id   INTEGER  NOT NULL,\n" +
            "  patient_id  INTEGER  NOT NULL,\n" +
            "  in_progress BOOLEAN DEFAULT FALSE,\n" +
            "  approved    BOOLEAN DEFAULT FALSE,\n" +
            "  FOREIGN KEY (doctor_id) REFERENCES Doctor (id),\n" +
            "  FOREIGN KEY (patient_id) REFERENCES Patient (id)\n" +
            ");";

    public DatabaseOperator(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DOCTOR_TABLE);
        db.execSQL(CREATE_PATIENT_TABLE);
        db.execSQL(CREATE_APPOINTMENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public SQLiteDatabase open() {
        return getWritableDatabase();
    }
}

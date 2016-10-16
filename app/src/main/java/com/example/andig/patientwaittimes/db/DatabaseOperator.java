package com.example.andig.patientwaittimes.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOperator extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "test-db";
    static final String DOCTOR_TABLE_NAME = "Doctor";
    static final String PATIENT_TABLE_NAME = "Patient";
    static final String APPOINTMENT_TABLE_NAME = "Appointment";
    private static final String CREATE_DOCTOR_TABLE = "CREATE TABLE Doctor (\n" +
            "  id   INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "  username INTEGER NOT NULL, \n" +
            "  password CHAR(20) NOT NULL, \n" +
            "  name VARCHAR NOT NULL,\n" +
            "  start TIME DEFAULT '08:00',\n" +
            "  end TIME DEFAULT '16:00'\n" +
            ");";
    private static final String CREATE_PATIENT_TABLE = "CREATE TABLE Patient (\n" +
            "  id        INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "  doctor_id INTEGER        NOT NULL,\n" +
            "  name      VARCHAR        NOT NULL,\n" +
            "  username  VARCHAR UNIQUE NOT NULL,\n" +
            "  password  CHAR(20)       NOT NULL,\n" +
            "  FOREIGN KEY (doctor_id) REFERENCES Doctor (id)\n" +
            ");";
    private static final String CREATE_APPOINTMENT_TABLE = "CREATE TABLE Appointment (\n" +
            "  id          INTEGER PRIMARY KEY      AUTOINCREMENT,\n" +
            "  start       DATETIME NOT NULL DEFAULT '2000-01-01 00:01',\n" +
            "  end         DATETIME NOT NULL DEFAULT '2000-01-01 01:00',\n" +
            "  doctor_id   INTEGER  NOT NULL,\n" +
            "  patient_id  INTEGER  NOT NULL,\n" +
            "  in_progress BOOLEAN                  DEFAULT FALSE,\n" +
            "  approved    BOOLEAN                  DEFAULT FALSE,\n" +
            "  FOREIGN KEY (doctor_id) REFERENCES Doctor (id),\n" +
            "  FOREIGN KEY (patient_id) REFERENCES Patient (id)\n" +
            ");";

    public DatabaseOperator(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //destroyAll(database);
        //onCreate(database);
    }

    public DatabaseOperator() {
        super(null, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DOCTOR_TABLE);
        db.execSQL(CREATE_PATIENT_TABLE);
        db.execSQL(CREATE_APPOINTMENT_TABLE);

    }

    public void destroyAll(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + APPOINTMENT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PATIENT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DOCTOR_TABLE_NAME);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}

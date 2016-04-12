package com.ssru.mrsmile.testprojectappsolute.presenter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Suttichai on 4/12/2016.
 */
public class OpenHelper extends SQLiteOpenHelper {

    private static int VERSION = 1 ;
    private static String NAME_DATABASE = "DataBase.db";

    public static final String TABLE_FACULTY = "faculty";
    public static final String TABLE_DEPARTMENT = "department";
    public static final String TABLE_SUBJECT = "subject";
    public static final String TABLE_TIMETABLE = "timetable";

    public static final String FACULTY_ID = "faculty_id";
    public static final String FACULTY_NAME = "faculty_name";
    public static final String FACULTY_IMAGE = "faculty_image_file_name";

    public static final String DEPARTMENT_ID = "department_id";
    public static final String DEPARTMENT_NAME = "department_name";

    public static final String SUBJECT_ID = "subject_id";
    public static final String SUBJECT_NAME = "subject_name";
    public static final String SUBJECT_SECTION = "subject_section";

    public static final String TIMETABLE_ID = "_id";

    private static final String CREATE_TABLE_FACULTY = "CREATE TABLE IF NOT EXISTS " + TABLE_FACULTY
            + " ( "
            + FACULTY_ID + " VARCHAR(10) , "
            + FACULTY_NAME + " VARCHAR(50) , "
            + FACULTY_IMAGE + " VARCHAR(10) "
            + " ); ";

    private static final String CREATE_TABLE_DEPARTMENT = "CREATE TABLE IF NOT EXISTS " + TABLE_DEPARTMENT
            + " ( "
            + DEPARTMENT_ID + " VARCHAR(10) , "
            + DEPARTMENT_NAME + " VARCHAR(50) , "
            + FACULTY_ID + " VARCHAR(10) "
            + " ); ";

    private static final String CREATE_TABLE_SUBJECT = "CREATE TABLE IF NOT EXISTS " + TABLE_SUBJECT
            + " ( "
            + SUBJECT_ID + " VARCHAR(10) , "
            + SUBJECT_NAME + " VARCHAR(50) , "
            + SUBJECT_SECTION + " TEXT "
            + " ); ";

    private static final String CREATE_TABLE_TIMETABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_TIMETABLE
            + " ( "
            + TIMETABLE_ID + " INTEGER PRIMARY KEY , "
            + DEPARTMENT_ID + " VARCHAR(10)  , "
            + SUBJECT_ID + " VARCHAR(10)  "
            + " ); ";



    public OpenHelper(Context context) {
        super(context, NAME_DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FACULTY);
        db.execSQL(CREATE_TABLE_DEPARTMENT);
        db.execSQL(CREATE_TABLE_SUBJECT);
        db.execSQL(CREATE_TABLE_TIMETABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

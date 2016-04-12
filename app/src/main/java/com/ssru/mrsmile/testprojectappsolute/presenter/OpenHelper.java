package com.ssru.mrsmile.testprojectappsolute.presenter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Suttichai on 4/12/2016.
 */
public class OpenHelper extends SQLiteOpenHelper {

    private static int VERSION = 1 ;
    private static String NAME_DATABASE = "";

    public static final String TABLE_FACULTY = "faculty";
    public static final String TABLE_DEPARTMENT = "department";
    public static final String TABLE_SUBJECT = "subject";
    public static final String TABLE_TIMETABLE = "timetable";

    public static final String FACULTY_ID = "tf_id";
    public static final String FACULTY_NAME = "tf_faculty_name";
    public static final String FACULTY_IMAGE = "tf_image_file_name";

    public static final String DEPARTMENT_ID = "td_id";
    public static final String DEPARTMENT_NAME = "td_department_name";

    public static final String SUBJECT_ID = "ts_id";
    public static final String SUBJECT_NAME = "ts_subject_name";
    public static final String SUBJECT_SECTION = "ts_section";

    public static final String TIMETABLE_ID = "tt_id";

    private static final String CREATE_TABLE_FACULTY = "CREATE TABLE IF NOT EXISTS " + TABLE_FACULTY
            + " ( "
            + FACULTY_ID + " VARCHAR(5) PRIMARY KEY "
            + FACULTY_NAME + " VARCHAR(50) NOL NULL "
            + FACULTY_IMAGE + " VARCHAR(10) NOL NULL "
            + " ) ";

    private static final String CREATE_TABLE_DEPARTMENT = "CREATE TABLE IF NOT EXISTS " + TABLE_DEPARTMENT
            + " ( "
            + DEPARTMENT_ID + " VARCHAR(5) PRIMARY KEY , "
            + DEPARTMENT_NAME + " VARCHAR(50) NOL NULL , "
            + FACULTY_ID + " VARCHAR(5) NOL NULL , "
            + " ) ";

    private static final String CREATE_TABLE_SUBJECT = "CREATE TABLE IF NOT EXISTS " + TABLE_SUBJECT
            + " ( "
            + SUBJECT_ID + " VARCHAR(5) PRIMARY KEY , "
            + SUBJECT_NAME + " VARCHAR(50) NOL NULL , "
            + SUBJECT_SECTION + " TEXT NOL NULL "
            + " ) ";

    private static final String CREATE_TABLE_TIMETABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_TIMETABLE
            + " ( "
            + TIMETABLE_ID + " INT PRIMARY KEY AUTO_INCREMENT , "
            + DEPARTMENT_ID + " VARCHAR(5) NOL NULL , "
            + SUBJECT_ID + " VARCHAR(5) NOL NULL "
            + " ) ";



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

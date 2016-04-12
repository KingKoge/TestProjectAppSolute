package com.ssru.mrsmile.testprojectappsolute.presenter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ssru.mrsmile.testprojectappsolute.model.Department;
import com.ssru.mrsmile.testprojectappsolute.model.Faculty;
import com.ssru.mrsmile.testprojectappsolute.model.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suttichai on 4/12/2016.
 */
public class OpenHelperController {

    private OpenHelper helper;
    private static SQLiteDatabase readDataBase;
    private static SQLiteDatabase writeDataBase;

    public OpenHelperController (Context mContext){
        if(helper == null){
            helper = new OpenHelper(mContext);
        }

        readDataBase = helper.getReadableDatabase();
        writeDataBase = helper.getWritableDatabase();
    }

    public List<Faculty> findFacultyAll(){
        List<Faculty> faculties = new ArrayList<>();

        String sql;

        sql = "SELECT * FROM " + helper.TABLE_FACULTY;

        Cursor facultyResult = readDataBase.rawQuery(sql, null);

        if(facultyResult.moveToFirst()){

            do {

                List<Department> departments = new ArrayList<>();

                sql = "SELECT * FROM " + helper.TABLE_DEPARTMENT
                        + " WHERE " + helper.FACULTY_ID
                        + " = '" + facultyResult.getString(facultyResult.getColumnIndex(helper.FACULTY_ID)) + "'";

                Cursor departmentResult = readDataBase.rawQuery(sql, null);

                if (departmentResult.moveToFirst()){

                    do {

                        List<Subject> subjects = new ArrayList<>();

                        sql = "SELECT * FROM " + helper.TABLE_SUBJECT + " AS s"
                                + " JOIN " + helper.TABLE_TIMETABLE + " AS t "
                                + " NO ( s." + helper.SUBJECT_ID + " = t." + helper.SUBJECT_ID + " ) , "
                                + " JOIN " + helper.TABLE_DEPARTMENT + " AS d "
                                + " NO ( d." + helper.DEPARTMENT_ID + " = t." + helper.DEPARTMENT_ID + " ) "
                                + " WHERE " + helper.DEPARTMENT_ID
                                + " = 'd." + departmentResult.getString(departmentResult.getColumnIndex(helper.DEPARTMENT_ID)) + "'";

                        Cursor subjectResult = readDataBase.rawQuery(sql, null);

                        if (subjectResult.moveToFirst()) {
                            do {
                                subjects.add(new Subject(
                                        subjectResult.getString(subjectResult.getColumnIndex("s."+helper.SUBJECT_ID)) ,
                                        subjectResult.getString(subjectResult.getColumnIndex("s."+helper.SUBJECT_NAME)) ,
                                        subjectResult.getString(subjectResult.getColumnIndex("s."+helper.SUBJECT_SECTION))
                                ));
                            } while (subjectResult.moveToNext());
                        }

                        subjectResult.close();

                        departments.add(new Department(
                                departmentResult.getString(departmentResult.getColumnIndex(helper.DEPARTMENT_ID)) ,
                                departmentResult.getString(departmentResult.getColumnIndex(helper.DEPARTMENT_NAME)) ,
                                subjects
                        ));

                    } while (departmentResult.moveToNext());
                }

                departmentResult.close();

                faculties.add(new Faculty(
                        facultyResult.getString(facultyResult.getColumnIndex(helper.FACULTY_ID)) ,
                        facultyResult.getString(facultyResult.getColumnIndex(helper.FACULTY_NAME)) ,
                        facultyResult.getString(facultyResult.getColumnIndex(helper.FACULTY_IMAGE)) ,
                        departments
                ));

            } while (facultyResult.moveToNext());

        }

        facultyResult.close();

        return faculties;
    }

    public boolean isAddFaculty(){
        return false;
    }

    public boolean isAddDepartment(){
        return false;
    }

    public boolean isAddSubject(){
        return false;
    }

    public boolean isAddTimeTable(){
        return false;
    }
}

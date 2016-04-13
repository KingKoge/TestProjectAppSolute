package com.ssru.mrsmile.testprojectappsolute.presenter;

import android.content.ContentValues;
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
    private SQLiteDatabase readDataBase;
    private SQLiteDatabase writeDataBase;


    public OpenHelperController (Context mContext){
        if(helper == null){
            helper = new OpenHelper(mContext);
        }

        readDataBase =  helper.getReadableDatabase();
        writeDataBase = helper.getWritableDatabase();
    }

    public List<Faculty> findFacultyAll(){
        List<Faculty> faculties = new ArrayList<>();

        String sql;

        sql = "SELECT * FROM " + helper.TABLE_FACULTY;

        Cursor facultyResult = readDataBase.rawQuery(sql, null);

        if(facultyResult.moveToFirst()) {

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
                                + " ON s." + helper.SUBJECT_ID + " = t." + helper.SUBJECT_ID
                                + " JOIN " + helper.TABLE_DEPARTMENT + " AS d "
                                + " ON d." + helper.DEPARTMENT_ID + " = t." + helper.DEPARTMENT_ID
                                + " WHERE d." + helper.DEPARTMENT_ID
                                + " = '" + departmentResult.getString(departmentResult.getColumnIndex(helper.DEPARTMENT_ID)) + "'";

                        Cursor subjectResult = readDataBase.rawQuery(sql, null);

                        if (subjectResult.moveToFirst()) {
                            do {
                                subjects.add(new Subject(
                                        subjectResult.getString(subjectResult.getColumnIndex(helper.SUBJECT_ID)) ,
                                        subjectResult.getString(subjectResult.getColumnIndex(helper.SUBJECT_NAME)) ,
                                        subjectResult.getString(subjectResult.getColumnIndex(helper.SUBJECT_SECTION))
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

    public void saveData(List<Faculty> faculties){

        for (Faculty faculty : faculties){
            addFaculty(faculty);
            for (Department department : faculty.getDepartments()) {
                addDepartment(faculty.getFaculty_id() , department);
                for (Subject subject : department.getSubjects()) {
                    addSubject(subject);
                    addTimeTable(department.getDepartment_id(), subject.getSubject_id());
                }
            }
        }
    }

    public long addFaculty(Faculty faculty){

        ContentValues values = new ContentValues();
        values.put(helper.FACULTY_ID , faculty.getFaculty_id());
        values.put(helper.FACULTY_NAME , faculty.getFaculty_name());
        values.put(helper.FACULTY_IMAGE , faculty.getFaculty_image());

        return writeDataBase.insert(helper.TABLE_FACULTY , null , values);
    }

    private long addDepartment(String faculty_id ,Department department) {

        ContentValues values = new ContentValues();
        values.put(helper.DEPARTMENT_ID, department.getDepartment_id());
        values.put(helper.DEPARTMENT_NAME, department.getDepartment_name());
        values.put(helper.FACULTY_ID , faculty_id);

        return writeDataBase.insert(helper.TABLE_DEPARTMENT , null , values);
    }

    private long addSubject(Subject subject) {

        ContentValues values = new ContentValues();
        values.put(helper.SUBJECT_ID, subject.getSubject_id());
        values.put(helper.SUBJECT_NAME, subject.getSubject_name());
        values.put(helper.SUBJECT_SECTION, subject.getSubject_section());

        return writeDataBase.insert(helper.TABLE_SUBJECT, null, values);
    }

    private long addTimeTable(String departmentId , String subjectId) {

        ContentValues values = new ContentValues();
        values.put(helper.DEPARTMENT_ID, departmentId);
        values.put(helper.SUBJECT_ID, subjectId);

        return writeDataBase.insert(helper.TABLE_TIMETABLE, null, values);
    }

    public void deleteData(){
        String sqlDeleteValueTimeTable = "DELETE FROM " + helper.TABLE_TIMETABLE;
        String sqlDeleteValueSubject = "DELETE FROM " + helper.TABLE_SUBJECT;
        String sqlDeleteValueDepartment = "DELETE FROM " + helper.TABLE_DEPARTMENT;
        String sqlDeleteValueFaculty = "DELETE FROM " + helper.TABLE_FACULTY;

        writeDataBase.execSQL(sqlDeleteValueTimeTable);
        writeDataBase.execSQL(sqlDeleteValueSubject);
        writeDataBase.execSQL(sqlDeleteValueDepartment);
        writeDataBase.execSQL(sqlDeleteValueFaculty);
    }
}

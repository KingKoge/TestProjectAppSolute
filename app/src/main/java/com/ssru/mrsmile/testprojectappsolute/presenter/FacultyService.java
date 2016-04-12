package com.ssru.mrsmile.testprojectappsolute.presenter;

import com.ssru.mrsmile.testprojectappsolute.R;
import com.ssru.mrsmile.testprojectappsolute.model.Department;
import com.ssru.mrsmile.testprojectappsolute.model.Faculty;
import com.ssru.mrsmile.testprojectappsolute.model.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suttichai on 4/12/2016.
 */
public class FacultyService {

    private static FacultyService facultyService;
    private List<Faculty> faculties;

    private FacultyService(){

        Subject subject1 = new Subject("1", "sub1", "Monday 11.00 AM");
        Subject subject2 = new Subject("2", "sub2", "Monday 09.00 AM");
        Subject subject3 = new Subject("3", "sub3", "Monday 10.00 PM");
        Subject subject4 = new Subject("4", "sub4", "Monday 08.00 PM");

        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject1);
        subjects.add(subject2);
        subjects.add(subject3);
        subjects.add(subject4);


        Department department1 = new Department( "1" , "d1" , subjects);
        Department department2 = new Department( "2" , "d2" , subjects);
        Department department3 = new Department( "3" , "d3" , subjects);
        Department department4 = new Department( "4" , "d4" , subjects);

        List<Department> departments = new ArrayList<>();
        departments.add(department1);
        departments.add(department2);
        departments.add(department3);
        departments.add(department4);

        Faculty faculty1 = new Faculty("1", "f1", "f1.jpg", departments);
        Faculty faculty2 = new Faculty("2", "f2", "f2.jpg", departments);
        Faculty faculty3 = new Faculty("3", "f3", "f3.jpg", departments);
        Faculty faculty4 = new Faculty("4", "f4", "f4.jpg", departments);

        faculty1.setResourceImage(R.drawable.f1);
        faculty2.setResourceImage(R.drawable.f2);
        faculty3.setResourceImage(R.drawable.f3);
        faculty4.setResourceImage(R.drawable.f4);

        faculties = new ArrayList<>();
        faculties.add(faculty1);
        faculties.add(faculty2);
        faculties.add(faculty3);
        faculties.add(faculty4);
    }

    public static FacultyService getIntance(){
        if(facultyService == null){
            facultyService = new FacultyService();
        }
        return facultyService;
    }

    public Faculty getDepartment(int position){
        return faculties.get(position-1);
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }
}

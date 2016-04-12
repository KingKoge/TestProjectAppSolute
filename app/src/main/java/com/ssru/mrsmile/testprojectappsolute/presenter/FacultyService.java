package com.ssru.mrsmile.testprojectappsolute.presenter;

import com.ssru.mrsmile.testprojectappsolute.R;
import com.ssru.mrsmile.testprojectappsolute.model.Faculty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suttichai on 4/12/2016.
 */
public class FacultyService {

    private static FacultyService facultyService;
    private List<Faculty> faculties;

    private FacultyService() {

    }

    public static FacultyService getIntance(){
        if(facultyService == null){
            facultyService = new FacultyService();
        }
        return facultyService;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = new ArrayList<>();
        this.faculties.addAll(faculties);
        if (this.faculties.size() == 4) {
            this.faculties.get(0).setResourceImage(R.drawable.f1);
            this.faculties.get(1).setResourceImage(R.drawable.f2);
            this.faculties.get(2).setResourceImage(R.drawable.f3);
            this.faculties.get(3).setResourceImage(R.drawable.f4);
        }

    }

    public Faculty getFaculty (int position){
        return faculties.get(position-1);
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }
}

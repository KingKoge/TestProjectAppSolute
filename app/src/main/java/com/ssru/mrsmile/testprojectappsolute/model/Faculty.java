package com.ssru.mrsmile.testprojectappsolute.model;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.util.List;

/**
 * Created by Mr.Smile on 11/4/2559.
 */

@Parcel
public class Faculty {

    private String faculty_id ;
    private String faculty_name;
    private String faculty_image;
    private List<Department> departments;

    private int resourceImage;

    @ParcelConstructor
    public Faculty(String faculty_id, String faculty_name, String faculty_image, List<Department> departments) {
        this.faculty_id = faculty_id;
        this.faculty_name = faculty_name;
        this.faculty_image = faculty_image;
        this.departments = departments;
    }

    public String getFaculty_id() {
        return faculty_id;
    }

    public void setFaculty_id(String faculty_id) {
        this.faculty_id = faculty_id;
    }

    public String getFaculty_name() {
        return faculty_name;
    }

    public void setFaculty_name(String faculty_name) {
        this.faculty_name = faculty_name;
    }

    public String getFaculty_image() {
        return faculty_image;
    }

    public void setFaculty_image(String faculty_image) {
        this.faculty_image = faculty_image;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public int getResourceImage() {
        return resourceImage;
    }

    public void setResourceImage(int resourceImage) {
        this.resourceImage = resourceImage;
    }
}

package com.ssru.mrsmile.testprojectappsolute.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mr.Smile on 11/4/2559.
 */
public class Faculty {

    @SerializedName("tf_id")
    private String faculty_id ;
    @SerializedName("tf_faculty_name")
    private String faculty_name;
    @SerializedName("tf_image_file_name")
    private String faculty_image;
    @SerializedName("Department")
    private List<Department> departments;

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
}

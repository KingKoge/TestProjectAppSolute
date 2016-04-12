package com.ssru.mrsmile.testprojectappsolute.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.util.List;

/**
 * Created by Mr.Smile on 11/4/2559.
 */
@Parcel
public class Department {

    @SerializedName("td_id")
    private String department_id;
    @SerializedName("td_department_name")
    private String department_name ;
    @SerializedName("Subject")
    private List<Subject> subjects;

    @ParcelConstructor
    public Department(String department_id, String department_name, List<Subject> subjects) {
        this.department_id = department_id;
        this.department_name = department_name;
        this.subjects = subjects;
    }

    public String getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}

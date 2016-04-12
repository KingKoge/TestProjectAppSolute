package com.ssru.mrsmile.testprojectappsolute.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mr.Smile on 11/4/2559.
 */
public class Subject {

    @SerializedName("ts_id")
    private String subject_id;
    @SerializedName("ts_subject_name")
    private String subject_name;
    @SerializedName("ts_section")
    private String subject_section;

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getSubject_section() {
        return subject_section;
    }

    public void setSubject_section(String subject_section) {
        this.subject_section = subject_section;
    }
}

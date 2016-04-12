package com.ssru.mrsmile.testprojectappsolute.presenter;

import com.ssru.mrsmile.testprojectappsolute.model.Faculty;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Mr.Smile on 11/4/2559.
 */
public interface APIService {

    @Headers({
            "Content-Type : application/json",
            "Accept: application/json"
    })
    @POST("getFacultyHierarchy")
    Call<List<Faculty>> getFacultyHierarchy(@Body JSONObject body);
}

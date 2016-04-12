package com.ssru.mrsmile.testprojectappsolute.presenter;

import android.util.Log;
import com.ssru.mrsmile.testprojectappsolute.model.Faculty;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mr.Smile on 11/4/2559.
 */
public class APIConnection {

    public void getCall(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mobilemagic.de/cuisinetest/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(ClientBuilder.getIntance().getClient())
                .build();


        JSONObject body = new JSONObject();

        try {
            body.put("r_id", 4);
            body.put("l_id", 4);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        APIService service = retrofit.create(APIService.class);

        Call<List<Faculty>> call = service.getFacultyHierarchy(body);

        call.enqueue(new Callback<List<Faculty>>() {
            @Override
            public void onResponse(Call<List<Faculty>> call, Response<List<Faculty>> response) {
                Log.d("testApi", response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Faculty>> call, Throwable t) {
                Log.d("testApi", t.getMessage());
            }
        });
    }

}

package com.ssru.mrsmile.testprojectappsolute.presenter;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Mr.Smile on 11/4/2559.
 */
public class ConnectService extends AsyncTask<Void , Void , String> {

    private HttpURLConnection urlConnection;

    private static String TAG = "ConnectService";

    @Override
    protected String doInBackground(Void... params) {

        StringBuilder result = new StringBuilder();

        try {

            URL url = new URL("https://mobilemagic.de/cuisinetest/getFacultyHierarchy");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

            urlConnection.setRequestProperty("Content-Type" , "application/json");
            urlConnection.setRequestProperty("Accept" , "application/json");
            urlConnection.setRequestMethod("POST");

            JSONObject json = new JSONObject();
            json.put("r_id" , 4);
            json.put("l_id" , 4);

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(urlConnection.getOutputStream());
            Log.d(TAG, json.toString());
            outputStreamWriter.write(json.toString());
            outputStreamWriter.flush();

            int HttpStatus = urlConnection.getResponseCode();
            if (HttpURLConnection.HTTP_OK == HttpStatus) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                String line = null;
                while ((line = reader.readLine()) != null) {
                    Log.d(TAG, line);
                    result.append(line);
                }

                reader.close();
            }else {
                Log.d(TAG, urlConnection.getResponseMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result.toString();
    }
}

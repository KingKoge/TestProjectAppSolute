package com.ssru.mrsmile.testprojectappsolute.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ssru.mrsmile.testprojectappsolute.R;
import com.ssru.mrsmile.testprojectappsolute.presenter.APIConnection;


public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        APIConnection connection = new APIConnection();
        connection.getCall();
    }
}

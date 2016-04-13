package com.ssru.mrsmile.testprojectappsolute.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.ssru.mrsmile.testprojectappsolute.R;
import com.ssru.mrsmile.testprojectappsolute.presenter.APIConnectService;
import com.ssru.mrsmile.testprojectappsolute.presenter.DataBaseService;

public class SplashScreen extends Activity {

    private Handler handler;
    private Runnable runnable;
    private long delay_time;
    private long time = 3000L;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);
        handler = new Handler();

        if (isNetworkOnline()) {
            Log.d("checkNetwork" , "OnLine");
            APIConnectService service = new APIConnectService(getApplicationContext());
            service.execute();
        } else {
            DataBaseService dataBaseService = new DataBaseService(getApplicationContext());
            dataBaseService.execute();
            Log.d("checkNetwork" , "OffLine");
            Toast.makeText(getApplicationContext() , "ใช้งานโหมดออฟไลน์" , Toast.LENGTH_SHORT).show();
        }

        runnable = new Runnable() {
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
    }

    public boolean isNetworkOnline() {
        boolean status = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getNetworkInfo(0);
            if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                status = true;
            } else {
                netInfo = cm.getNetworkInfo(1);
                if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                    status = true;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return status;
    }

    public void onResume() {
        super.onResume();
        delay_time = time;
        handler.postDelayed(runnable, delay_time);
        time = System.currentTimeMillis();
    }

    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
        time = delay_time - (System.currentTimeMillis() - time);
    }
}

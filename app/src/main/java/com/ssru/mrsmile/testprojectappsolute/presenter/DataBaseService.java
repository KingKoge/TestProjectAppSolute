package com.ssru.mrsmile.testprojectappsolute.presenter;

import android.content.Context;
import android.os.AsyncTask;


/**
 * Created by Mr.Smile on 13/4/2559.
 */
public class DataBaseService extends AsyncTask<Void , Void , Void> {

    private OpenHelperController helperController;

    public DataBaseService(Context context){
        helperController = new OpenHelperController(context);
    }

    @Override
    protected Void doInBackground(Void... params) {
        FacultyService.getIntance().setFaculties(helperController.findFacultyAll());
        return null;
    }
}

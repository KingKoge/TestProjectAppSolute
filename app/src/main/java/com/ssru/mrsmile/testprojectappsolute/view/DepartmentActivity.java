package com.ssru.mrsmile.testprojectappsolute.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.ssru.mrsmile.testprojectappsolute.R;
import com.ssru.mrsmile.testprojectappsolute.model.Department;

import java.util.ArrayList;
import java.util.List;

public class DepartmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        int faculty_id = intent.getIntExtra("faculty_id" , -1);

        if (faculty_id != -1) {
            switch (faculty_id){
                case 1 :
                    break;
                case 2 :
                    break;
                case 3 :
                    break;
                case 4 :
                    break;
            }
        }

        List<Department> departments = new ArrayList<>();
        for (int i = 0 ; i < 10 ; i++) {
            departments.add(new Department(""+(i+1) , "de"+(i+1) , null));
        }


        AdapterView adapterView = new AdapterView(getApplicationContext(), departments);

        ListView listView = (ListView) findViewById(R.id.listViewDepartment);
        listView.setAdapter(adapterView);

    }
}

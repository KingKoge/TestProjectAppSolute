package com.ssru.mrsmile.testprojectappsolute.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import com.ssru.mrsmile.testprojectappsolute.R;
import com.ssru.mrsmile.testprojectappsolute.model.Faculty;
import com.ssru.mrsmile.testprojectappsolute.presenter.FacultyService;

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

        Faculty faculty = null;
        if (faculty_id != -1) {
            faculty = FacultyService.getIntance().getFaculty(faculty_id);
        }

        final AdapterViewDepartment adapterViewDepartment = new AdapterViewDepartment(getApplicationContext(), faculty);

        ListView listView = (ListView) findViewById(R.id.listViewDepartment);
        listView.setAdapter(adapterViewDepartment);

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                adapterViewDepartment.filter(s.toString());
            }
        };


        EditText inputText = (EditText) findViewById(R.id.inputText);

        inputText.addTextChangedListener(watcher);

    }
}

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
import com.ssru.mrsmile.testprojectappsolute.model.Department;

import org.parceler.Parcels;

public class SubjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        Department department = Parcels.unwrap(intent.getParcelableExtra("department"));

        final AdapterViewSubject adapterViewSubject = new AdapterViewSubject(getApplicationContext(), department);

        ListView listView = (ListView) findViewById(R.id.listViewDepartment);
        listView.setAdapter(adapterViewSubject);

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                adapterViewSubject.filter(s.toString());
            }
        };


        EditText inputText = (EditText) findViewById(R.id.inputText);

        inputText.addTextChangedListener(watcher);

    }


}

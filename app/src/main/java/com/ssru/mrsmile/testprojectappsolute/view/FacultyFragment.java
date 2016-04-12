package com.ssru.mrsmile.testprojectappsolute.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ssru.mrsmile.testprojectappsolute.R;

/**
 * Created by Mr.Smile on 10/4/2559.
 */
public class FacultyFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_faculty, container, false);

        ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rootView.getContext() ,ScrollingActivity.class );
                startActivity(intent);
            }
        });
        return rootView;
    }
}

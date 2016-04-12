package com.ssru.mrsmile.testprojectappsolute.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ssru.mrsmile.testprojectappsolute.R;

/**
 * Created by Mr.Smile on 10/4/2559.
 */
public class FacultyFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_faculty, container, false);

        ImageView imageViewF1 = (ImageView) rootView.findViewById(R.id.imageViewF1);
        imageViewF1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rootView.getContext() ,DepartmentActivity.class );
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("faculty_id" , 1);
                startActivity(intent);
            }
        });

        ImageView imageViewF2 = (ImageView) rootView.findViewById(R.id.imageViewF2);
        imageViewF2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rootView.getContext() ,DepartmentActivity.class );
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("faculty_id" , 2);
                startActivity(intent);
            }
        });

        ImageView imageViewF3 = (ImageView) rootView.findViewById(R.id.imageViewF3);
        imageViewF3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rootView.getContext() ,DepartmentActivity.class );
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("faculty_id" , 3);
                startActivity(intent);
            }
        });

        ImageView imageViewF4 = (ImageView) rootView.findViewById(R.id.imageViewF4);
        imageViewF4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rootView.getContext() ,DepartmentActivity.class );
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("faculty_id" , 4);
                startActivity(intent);
            }
        });
        return rootView;
    }
}

package com.ssru.mrsmile.testprojectappsolute.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ssru.mrsmile.testprojectappsolute.R;

/**
 * Created by Mr.Smile on 10/4/2559.
 */
public class FollowFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_follow, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText("Follow");
        return rootView;
    }
}

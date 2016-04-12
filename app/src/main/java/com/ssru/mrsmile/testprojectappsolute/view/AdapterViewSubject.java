package com.ssru.mrsmile.testprojectappsolute.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ssru.mrsmile.testprojectappsolute.R;
import com.ssru.mrsmile.testprojectappsolute.model.Department;
import com.ssru.mrsmile.testprojectappsolute.model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Suttichai on 4/12/2016.
 */
public class AdapterViewSubject extends BaseAdapter {


    private LayoutInflater inflater;
    private List<Subject> subjects = null;
    private List<Subject> tempSubjects = null;

    public AdapterViewSubject(Context mContext, Department department) {
        this.subjects = new ArrayList<>();
        this.subjects.addAll(department.getSubjects());
        this.tempSubjects = new ArrayList<>();
        this.tempSubjects.addAll(subjects);
        this.inflater = LayoutInflater.from(mContext);
    }

    public class ViewHolder{
        TextView textViewSubjectName , textViewSubjectSection;
    }

    @Override
    public int getCount() {
        return subjects.size();
    }

    @Override
    public Object getItem(int position) {
        return subjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.subject_list_view, null);

            holder.textViewSubjectName = (TextView) convertView.findViewById(R.id.textViewSubjectName);
            holder.textViewSubjectSection = (TextView) convertView.findViewById(R.id.textViewSubjectSection);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textViewSubjectName.setText(subjects.get(position).getSubject_name());
        holder.textViewSubjectSection.setText(subjects.get(position).getSubject_section());

        return convertView;
    }

    public void filter(String chatText) {

        subjects.clear();
        chatText = chatText.toLowerCase(Locale.getDefault());

        if (chatText.length() == 0) {
            subjects.addAll(tempSubjects);
            notifyDataSetChanged();
        }else {
            for (Subject subject : tempSubjects) {
                if (subject.getSubject_name().toLowerCase(Locale.getDefault()).contains(chatText)) {
                    subjects.add(subject);
                }
            }
            notifyDataSetChanged();
        }
    }
}

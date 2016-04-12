package com.ssru.mrsmile.testprojectappsolute.view;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ssru.mrsmile.testprojectappsolute.R;
import com.ssru.mrsmile.testprojectappsolute.model.Department;
import com.ssru.mrsmile.testprojectappsolute.model.Faculty;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Suttichai on 4/12/2016.
 */
public class AdapterViewDepartment extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<Department> departments = null;
    private List<Department> tempDepartments = null;
    private String imageName;
    private int imgId;

    public AdapterViewDepartment(Context mContext, Faculty faculty) {
        this.mContext = mContext;
        this.departments = new ArrayList<>();
        this.departments.addAll(faculty.getDepartments());
        this.tempDepartments = new ArrayList<>();
        this.tempDepartments.addAll(departments);
        this.inflater = LayoutInflater.from(mContext);
        this.imageName = faculty.getFaculty_image();
        this.imgId = faculty.getResourceImage();

        Log.e("AdapterViewDepartment", faculty.getFaculty_name());

    }

    public class ViewHolder{
        TextView textViewDepartment;
        ImageView imageView;
    }

    @Override
    public int getCount() {
        return departments.size();
    }

    @Override
    public Object getItem(int position) {
        return departments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

       if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.department_list_view, null);

            holder.textViewDepartment = (TextView) convertView.findViewById(R.id.textViewDepartment);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageViewDepartment);
            convertView.setTag(holder);
       } else {
            holder = (ViewHolder) convertView.getTag();
       }



        holder.textViewDepartment.setText(departments.get(position).getDepartment_name());
        //holder.imageView.setImageResource(getImgId(mContext, imageName));
        holder.imageView.setImageResource(imgId);

       convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, SubjectActivity.class);
                Parcelable wrapped = Parcels.wrap(departments.get(position));
                intent.putExtra("department",wrapped);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }

    public static int getImgId(Context mContext , String img) {
        img = img.trim();
        return mContext.getResources().getIdentifier(img , "drawable", mContext.getPackageName());
    }

    public void filter(String chatText) {

        departments.clear();
        chatText = chatText.toLowerCase(Locale.getDefault());

        if (chatText.length() == 0) {
            departments.addAll(tempDepartments);
            notifyDataSetChanged();
        }else {
            for (Department department : tempDepartments) {
                if (department.getDepartment_name().toLowerCase(Locale.getDefault()).contains(chatText)) {
                    departments.add(department);
                }
            }
            notifyDataSetChanged();
        }
    }
}

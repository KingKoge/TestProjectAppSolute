package com.ssru.mrsmile.testprojectappsolute.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ssru.mrsmile.testprojectappsolute.R;
import com.ssru.mrsmile.testprojectappsolute.model.Department;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Suttichai on 4/12/2016.
 */
public class AdapterView extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    private List<Department> departments = null;
    private ArrayList<Department> departmentList;

    public AdapterView(Context mContext, List<Department> departments) {
        this.mContext = mContext;
        this.departments = departments;
        this.inflater = LayoutInflater.from(mContext);
        this.departmentList = new ArrayList<>();
        this.departmentList.addAll(departments);
    }

    public class ViewHolder{
        TextView textViewDepartment;
        ImageView mapImage;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.department_list_view, null);

            holder.textViewDepartment = (TextView) convertView.findViewById(R.id.textViewDepartment);
            holder.mapImage = (ImageView) convertView.findViewById(R.id.imageViewDepartment);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.textViewDepartment.setText(departments.get(position).getDepartment_name());
        holder.mapImage.setImageResource(getImgId(mContext, "f1"));

    /*    convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, ShowMaps.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("_id", mapsList.get(position).getIdMap());
                mContext.startActivity(intent);
            }
        });*/
        return convertView;
    }

    public static int getImgId(Context mContext , String img) {
        img = img.trim();
        return mContext.getResources().getIdentifier("drawable/" + img, null, mContext.getPackageName());
    }

    /*public void filter(String chatText) {
        chatText = chatText.toLowerCase(Locale.getDefault());
        mapsList.clear();
        if (chatText.length() == 0) {
            mapsList.addAll(arrayList);
        }else {
            for (Maps maps : arrayList) {
                if (maps.getNameMap().toLowerCase(Locale.getDefault()).contains(chatText)) {
                    mapsList.add(maps);
                }

            }
            notifyDataSetChanged();
        }
    }*/
}

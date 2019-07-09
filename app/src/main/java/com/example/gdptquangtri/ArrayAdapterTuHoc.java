package com.example.gdptquangtri;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ArrayAdapterTuHoc extends ArrayAdapter<TuHocHuanLuyen> {
    public ArrayAdapterTuHoc(Context context, int resource, List<TuHocHuanLuyen> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.context_tuhoc, parent, false);
        }
        TextView ten = convertView.findViewById(R.id.txtTenTuHoc);
        ImageView img = convertView.findViewById(R.id.imgTuHoc);
        // get data item of position

        TuHocHuanLuyen tuHocHuanLuyen = getItem(position);
        //set data
        ten.setText(tuHocHuanLuyen.getTen());
        img.setImageResource(tuHocHuanLuyen.getImg());


        // imageView.setImageBitmap();


        return convertView;
    }
}

package com.example.gdptquangtri;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ArrayAdapterTinTucGDPT extends BaseAdapter {
    List<TinTucGDPT> gdptArrayList;
    Context context;
    DatabaseTinTuc db;
    private List<TinTucGDPT> arrayListDBTT;


    public ArrayAdapterTinTucGDPT(List<TinTucGDPT> gdptArrayList, Context context) {
        this.gdptArrayList = gdptArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return gdptArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return gdptArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.context_tintucgdpt, null);
        db = new DatabaseTinTuc(context);
        arrayListDBTT = new ArrayList<>();

        TextView title = convertView.findViewById(R.id.txtTitelGDPT);
        TextView pudata = convertView.findViewById(R.id.txtPubDateGDPT);
        ImageView img = convertView.findViewById(R.id.imgGDPT);

        // get data item of position

        TinTucGDPT tinTucGDPT = (TinTucGDPT) getItem(position);
        //set data


        if (ConnectionReceiver.isConnected()) {
            title.setText(tinTucGDPT.getTitle());
            pudata.setText(tinTucGDPT.getPubDate());


            Picasso.with(context)
                    .load(tinTucGDPT.getSrc())
                    .placeholder(R.drawable.ic_gdpt)
                    .into(img);


        } else {
            title.setText(tinTucGDPT.getTitle());
            pudata.setText(tinTucGDPT.getPubDate());

            byte[] hinhanh = tinTucGDPT.getHinhanh();
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
            img.setImageBitmap(bitmap);

        }

        // imageView.setImageBitmap();


        return convertView;
    }

}

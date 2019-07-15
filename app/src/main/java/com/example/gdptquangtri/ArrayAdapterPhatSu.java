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

public class ArrayAdapterPhatSu extends BaseAdapter {
    List<NewsPhatSu> psArrayList;
    Context context;
    DatabaseTinTuc db;
    private List<NewsPhatSu> listDBPS;
    public ArrayAdapterPhatSu(Context context, List<NewsPhatSu> objects) {
        this.psArrayList = objects;
        this.context = context;
    }

    @Override
    public int getCount() {
        return psArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return psArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.context_tintucps, null);
        listDBPS = new ArrayList<>();
        db = new DatabaseTinTuc(context);
        TextView title = convertView.findViewById(R.id.txtTitelPS);
        TextView pudata = convertView.findViewById(R.id.txtPubDatePS);
        ImageView img = convertView.findViewById(R.id.imgPhatsu);
        // get data item of position

        NewsPhatSu newsPhatSu = (NewsPhatSu) getItem(position);
        //set data

        if (ConnectionReceiver.isConnected() == true) {
            title.setText(newsPhatSu.getTitle());
            pudata.setText(newsPhatSu.getPubDate());
            Picasso.with(context)
                    .load(newsPhatSu.getSrc())
                    .placeholder(R.drawable.ic_gdpt)
                    .into(img);


        } else {
            title.setText(newsPhatSu.getTitle());
            pudata.setText(newsPhatSu.getPubDate());
            byte[] hinhanh = newsPhatSu.getHinhanh();
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
            img.setImageBitmap(bitmap);

        }

        // imageView.setImageBitmap();


        return convertView;
    }
}

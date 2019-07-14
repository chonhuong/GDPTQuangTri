package com.example.gdptquangtri;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
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
        title.setText(tinTucGDPT.getTitle());
        pudata.setText(tinTucGDPT.getPubDate());
        if (ConnectionReceiver.isConnected()) {
            Picasso.with(context)
                    .load(tinTucGDPT.getSrc())
                    .placeholder(R.drawable.ic_gdpt)
                    .fit()
                    .into(img);


            BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
            Bitmap bitmap = bitmapDrawable.getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] hinhanh = stream.toByteArray();

            String pubDate = tinTucGDPT.getPubDate();
            String link = tinTucGDPT.getLink();
            String tieuDe = tinTucGDPT.getTitle();


            arrayListDBTT = db.getAllGDPT();
            if (arrayListDBTT.size() < 1) {
                long id = db.insertGDPT(tieuDe, link, pubDate, hinhanh);
                TinTucGDPT tinTucGDPT1 = db.getGDPT(id);
                arrayListDBTT.add(0, tinTucGDPT1);
            }

            int k = 0;
            for (int j = 0; j < arrayListDBTT.size(); j++) {
                arrayListDBTT = db.getAllGDPT();
                TinTucGDPT tinTucGDPT1 = arrayListDBTT.get(j);

                if (tinTucGDPT1.getTitle().equalsIgnoreCase(tieuDe)) {
                    k++;
                }
            }

            if (k == 0) {
                db.insertGDPT(tieuDe, link, pubDate, hinhanh);
            }

        } else {
            byte[] hinhanh = tinTucGDPT.getHinhanh();
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
            img.setImageBitmap(bitmap);

        }

        // imageView.setImageBitmap();


        return convertView;
    }
}

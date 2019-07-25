package com.example.gdptquangtri;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ViewPagerAdapterTinTucGDPT extends PagerAdapter {
    private LayoutInflater inflater;
    String link = "";
    private List<TinTucGDPT> arrayList;
    private Context context;

    public ViewPagerAdapterTinTucGDPT(Context mContext, List<TinTucGDPT> arrayList1) {
        this.context = mContext;
        inflater = LayoutInflater.from(mContext);
        arrayList = arrayList1;

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view.equals(o);
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View itemView = inflater.inflate(R.layout.viewpager_titucgdpt, container, false);
        FrameLayout frameLayout = itemView.findViewById(R.id.frame_ViewPager1);
        ImageView imageView = itemView.findViewById(R.id.imgviewPagerGDPT);
        TextView title = itemView.findViewById(R.id.titleViewPagerGDPT);


        TinTucGDPT tinTucGDPT = arrayList.get(position);
        link = tinTucGDPT.getLink();
        String src = tinTucGDPT.getSrc();

        title.setText(tinTucGDPT.getTitle());

        Picasso.with(context)
                .load(src)
                .placeholder(R.drawable.ic_gdpt)
                .into(imageView);


        container.addView(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ViewNewsPhatSu.class);
                intent.putExtra("link", link);
                intent.putExtra("title", "Tin tức GĐPT");
                context.startActivity(intent);
            }
        });


//        imageView.setImageDrawable (container.getResources().getDrawable(mResources[position]));

        return itemView;


    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

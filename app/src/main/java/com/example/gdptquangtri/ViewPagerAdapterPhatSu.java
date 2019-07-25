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

public class ViewPagerAdapterPhatSu extends PagerAdapter {
    private LayoutInflater inflater;
    String link = "";
    private List<NewsPhatSu> arrayList;
    private Context context;

    public ViewPagerAdapterPhatSu(Context mContext, List<NewsPhatSu> arrayList1) {
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

        View itemView = inflater.inflate(R.layout.viewpager_phatsu, container, false);
        FrameLayout frameLayout = itemView.findViewById(R.id.frame_ViewPager);
        ImageView imageView = itemView.findViewById(R.id.imgviewPagerPS);
        TextView title = itemView.findViewById(R.id.titleViewPagerPS);


        NewsPhatSu newsPhatSu = arrayList.get(position);
        link = newsPhatSu.getLink();
        String src = newsPhatSu.getSrc();

        title.setText(newsPhatSu.getTitle());
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
                intent.putExtra("title", "Tin tức Phật Sự");
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

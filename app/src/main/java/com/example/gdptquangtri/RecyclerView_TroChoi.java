package com.example.gdptquangtri;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RecyclerView_TroChoi {
    private Context mContext;
    private TrochoiAdapter trochoiAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<TroChoi> troChois, List<String> keys) {
        mContext = context;
        trochoiAdapter = new TrochoiAdapter(troChois, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(trochoiAdapter);


    }

    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    class TroChoiItemView extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView ten;
        private TextView pubdate;


        private String key;

        private ItemClickListener itemClickListener;

        public TroChoiItemView(View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.txt_TenTroChoi);
            pubdate = itemView.findViewById(R.id.txt_pubdateTroChoi);
            itemView.setOnClickListener(this);

        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }


        public void bind(String key) {


            this.key = key;
        }


        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition());
        }
    }

    class TrochoiAdapter extends RecyclerView.Adapter<TroChoiItemView> {
        private List<TroChoi> troChoiArrayList;
        private List<String> keys;

        public TrochoiAdapter(List<TroChoi> trochoi, List<String> key) {
            this.troChoiArrayList = trochoi;
            this.keys = key;
        }

        @NonNull
        @Override
        public TroChoiItemView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(mContext);

            View itemView = inflater.inflate(R.layout.trochoi_list_item, viewGroup, false);
            return new TroChoiItemView(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull TroChoiItemView troChoiItemView, int i) {
            final TroChoi troChoi = troChoiArrayList.get(i);
            troChoiItemView.bind(keys.get(i));
            troChoiItemView.ten.setText(troChoi.getTen());
            troChoiItemView.pubdate.setText(troChoi.getPubDate());
            troChoiItemView.setItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int position) {
                    Toast.makeText(mContext, " " + troChoi.getNoiDung(), Toast.LENGTH_SHORT).show();
                }
            });

        }

        @Override
        public int getItemCount() {
            return troChoiArrayList.size();
        }
    }
}

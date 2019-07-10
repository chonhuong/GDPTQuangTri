package com.example.gdptquangtri;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

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

    class TroChoiItemView extends RecyclerView.ViewHolder {
        private TextView ten;
        private TextView pubdate;

        private String key;


        public TroChoiItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.trochoi_list_item, parent, false));
            ten = itemView.findViewById(R.id.txt_TenTroChoi);
            pubdate = itemView.findViewById(R.id.txt_pubdateTroChoi);


        }

        public void bind(TroChoi troChoi, String key) {
            ten.setText(troChoi.getTen());
            pubdate.setText(troChoi.getPubdate());
            this.key = key;
        }
    }

    class TrochoiAdapter extends RecyclerView.Adapter<TroChoiItemView> {
        private List<TroChoi> troChoiArrayList;
        private List<String> key;

        public TrochoiAdapter(List<TroChoi> trochoi, List<String> key) {
            this.troChoiArrayList = trochoi;
            this.key = key;
        }

        @NonNull
        @Override
        public TroChoiItemView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new TroChoiItemView(viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull TroChoiItemView troChoiItemView, int i) {
            troChoiItemView.bind(troChoiArrayList.get(i), key.get(i));
        }

        @Override
        public int getItemCount() {
            return troChoiArrayList.size();
        }
    }
}

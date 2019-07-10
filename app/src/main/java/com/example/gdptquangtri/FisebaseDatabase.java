package com.example.gdptquangtri;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FisebaseDatabase {
    private List<TroChoi> troChoiArrayList = new ArrayList<>();
    private FirebaseDatabase database;
    private DatabaseReference mReferenceTroChoi;


    public FisebaseDatabase() {
        database = FirebaseDatabase.getInstance();
        mReferenceTroChoi = database.getReference("TroChoi");
    }

    public void readTroChoi(final DataStatus dataStatus) {
        mReferenceTroChoi.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                troChoiArrayList.clear();
                List<String> key = new ArrayList<>();
                for (DataSnapshot keynode : dataSnapshot.getChildren()) {
                    key.add(keynode.getKey());
                    // TroChoi troChoi = keynode.getValue(TroChoi.class);
                    String ten = keynode.getValue(TroChoi.class).getTen();
                    String pubdate = keynode.getValue(TroChoi.class).getPubDate();
                    String noidung = keynode.getValue(TroChoi.class).getNoiDung();
                    troChoiArrayList.add(new TroChoi(pubdate, noidung, ten));
                }
                dataStatus.DataIsLoaded(troChoiArrayList, key);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public interface DataStatus {
        void DataIsLoaded(List<TroChoi> troChois, List<String> key);

    }


}

package com.example.gdptquangtri;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class FragmentTroChoi extends Fragment {
    private RecyclerView lv_trochoi;
    private ArrayAdapter adapter;
    private View view;
    private List<TroChoi> troChoiArrayList;
    private FirebaseDatabase database;
    private DatabaseReference mReferenceTroChoi;
    ProgressDialog myProgress;
    private Button btnAdd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_trochoi, container, false);
        lv_trochoi = view.findViewById(R.id.recycler_TroChoi);
        setHasOptionsMenu(true);
        if (ConnectionReceiver.isConnected() == true) {
            myProgress = new ProgressDialog(getActivity());
            myProgress.setTitle("Đang tải dữ liệu ...");
            myProgress.setMessage("Vui lòng chờ...");
            myProgress.setCancelable(true);
            //Hiển thị Progress Bar
            myProgress.show();
            new FisebaseDatabase().readTroChoi(new FisebaseDatabase.DataStatus() {
                @Override
                public void DataIsLoaded(List<TroChoi> troChois, List<String> key) {
                    myProgress.dismiss();
                    new RecyclerView_TroChoi().setConfig(lv_trochoi, getActivity(), troChois, key);

                }

                @Override
                public void DataIsInserted() {

                }

                @Override
                public void DataIsUpdate() {

                }

                @Override
                public void DataIsDelete() {

                }

            });


        } else {
            Toast.makeText(getActivity(), "Không có kết nối mạng", Toast.LENGTH_LONG).show();
            view = inflater.inflate(R.layout.kiemtrainternet, container, false);

        }
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_listtrochoi, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Menu_addTrochoi:
                Intent intent = new Intent(getActivity(), AddTroChoi.class);

                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

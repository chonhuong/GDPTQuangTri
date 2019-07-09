package com.example.gdptquangtri;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class FragmentTroChoi extends Fragment {
    private ListView lv_trochoi;
    private ArrayAdapter adapter;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_trochoi, container, false);
        lv_trochoi = view.findViewById(R.id.listview_TroChoi);
        if (ConnectionReceiver.isConnected() == true) {

        } else {
            Toast.makeText(getActivity(), "Không có kết nối mạng", Toast.LENGTH_LONG).show();
            view = inflater.inflate(R.layout.kiemtrainternet, container, false);

        }
        return view;
    }
}

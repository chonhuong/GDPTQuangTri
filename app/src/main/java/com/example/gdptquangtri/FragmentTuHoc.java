package com.example.gdptquangtri;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class FragmentTuHoc extends Fragment implements AdapterView.OnItemClickListener {
    private GridView gvTuHoc;
    private ArrayList<TuHocHuanLuyen> arrayTuHoc;

    private ArrayAdapterTuHoc adapterTuHoc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tuhoc, container, false);
        gvTuHoc = view.findViewById(R.id.gridviewTuhoc);
        arrayTuHoc = new ArrayList<>();
        arrayTuHoc.add(new TuHocHuanLuyen(R.drawable.tuhoc_momat, "BẬC MỞ MẮT"));
        arrayTuHoc.add(new TuHocHuanLuyen(R.drawable.tuhoc_canhmem, "BẬC CÁNH MỀM"));
        arrayTuHoc.add(new TuHocHuanLuyen(R.drawable.tuhoc_chancung, "BẬC CHÂN CỨNG"));
        arrayTuHoc.add(new TuHocHuanLuyen(R.drawable.tuhoc_tungbay, "BẬC TUNG BAY"));

        arrayTuHoc.add(new TuHocHuanLuyen(R.drawable.tuhoc_huongthien, "BẬC HƯỚNG THIỆN"));
        arrayTuHoc.add(new TuHocHuanLuyen(R.drawable.tuhoc_sothien, "BẬC SƠ THIỆN"));
        arrayTuHoc.add(new TuHocHuanLuyen(R.drawable.tuhoc_trungthien, "BẬC TRUNG THIỆN"));
        arrayTuHoc.add(new TuHocHuanLuyen(R.drawable.tuhoc_chanhthien, "BẬC CHÁNH THIỆN"));


        arrayTuHoc.add(new TuHocHuanLuyen(R.drawable.tuhoc_hoa, "BẬC HÒA"));
        arrayTuHoc.add(new TuHocHuanLuyen(R.drawable.tuhoc_minh, "BẬC MINH"));
        arrayTuHoc.add(new TuHocHuanLuyen(R.drawable.tuhoc_kiens, "BẬC KIẾN"));
        arrayTuHoc.add(new TuHocHuanLuyen(R.drawable.tuhoc_truc, "BẬC TRỰC"));


        arrayTuHoc.add(new TuHocHuanLuyen(R.drawable.tuhoc_kien_lu, "BẬC KIÊN-LỘC UYỂN"));
        arrayTuHoc.add(new TuHocHuanLuyen(R.drawable.tuhoc_tri_ad, "BẬC TRÌ-A DỤC"));
        arrayTuHoc.add(new TuHocHuanLuyen(R.drawable.tuhoc_dinh_ht, "BẬC ĐỊNH-HUYỂN TRANG"));
        arrayTuHoc.add(new TuHocHuanLuyen(R.drawable.tuhoc_luc_vh, "BẬC LỰC-VẠN HẠNH"));
        adapterTuHoc = new ArrayAdapterTuHoc(getActivity(), R.layout.context_tuhoc, arrayTuHoc);
        gvTuHoc.setAdapter(adapterTuHoc);

        gvTuHoc.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), arrayTuHoc.get(position).getTen(), Toast.LENGTH_SHORT).show();
    }
}

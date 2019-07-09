package com.example.gdptquangtri;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FragmentNewsGDPT extends Fragment {
    ProgressDialog myProgress;
    ViewPager viewPager;
    private ArrayList<TinTucGDPT> gdptArrayList;
    private ViewPagerAdapterTinTucGDPT viewPagerAdapterTinTucGDPT;
    private ListView listViewGDPT;
    private ArrayAdapterTinTucGDPT adapterTinTucGDPT;
    private ArrayList<TinTucGDPT> arrayNewssGDPT;
    private DatabaseTinTuc db;
    private List<TinTucGDPT> arrayListDBTT;
    private List<TinTucGDPT> arrayListVPDBTT;
    private String url = "https://gdptquangtri.vn/category/tin-tuc/tin-gia-dinh-phat-tu/feed";
    private boolean isOnline;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newsgdpt, container, false);
        listViewGDPT = view.findViewById(R.id.listviewNewsGDPT);
        viewPager = view.findViewById(R.id.viewPagerGDPT);
        arrayNewssGDPT = new ArrayList<>();
        gdptArrayList = new ArrayList<>();
        myProgress = new ProgressDialog(getActivity());
        db = new DatabaseTinTuc(getActivity());
        arrayListDBTT = new ArrayList<>();
        isOnline = ConnectionReceiver.isConnected();

        if (isOnline == true) {
            myProgress.setTitle("Đang tải dữ liệu ...");
            myProgress.setMessage("Vui lòng chờ...");
            myProgress.setCancelable(true);
            //Hiển thị Progress Bar
            myProgress.show();
            new ReadGDPT().execute(url);
        } else {
            Toast.makeText(getActivity(), "Không có kết nối mạng", Toast.LENGTH_LONG).show();

            arrayListDBTT = db.getAllGDPT();

            adapterTinTucGDPT = new ArrayAdapterTinTucGDPT(arrayListDBTT, getActivity());
            listViewGDPT.setAdapter(adapterTinTucGDPT);
            listViewGDPT.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TinTucGDPT tinTucGDPT = arrayListDBTT.get(position);

                    Intent intent = new Intent(getActivity(), KiemTraInternetGDPT.class);
                    intent.putExtra("title", "Tin tức GĐPT");
                    startActivity(intent);
                }
            });
            arrayListVPDBTT = db.getAllVPGDPT();
            viewPagerAdapterTinTucGDPT = new ViewPagerAdapterTinTucGDPT(getActivity(), arrayListVPDBTT);
            viewPager.setAdapter(viewPagerAdapterTinTucGDPT);

        }

        return view;
    }

    private class ReadGDPT extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... strings) {

            //--------------------------------------
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                }
                bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return content.toString();

        }

        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);

            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);

            NodeList nodeList = document.getElementsByTagName("item");
            NodeList nodeListdescription = document.getElementsByTagName("content:encoded");

            String hinhanh = "";
            String pubDate = "";
            String link = "";
            String tieuDe = "";

            for (int i = 0; i < nodeList.getLength(); i++) {
                String cdata = nodeListdescription.item(i).getTextContent();

                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher matcher = p.matcher(cdata);

                if (matcher.find()) {
                    hinhanh = matcher.group(1);


                }
                Element element = (Element) nodeList.item(i);
                // hinhanh=parser.getValue(element,"img");
                tieuDe = parser.getValue(element, "title");
                link = parser.getValue(element, "link");
                pubDate = parser.getValue(element, "pubDate");


                if (i == 0) {
                    gdptArrayList.add(new TinTucGDPT(tieuDe, link, hinhanh, pubDate));
                    arrayListVPDBTT = db.getAllVPGDPT();
                    if (arrayListVPDBTT.size() < 1) {
                        long id = db.insertVPGDPT(new TinTucGDPT(tieuDe, link, hinhanh, pubDate));
                        TinTucGDPT tinTucGDPT = db.getVPGDPT(id);
                        arrayListVPDBTT.add(0, tinTucGDPT);
                    }

                    int k = 0;
                    for (int j = 0; j < 1; j++) {
                        TinTucGDPT tinTucGDPT = arrayListVPDBTT.get(j);

                        if (tinTucGDPT.getTitle().equalsIgnoreCase(tieuDe)) {
                            k++;
                        }
                    }

                    if (k == 0) {
                        db.insertVPGDPT(new TinTucGDPT(tieuDe, link, hinhanh, pubDate));
                    }
                } else {
                    arrayNewssGDPT.add(new TinTucGDPT(tieuDe, link, hinhanh, pubDate));
                    arrayListDBTT = db.getAllGDPT();
                    if (arrayListDBTT.size() < 1) {
                        long id = db.insertGDPT(new TinTucGDPT(tieuDe, link, hinhanh, pubDate));
                        TinTucGDPT tinTucGDPT = db.getGDPT(id);
                        arrayListDBTT.add(0, tinTucGDPT);
                    }

                    int k = 0;
                    for (int j = 0; j < arrayListDBTT.size(); j++) {
                        TinTucGDPT tinTucGDPT = arrayListDBTT.get(j);

                        if (tinTucGDPT.getTitle().equalsIgnoreCase(tieuDe)) {
                            k++;
                        }
                    }

                    if (k == 0) {
                        db.insertGDPT(new TinTucGDPT(tieuDe, link, hinhanh, pubDate));
                    }
                }


            }

            viewPagerAdapterTinTucGDPT = new ViewPagerAdapterTinTucGDPT(getActivity(), gdptArrayList);
            viewPager.setAdapter(viewPagerAdapterTinTucGDPT);
            viewPager.setOffscreenPageLimit(2);
            adapterTinTucGDPT = new ArrayAdapterTinTucGDPT(arrayNewssGDPT, getActivity());
            listViewGDPT.setAdapter(adapterTinTucGDPT);
            listViewGDPT.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TinTucGDPT tinTucGDPT = arrayNewssGDPT.get(position);

                    Intent intent = new Intent(getActivity(), ViewNewsPhatSu.class);
                    intent.putExtra("link", tinTucGDPT.getLink());
                    startActivity(intent);
                }
            });
            myProgress.dismiss();
            adapterTinTucGDPT.notifyDataSetChanged();
            viewPagerAdapterTinTucGDPT.notifyDataSetChanged();


            // Toast.makeText(,tieuDe,Toast.LENGTH_SHORT).show();
        }
    }


}

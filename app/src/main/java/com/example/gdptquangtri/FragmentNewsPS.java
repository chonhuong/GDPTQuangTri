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


public class FragmentNewsPS extends Fragment {
    ProgressDialog myProgress;
    // private MainActivity mainActivity;
    private ListView lvPhatSu;
    private ArrayList<NewsPhatSu> arrayPhatSu;
    private ArrayAdapterPhatSu adapterPS;
    private ViewPager viewPager;
    private ViewPagerAdapterPhatSu viewPagerAdapterPhatSu;
    private ArrayList<NewsPhatSu> arrayViewPagerPhatSu;
    private List<NewsPhatSu> listDBPS;
    private DatabaseTinTuc db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tintucps, container, false);
        myProgress = new ProgressDialog(getActivity());


        //------------------------------------------
        lvPhatSu = view.findViewById(R.id.listviewPS);
        viewPager = view.findViewById(R.id.viewPagerPhatSu);
        arrayPhatSu = new ArrayList<>();
        arrayViewPagerPhatSu = new ArrayList<>();
        db = new DatabaseTinTuc(getActivity());
        listDBPS = new ArrayList<>();
        if (ConnectionReceiver.isConnected() == true) {
            myProgress.setTitle("Đang tải dữ liệu ...");
            myProgress.setMessage("Vui lòng chờ...");
            myProgress.setCancelable(true);
            //Hiển thị Progress Bar
            myProgress.show();
            new ReadRSSphatSu().execute("http://phatgiaoquangtri.com/rss/");
            //view pager
//
            viewPagerAdapterPhatSu = new ViewPagerAdapterPhatSu(getActivity(), arrayViewPagerPhatSu);
            viewPager.setAdapter(viewPagerAdapterPhatSu);
            viewPager.setOffscreenPageLimit(2);
            //------------------------------------------
            adapterPS = new ArrayAdapterPhatSu(getActivity(), arrayPhatSu);

            lvPhatSu.setAdapter(adapterPS);

            lvPhatSu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    NewsPhatSu newsPhatSu = arrayPhatSu.get(position);

                    Intent intent = new Intent(getActivity(), ViewNewsPhatSu.class);
                    intent.putExtra("link", newsPhatSu.getLink());
                    startActivity(intent);
                }
            });

        } else {
            Toast.makeText(getActivity(), "Không có kết nối mạng", Toast.LENGTH_LONG).show();
            listDBPS = db.getAllPS();
            adapterPS = new ArrayAdapterPhatSu(getActivity(), listDBPS);

            lvPhatSu.setAdapter(adapterPS);

            lvPhatSu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    NewsPhatSu newsPhatSu = listDBPS.get(position);

                    Intent intent = new Intent(getActivity(), KiemTraInternetGDPT.class);
                    intent.putExtra("title", "Tin tức Phật Sự");
                    startActivity(intent);

                }
            });

        }


        return view;

    }


    private class ReadRSSphatSu extends AsyncTask<String, Integer, String> {
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
            NodeList nodeListdescription = document.getElementsByTagName("description");

            String hinhanh = "";
            String pubDate = "";
            String link = "";
            String tieuDe = "";

            for (int i = 0; i < nodeList.getLength(); i++) {
                String cdata = nodeListdescription.item(i + 1).getTextContent();

                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher matcher = p.matcher(cdata);

                if (matcher.find()) {
                    hinhanh = matcher.group(1);


                }
                Element element = (Element) nodeList.item(i);

                tieuDe = parser.getValue(element, "title");
                link = parser.getValue(element, "link");
                pubDate = parser.getValue(element, "pubDate");


                if (i == 0) {
                    // arrayPhatSu.add(new NewsPhatSu(tieuDe, link,hinhanh, pubDate));
                    arrayViewPagerPhatSu.add(new NewsPhatSu(tieuDe, link, hinhanh, pubDate));
                } else {
                    arrayPhatSu.add(new NewsPhatSu(tieuDe, link, hinhanh, pubDate));

                }


            }

            myProgress.dismiss();
            adapterPS.notifyDataSetChanged();
            viewPagerAdapterPhatSu.notifyDataSetChanged();


            // Toast.makeText(,tieuDe,Toast.LENGTH_SHORT).show();
        }
    }


}

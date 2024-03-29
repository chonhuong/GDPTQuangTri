package com.example.gdptquangtri;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
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
    private ArrayAdapterPhatSuOff adapterPSOff;
    private ViewPager viewPager;
    private ViewPagerAdapterPhatSu viewPagerAdapterPhatSu;
    private ViewPagerAdapterPhatSuOff viewPagerAdapterPhatSuOff;
    private ArrayList<NewsPhatSu> arrayViewPagerPhatSu;
    private List<NewsPhatSu> listDBPS;
    private List<NewsPhatSu> listVPDBPS;
    private DatabaseTinTuc db;
    private ImageView img;
    private String url = "http://phatgiaoquangtri.com/rss";

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
        img = new ImageView(getActivity());
        if (ConnectionReceiver.isConnected() == true) {
            myProgress.setTitle("Đang tải dữ liệu ...");
            myProgress.setMessage("Vui lòng chờ...");
            myProgress.setCancelable(true);
            //Hiển thị Progress Bar
            myProgress.show();
            new ReadRSSphatSu().execute(url);
            //view pager
//
            viewPagerAdapterPhatSu = new ViewPagerAdapterPhatSu(getActivity(), arrayViewPagerPhatSu);
            viewPager.setAdapter(viewPagerAdapterPhatSu);

            //------------------------------------------
            adapterPS = new ArrayAdapterPhatSu(getActivity(), arrayPhatSu);

            lvPhatSu.setAdapter(adapterPS);

            lvPhatSu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    NewsPhatSu newsPhatSu = arrayPhatSu.get(position);

                    Intent intent = new Intent(getActivity(), ViewNewsPhatSu.class);
                    intent.putExtra("link", newsPhatSu.getLink());
                    intent.putExtra("title", "Tin tức Phật Sự");
                    startActivity(intent);
                }
            });

        } else {
            Toast.makeText(getActivity(), "Không có kết nối mạng", Toast.LENGTH_LONG).show();
            listDBPS = db.getAllPS();
            listVPDBPS = db.getAllVPPS();
            adapterPSOff = new ArrayAdapterPhatSuOff(getActivity(), listDBPS);

            lvPhatSu.setAdapter(adapterPSOff);

            lvPhatSu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    NewsPhatSu newsPhatSu = listDBPS.get(position);
                    if (ConnectionReceiver.isConnected() == true) {
                        Intent intent = new Intent(getActivity(), ViewNewsPhatSu.class);
                        intent.putExtra("link", newsPhatSu.getLink());
                        intent.putExtra("title", "Tin tức Phật Sự");

                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getActivity(), KiemTraInternetGDPT.class);
                        intent.putExtra("title", "Tin tức Phật Sự");
                        startActivity(intent);
                    }

                }
            });
            viewPagerAdapterPhatSuOff = new ViewPagerAdapterPhatSuOff(getActivity(), listVPDBPS);
            viewPager.setAdapter(viewPagerAdapterPhatSuOff);

        }


        return view;

    }

    //----------------------------------------------------------------------------------------------
    public void insertPS(String tieuDe, String link, String pubDate, ImageView img) {
        //  ImageView img = view.findViewById(R.id.imgGDPT);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] hinhanh = stream.toByteArray();


//        listDBPS = db.getAllPS();
//        if (listDBPS.size() < 1) {
//            long id = db.insertPS(tieuDe, link, pubDate, hinhanh);
//            NewsPhatSu newsPhatSu1 = db.getPS(id);
//
//            listDBPS.add(0, newsPhatSu1);
//        }
//
//        int k = 0;
//        for (int j = 0; j < listDBPS.size(); j++) {
//            listDBPS = db.getAllPS();
//            NewsPhatSu newsPhatSu1 = listDBPS.get(j);
//
//            if (newsPhatSu1.getTitle().equalsIgnoreCase(tieuDe)) {
//                k++;
//            }
//        }
//
//        if (k == 0) {
        db.insertPS(tieuDe, link, pubDate, hinhanh);
        //}
    }

    //-------------------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------
    public void insertVPPS(String tieuDe, String link, String pubDate, ImageView img) {
        //  ImageView img = view.findViewById(R.id.imgGDPT);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] hinhanh = stream.toByteArray();


//        listVPDBPS = db.getAllVPPS();
//        if (listVPDBPS.size() < 1) {
//            long id = db.insertVPPS(tieuDe, link, pubDate, hinhanh);
//            NewsPhatSu newsPhatSu1 = db.getVPPS(id);
//
//            listVPDBPS.add(0, newsPhatSu1);
//        } else {
//
//            int k = 0;
//            listVPDBPS = db.getAllVPPS();
//            for (int j = 0; j < listVPDBPS.size(); j++) {
//
//                NewsPhatSu newsPhatSu1 = listVPDBPS.get(j);
//
//                if (newsPhatSu1.getTitle().equalsIgnoreCase(tieuDe)) {
//
//                    k++;
//                }
//                if (k == 0) {
//                    db.deleteVPGDPT(newsPhatSu1.getTitle());
        db.insertVPPS(tieuDe, link, pubDate, hinhanh);
//                }
//
//            }
//
//        }
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


                    listVPDBPS = db.getAllVPPS();
                    if (listVPDBPS.size() < 1) {
                        new DownloadImageTaskVP(img, pubDate, link, tieuDe).execute(hinhanh);
                    } else {

                        int k = 0;
                        listVPDBPS = db.getAllVPPS();


                        NewsPhatSu newsPhatSu1 = listVPDBPS.get(0);

                        if (newsPhatSu1.getTitle().equalsIgnoreCase(tieuDe)) {

                            k++;
                        } else {
                            db.deleteVPPS(newsPhatSu1.getTitle());
                            new DownloadImageTaskVP(img, pubDate, link, tieuDe).execute(hinhanh);
                        }


                    }
                } else {
                    arrayPhatSu.add(new NewsPhatSu(tieuDe, link, hinhanh, pubDate));
                    listDBPS = db.getAllPS();
                    if (listDBPS.size() < 1) {
                        new DownloadImageTask(img, pubDate, link, tieuDe).execute(hinhanh);
                    } else {
                        int k = 0;
                        for (int j = 0; j < listDBPS.size(); j++) {
                            listDBPS = db.getAllPS();
                            NewsPhatSu newsPhatSu1 = listDBPS.get(j);

                            if (newsPhatSu1.getTitle().equalsIgnoreCase(tieuDe)) {
                                k++;
                            }
                        }

                        if (k == 0) {
                            new DownloadImageTask(img, pubDate, link, tieuDe).execute(hinhanh);
                        }
                    }
                }


            }

            myProgress.dismiss();
            adapterPS.notifyDataSetChanged();
            viewPagerAdapterPhatSu.notifyDataSetChanged();


            // Toast.makeText(,tieuDe,Toast.LENGTH_SHORT).show();
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        String pubDate;
        String link1;
        String tieuDe;

        public DownloadImageTask(ImageView bmImage, String pubDate, String link1, String tieuDe) {
            this.bmImage = bmImage;
            this.pubDate = pubDate;
            this.link1 = link1;
            this.tieuDe = tieuDe;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
            insertPS(tieuDe, link1, pubDate, bmImage);

        }
    }

    private class DownloadImageTaskVP extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        String pubDate;
        String link1;
        String tieuDe;

        public DownloadImageTaskVP(ImageView bmImage, String pubDate, String link1, String tieuDe) {
            this.bmImage = bmImage;
            this.pubDate = pubDate;
            this.link1 = link1;
            this.tieuDe = tieuDe;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
            insertVPPS(tieuDe, link1, pubDate, bmImage);

        }
    }
}

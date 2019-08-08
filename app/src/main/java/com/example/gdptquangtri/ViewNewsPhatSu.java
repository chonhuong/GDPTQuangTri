package com.example.gdptquangtri;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ViewNewsPhatSu extends AppCompatActivity {
    WebView webView;
    TextView textview;
    RelativeLayout.LayoutParams layoutparams;
    private ActionBar actionbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_news_phat_su);
        webView = findViewById(R.id.webViewPS);
        Intent intent = getIntent();
        String ten = intent.getStringExtra("title");
        String link = intent.getStringExtra("link");

        ActionBarTitleGravity(ten);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(link);
        webView.setWebViewClient(new WebViewClient());
        //  Toast.makeText(this,link,Toast.LENGTH_SHORT).show();
    }

    private void ActionBarTitleGravity(String title) {
        // TODO Auto-generated method stub

        actionbar = getSupportActionBar();

        textview = new TextView(getApplicationContext());

        layoutparams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        textview.setLayoutParams(layoutparams);

        textview.setText(title);

        textview.setTextColor(Color.WHITE);

        textview.setGravity(Gravity.CENTER);

        textview.setTextSize(20);


        actionbar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        actionbar.setCustomView(textview);

    }
}

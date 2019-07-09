package com.example.gdptquangtri;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ViewNewsPhatSu extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_news_phat_su);
        webView = findViewById(R.id.webViewPS);
        Intent intent = getIntent();
        String link = intent.getStringExtra("link");

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(link);
        webView.setWebViewClient(new WebViewClient());
        //  Toast.makeText(this,link,Toast.LENGTH_SHORT).show();
    }


}

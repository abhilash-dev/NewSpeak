package com.example.abhi.newspeak.NyTimes.TopStories.Technology;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.abhi.newspeak.R;

public class WebViewActivity extends AppCompatActivity {

    WebView webView;
    private String fullNewsUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = (WebView) findViewById(R.id.full_news_display_webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        Bundle bundle = getIntent().getExtras();
        fullNewsUrl = bundle.getString("fullNewsUrl");
        webView.loadUrl(fullNewsUrl);
    }
}

package com.neeral.mystanford;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MyWebView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_web_view);

        final Intent i = getIntent();
        final int urlId = i.getIntExtra("url", R.string.google);

        final WebView wv = (WebView) findViewById(R.id.my_webview);
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        if (urlId == R.string.url_campus_map || urlId == R.string.url_bus_map)
            wv.getSettings().setJavaScriptEnabled(true);

        String url = getString(urlId);

        if (urlId == R.string.url_bus_schedule_prefix)
            url = url + i.getStringExtra("route").trim().toLowerCase();


        Log.i("MyWebView loading url ", url);
        wv.loadUrl(url);
    }
}

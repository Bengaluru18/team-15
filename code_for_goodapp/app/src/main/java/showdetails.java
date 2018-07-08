package com.example.shubhamkumar.code_for_good;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class showdetails extends AppCompatActivity {

    String open;
    WebView wb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdetails);
        open="https://wayne-enterprises.000webhostapp.com/codeforgood/tableshow.php";
        wb=(WebView)findViewById(R.id.mywebview);
        wb.getSettings().setJavaScriptEnabled(true);
        wb.setWebViewClient(new WebViewClient());
        wb.getSettings().setBuiltInZoomControls(true);
        wb.getSettings().setSupportZoom(true);
        wb.loadUrl(open);

    }
}

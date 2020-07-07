package com.example.covid19helper;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SelfAssessmentActivity extends AppCompatActivity {
    private  WebView webView;
    private Toolbar toolbar;
    Button btnView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_assessment);

        //btnView = findViewById(R.id.btnView);
//        btnView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openBrowserWebView();
//            }
//        });
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Self-Assessment");

        //openBrowserWithOptions();
        //openBrowser();
        openBrowserWebView();
    }

    private void openBrowserWebView() {
        webView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        //webView.loadUrl("https://www.google.co.in/");
        webView.loadUrl("https://ca.thrive.health/covid19/en");
        webView.setWebViewClient(new WebViewClient());
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    private void openBrowser() {
        WebView webView = new WebView(this);
        setContentView(webView);
        webView.loadUrl("https://www.google.co.in/");
        //webView.loadUrl("https://ca.thrive.health/covid19/en");
    }

    private void openBrowserWithOptions(){
        Intent i=new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://ca.thrive.health/covid19/en"));
        Intent chooser = i.createChooser(i,"Open website using...");
        if (i.resolveActivity(getPackageManager())!=null)
            startActivity(chooser);
    }
}
package com.example.covid19helper;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelfAssessmentFragment extends Fragment {
    private  WebView webView;
    private Toolbar toolbar;
    Button btnView;
    public SelfAssessmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_self_assessment, container, false);
        //openBrowserWithOptions();
        //openBrowser();
        webView = (WebView) (v.findViewById(R.id.webView));
        openBrowserWebView();
        return v;
    }

    private void openBrowserWebView() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        //webView.loadUrl("https://www.google.co.in/");
        webView.loadUrl("https://ca.thrive.health/covid19/en");
        webView.setWebViewClient(new WebViewClient());
    }
    /*
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
    }*/
}

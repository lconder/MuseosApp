package com.lconde.museosapp.Activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.lconde.museosapp.R;



public class webActivity extends AppCompatActivity
{

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Bundle extras = getIntent().getExtras();

        if (Build.VERSION.SDK_INT < 19) {
            FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
            statusBar.setVisibility(View.GONE);
        }

        mWebView = (WebView) this.findViewById(R.id.webView);
        // Activamos Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // Url que carga la app (webview)
        mWebView.loadUrl(extras.getString("url"));
        // Forzamos el webview para que abra los enlaces internos dentro de la la APP
        mWebView.setWebViewClient(new WebViewClient());
        // Forzamos el webview para que abra los enlaces externos en el navegador
        //mWebView.setWebViewClient(new MyAppWebViewClient());
        //myWebView.loadUrl("http://www.google.com/");

    }

}

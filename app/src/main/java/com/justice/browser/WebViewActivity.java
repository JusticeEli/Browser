package com.justice.browser;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity implements View.OnClickListener {
    private int position;
    private ImageView imageViewLogo, imageViewBackArrow, imageViewForwardArrow;
    private WebView webView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        position = getIntent().getIntExtra("position", 0);

        initWidgets();
        initWebView();
        setOnClickListeners();


    }

    private void setOnClickListeners() {
        imageViewBackArrow.setOnClickListener(this);
        imageViewForwardArrow.setOnClickListener(this);
    }

    private void initWebView() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        progressBar.setMax(100);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if(newProgress==100){
                    progressBar.setVisibility(View.GONE);
                }else{
                    progressBar.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                getSupportActionBar().setTitle(title);
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
                imageViewLogo.setImageBitmap(icon);
            }
        });
        webView.loadUrl(ApplicationClass.choosenWebsitesList.get(position).getUrl());
    }

    private void initWidgets() {

        imageViewLogo = findViewById(R.id.imageViewLogo);
        imageViewBackArrow = findViewById(R.id.imageViewBackArrow);
        imageViewForwardArrow = findViewById(R.id.imageViewForwardArrow);


        webView = findViewById(R.id.webView);
        progressBar = findViewById(R.id.progressBar);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageViewBackArrow:
                onBackPressed();

                break;
            case R.id.imageViewForwardArrow:
                onForwardPressed();
                break;
        }

    }

    private void onForwardPressed() {
        if (webView.canGoForward()) {
            webView.goForward();
        } else {
            Toast.makeText(this, "Cant Go forward!!", Toast.LENGTH_SHORT).show();
        }
    }
}

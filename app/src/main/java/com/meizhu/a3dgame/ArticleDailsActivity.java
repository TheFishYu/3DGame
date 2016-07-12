package com.meizhu.a3dgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class ArticleDailsActivity extends AppCompatActivity {

    WebView webView;
    Button btn1,btn2;WebSettings webSettings;
    String articleId ;
    String articleTypeId;
    String urlStr ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_dails);
        webView = (WebView) findViewById(R.id.webview);
        webSettings = webView.getSettings();
        webSettings.setBuiltInZoomControls(true);


        Intent intent = getIntent();
        articleId = intent.getStringExtra("articleId");
        articleTypeId = intent.getStringExtra("articleTypeId");
        urlStr = "http://www.3dmgame.com/sitemap/api.php?id="+articleId+"&typeid="+articleTypeId;
        Log.i("aaa",urlStr);
        webView.loadUrl(urlStr);

        webView.setWebViewClient(new WebViewClient() {
            //覆盖url的加载
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
}

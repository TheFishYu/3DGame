package com.meizhu.a3dgame.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.meizhu.a3dgame.R;

/**
 * Created by Kun Yu on 2016/7/8.
 */
public class ForumFragment extends Fragment {

    WebView webView;
    WebSettings webSettings;
    String urlStr = "http://bbs.3dmgame.com/forum.php";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.forum_fragment, null);
        webView = (WebView) view.findViewById(R.id.webview);
        webView = (WebView) view.findViewById(R.id.webview);
        webSettings = webView.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webView.loadUrl(urlStr);

        webView.setWebViewClient(new WebViewClient() {
            //覆盖url的加载
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        return view;
    }
}

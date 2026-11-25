package com.grain.retrofilm;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebChromeClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.webkit.WebViewAssetLoader;
import androidx.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webView = new WebView(this);

        WebViewAssetLoader assetLoader =
                new WebViewAssetLoader.Builder()
                        .addPathHandler(
                                "/assets/",
                                new WebViewAssetLoader.AssetsPathHandler(this)
                        )
                        .build();

        webView.setWebViewClient(
                new WebViewClient() {
                    @Override
                    public android.webkit.WebResourceResponse shouldInterceptRequest(
                            WebView view,
                            android.webkit.WebResourceRequest request
                    ) {
                        return assetLoader.shouldInterceptRequest(
                                request.getUrl()
                        );
                    }
                }
        );

        webView.setWebChromeClient(new WebChromeClient());

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);

        setContentView(webView);

        webView.loadUrl(
                "https://appassets.androidplatform.net/assets/index.html"
        );
    }
}

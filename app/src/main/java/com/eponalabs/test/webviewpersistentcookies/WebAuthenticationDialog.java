package com.eponalabs.test.webviewpersistentcookies;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

@SuppressLint("SetJavaScriptEnabled")
class WebAuthenticationDialog extends Dialog
{
    private static final String TAG = "AuthenticationDebugger";
    private String _url;

    WebAuthenticationDialog(Context context, int theme, String aURL) {
        super(context, theme);

        _url = aURL;

        initializeDialog();
    }

private void initializeDialog() {
    // Clear cookies (callback not handled in this test implementation)
    CookieManager cookieManager = CookieManager.getInstance();
    cookieManager.removeAllCookies(null);

    WebView webView = new WebView(getContext());

    webView.setLayoutParams(new LinearLayout.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT));
    webView.setWebViewClient(new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading (WebView view, WebResourceRequest request)
        {
            CookieManager cookieManager = CookieManager.getInstance();
            String cookiesString = cookieManager.getCookie(_url);

            if (cookiesString != null)
                Log.d(TAG, String.format("%s:\n%s", request.getUrl().toString(), cookiesString));

            return false;
        }
    });

    WebSettings settings = webView.getSettings();
    settings.setJavaScriptEnabled(true);
    settings.setJavaScriptCanOpenWindowsAutomatically(true);
    settings.setBuiltInZoomControls(true);
    setContentView(webView);

    webView.loadUrl(_url);
}
}



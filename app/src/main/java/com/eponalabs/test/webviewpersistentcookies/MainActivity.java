package com.eponalabs.test.webviewpersistentcookies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "https://eponalabs.sharepoint.com";
        WebAuthenticationDialog dialog = new WebAuthenticationDialog(this, android.R.style.Theme_NoTitleBar, url);
        dialog.show();
    }
}

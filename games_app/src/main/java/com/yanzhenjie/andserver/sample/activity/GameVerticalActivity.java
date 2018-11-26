package com.yanzhenjie.andserver.sample.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.yanzhenjie.andserver.sample.R;

public class GameVerticalActivity extends Activity {


    BridgeWebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,WindowManager.LayoutParams. FLAG_FULLSCREEN);

        setContentView(R.layout.activity_game_vertical);
        webView = findViewById(R.id.webView);
        String absUrl = MainActivity.mRootUrl + "games/game_point/index.html";

        webView.loadUrl(absUrl);

    }
}

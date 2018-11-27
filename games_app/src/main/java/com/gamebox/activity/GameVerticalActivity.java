package com.gamebox.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.domain.Game;
import com.domain.GameGson;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.yanzhenjie.andserver.sample.R;
import com.yanzhenjie.andserver.sample.activity.MainActivity;

public class GameVerticalActivity extends Activity {

    BridgeWebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game_vertical);

        webView = findViewById(R.id.webView);

        GameGson game=getIntent().getParcelableExtra("game");

        String absUrl = MainActivity.mRootUrl + game.getPath();
        webView.loadUrl(absUrl);

    }
}

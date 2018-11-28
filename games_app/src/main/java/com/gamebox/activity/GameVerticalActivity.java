package com.gamebox.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;

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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game_vertical);
        webView = findViewById(R.id.webView);
        GameGson game = getIntent().getParcelableExtra("game");

        String absUrl = MainActivity.mRootUrl + game.getPath();
        webView.loadUrl(absUrl);
    }
    @Override
    protected void onDestroy() {
        if( webView!=null) {

            // 如果先调用destroy()方法，则会命中if (isDestroyed()) return;这一行代码，需要先onDetachedFromWindow()，再
            // destory()
            ViewParent parent = webView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(webView);
            }

            webView.stopLoading();
            // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
            webView.getSettings().setJavaScriptEnabled(false);
            webView.clearHistory();
            webView.clearView();
            webView.removeAllViews();
            webView.destroy();

        }
        super.onDestroy();
    }


}

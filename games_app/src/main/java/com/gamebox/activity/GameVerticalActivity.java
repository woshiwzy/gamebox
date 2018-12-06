package com.gamebox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.domain.GameGson;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.yanzhenjie.andserver.sample.BuildConfig;
import com.yanzhenjie.andserver.sample.R;

public class GameVerticalActivity extends BaseGameActivity {

    private BridgeWebView webView;
    private long firstTime;
    private AdView adView;

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

        adView = new AdView(this, "222090675045613_324107841510562", AdSize.BANNER_HEIGHT_50);
        LinearLayout adContainer = findViewById(R.id.banner_container);
        adContainer.addView(adView);

        adView.setAdListener(new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                Log.d("game", adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
            }

            @Override
            public void onAdClicked(Ad ad) {
            }

            @Override
            public void onLoggingImpression(Ad ad) {
            }
        });

        int delayShow= BuildConfig.DEBUG?5*1000:10*1000;

        adView.postDelayed(new Runnable() {
            @Override
            public void run() {
                adView.loadAd();
            }
        },delayShow);

    }

    @Override
    protected void onDestroy() {
        if (webView != null) {
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


        if (adView != null) {
            adView.destroy();
        }

        super.onDestroy();
    }


    @Override
    public void onBackPressed() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 2000) {
            Toast.makeText(this, "Press Again Exit Game", Toast.LENGTH_SHORT).show();
            firstTime = secondTime;
        } else {

            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra(EXIST, true);
            startActivity(intent);

            finish();
        }
    }
}

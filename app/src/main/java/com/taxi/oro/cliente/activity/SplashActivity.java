package com.taxi.oro.cliente.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.airbnb.lottie.LottieAnimationView;
import com.taxi.oro.R;
import com.taxi.oro.cliente.settings.PrefManager;


public class SplashActivity extends BaseActivity {
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setContentView(R.layout.activity_splash);
        Context context = SplashActivity.this;



        LottieAnimationView animationViews = (LottieAnimationView) findViewById(R.id.lots);
        animationViews.setAnimation("loading.json");
        animationViews.loop(true);
        animationViews.playAnimation();
        // making notification bar transparent
        changeStatusBarColor();

        PrefManager prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
        }else{
            timeHandler();
        }
    }

    private void timeHandler(){
        int SPLASH_TIME_OUT = 2000;
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                launchHomeScreen();
            }
        }, SPLASH_TIME_OUT);
    }

    private void launchHomeScreen() {
        //prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        finish();
    }

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}

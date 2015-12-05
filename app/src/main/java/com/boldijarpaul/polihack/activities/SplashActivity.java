package com.boldijarpaul.polihack.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.boldijarpaul.polihack.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SplashActivity extends AppCompatActivity {

    @Bind(R.id.splash)
    View mRoot;
    @Bind(R.id.splash_icon)
    View mIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);

        new AnimationUtils();
        Animation rotation = AnimationUtils.loadAnimation(this, R.anim.rotating);
        mIcon.startAnimation(rotation);
    }

    @OnClick(R.id.splash)
    void splashClick() {
        startActivity(new Intent(this, LoginAppActivity.class));
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}

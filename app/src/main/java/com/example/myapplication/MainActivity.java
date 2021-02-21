package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN = 3000;
    Animation topanim, botanim;
    ImageView image;
    TextView brandname, slogan;

    @Override
    public boolean moveSharedPreferencesFrom(Context sourceContext, String name) {
        return super.moveSharedPreferencesFrom(sourceContext, name);
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        topanim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.animation_top);
        botanim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.animation_bottom);
        image = (ImageView) findViewById(R.id.imageView);
        brandname = (TextView) findViewById(R.id.brandname);
        slogan = (TextView) findViewById(R.id.Slogan);
        //moveSharedPreferencesFrom(,"username");
        image.setAnimation(topanim);
        brandname.setAnimation(botanim);
        slogan.setAnimation(botanim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, loginform.class);
                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View,String>(image,"logo_image");
                pairs[1] = new Pair<View,String>(brandname,"memory");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
                startActivity(intent,options.toBundle());
            }
        },SPLASH_SCREEN);
    }
}
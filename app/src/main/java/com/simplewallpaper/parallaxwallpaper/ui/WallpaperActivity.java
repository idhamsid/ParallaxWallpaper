package com.simplewallpaper.parallaxwallpaper.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.WallpaperManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;
import com.simplewallpaper.spacewallpaper.R;
import com.bumptech.glide.Glide;
import com.simplewallpaper.parallaxwallpaper.adapter.ImageAdapter;
import com.simplewallpaper.parallaxwallpaper.parallax.LiveWallpaperService;

import java.io.IOException;

public class WallpaperActivity extends AppCompatActivity{
    private void transparentStatusAndNavigation() {
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            );
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
        }
    }

    private void setWindowFlag(final int bits, boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    ImageView imgWall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.baseline_arrow_back_24));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.POS=0;
                finish();

            }
        });
        setTitle("");

        transparentStatusAndNavigation();
        if (ContextCompat.checkSelfPermission(WallpaperActivity.this, android.Manifest.permission.SET_WALLPAPER)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(WallpaperActivity.this,
                    new String[]{Manifest.permission.SET_WALLPAPER},
                    124);
        }
        imgWall = findViewById(R.id.imgDetailwall);
            if (ImageAdapter.url_image.startsWith("http")) {
                Glide
                        .with(this)
                        .load(ImageAdapter.url_image)
                        .centerCrop()
                        .into(imgWall);
            } else {
                Glide
                        .with(this)
                        .load("file:///android_asset/" + ImageAdapter.url_image)
                        .centerCrop()
                        .into(imgWall);

            }

            this.findViewById(R.id.imgSet).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (MainActivity.POS==0){
                        Toast.makeText(WallpaperActivity.this, R.string
                                .failedSetting, Toast.LENGTH_LONG).show();

                    } else {
                        Intent open = new Intent(WallpaperActivity.this, SettingActivity.class);
                        startActivity(open);

                    }

                }
            });

        this.findViewById(R.id.imgWall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.POS=1;
                if (Build.VERSION.SDK_INT > 15) {
                    try {
                        WallpaperManager.getInstance(WallpaperActivity.this).clear();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {

                        WallpaperActivity.this.startActivity(new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER)
                                .putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, new
                                        ComponentName(WallpaperActivity.this, LiveWallpaperService.class))
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    } catch (ActivityNotFoundException e) {
                        try {
                            WallpaperActivity.this.startActivity(new Intent(WallpaperManager.ACTION_LIVE_WALLPAPER_CHOOSER)
                                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        } catch (ActivityNotFoundException e2) {
                            Toast.makeText(WallpaperActivity.this, R.string
                                    .toast_failed_launch_wallpaper_chooser, Toast.LENGTH_LONG).show();
                        }
                    }

                }
            }
        });

    }

    public void onBackPressed() {



    }
}


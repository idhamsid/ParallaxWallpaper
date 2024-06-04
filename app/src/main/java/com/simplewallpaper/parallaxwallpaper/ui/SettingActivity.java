package com.simplewallpaper.parallaxwallpaper.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.text.SpannableString;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.simplewallpaper.parallaxwallpaper.config.Utils;
import com.simplewallpaper.spacewallpaper.R;
import com.simplewallpaper.parallaxwallpaper.parallax.Cube;
import com.simplewallpaper.parallaxwallpaper.parallax.LiveWallpaperRenderer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.InputStream;

public class SettingActivity extends AppCompatActivity {
    private final int REQUEST_CHOOSE_PHOTOS = 0;
    private SharedPreferences.Editor editor;
    private Cube cube;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.baseline_arrow_back_24));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Utils.ShowInterstitialAds(SettingActivity.this, 0);
            }
        });
        setTitle("Settings 3D Effect");

        TextView textViewIntroduction = (TextView)findViewById(R.id.introduction);
        SpannableString spannableString = new SpannableString(Html.fromHtml(getResources()
                .getString(R.string.introduction2)));
        textViewIntroduction.setText(spannableString);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        if (Build.VERSION.SDK_INT >= 21) {
            CheckBox checkBoxPowerSaver = (CheckBox)findViewById(R.id.checkBoxPower);
            checkBoxPowerSaver.setVisibility(View.VISIBLE);
            checkBoxPowerSaver.setChecked(preferences.getBoolean("power_saver", true));
            checkBoxPowerSaver.setOnCheckedChangeListener(new CompoundButton
                    .OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    editor.putBoolean("power_saver", isChecked);
                    editor.apply();
                }
            });
        }


        SeekBar seekBarRange = (SeekBar)findViewById(R.id.seekBarRange);
        seekBarRange.setProgress(preferences.getInt("range", 10));
        seekBarRange.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    editor.putInt("range", progress);
                    editor.apply();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        SeekBar seekBarDelay = (SeekBar)findViewById(R.id.seekBarDelay);
        seekBarDelay.setProgress(preferences.getInt("delay", 10));
        seekBarDelay.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    editor.putInt("delay", progress);
                    editor.apply();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        CheckBox checkBoxScroll = (CheckBox)findViewById(R.id.checkBoxScroll);
        checkBoxScroll.setChecked(preferences.getBoolean("scroll", true));
        checkBoxScroll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("scroll", isChecked);
                editor.apply();
            }
        });
        cube = (Cube)findViewById(R.id.cube);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    public InputStream openUri(Uri uri) {
        if (uri == null) return null;
        try {
            return getContentResolver().openInputStream(uri);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LiveWallpaperRenderer.BiasChangeEvent event) {
        cube.setRotation(event.getY(), event.getX());
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
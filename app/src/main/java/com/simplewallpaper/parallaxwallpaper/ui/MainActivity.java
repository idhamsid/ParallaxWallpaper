package com.simplewallpaper.parallaxwallpaper.ui;



import static com.google.android.play.core.install.model.AppUpdateType.FLEXIBLE;
import static com.simplewallpaper.parallaxwallpaper.config.SettingsAlien.BASE_64_LICENSE_KEY;
import static com.simplewallpaper.parallaxwallpaper.config.SettingsAlien.LINK_REDIRECT;
import static com.simplewallpaper.parallaxwallpaper.config.SettingsAlien.MODE_DIALOG_EXIT;
import static com.simplewallpaper.parallaxwallpaper.config.SettingsAlien.ON_OFF_ADS;
import static com.simplewallpaper.parallaxwallpaper.config.SettingsAlien.ON_OFF_DATA;
import static com.simplewallpaper.parallaxwallpaper.config.SettingsAlien.PROTECT_APP;
import static com.simplewallpaper.parallaxwallpaper.config.SettingsAlien.SELECT_MAIN_ADS;
import static com.simplewallpaper.parallaxwallpaper.config.SettingsAlien.STATUS_APP;
import static com.simplewallpaper.parallaxwallpaper.config.SettingsAlien.URL_DATA;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aliendroid.alienads.AlienNotif;
import com.aliendroid.alienads.AlienOpenAds;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.github.javiersantos.piracychecker.PiracyChecker;
import com.github.javiersantos.piracychecker.enums.Display;
import com.github.javiersantos.piracychecker.enums.InstallerID;
import com.github.javiersantos.piracychecker.utils.LibraryUtilsKt;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.simplewallpaper.parallaxwallpaper.adapter.AdmobAdAdapter;
import com.simplewallpaper.parallaxwallpaper.adapter.ImageAdapter;
import com.simplewallpaper.parallaxwallpaper.adapter.MoreApp;

import com.simplewallpaper.parallaxwallpaper.config.SettingsAlien;
import com.simplewallpaper.parallaxwallpaper.config.Utils;
import com.simplewallpaper.parallaxwallpaper.model.Wallpaper;
import com.simplewallpaper.spacewallpaper.BuildConfig;
import com.squareup.picasso.Picasso;
import com.simplewallpaper.spacewallpaper.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    GridLayoutManager mLayoutManager;
    List<MoreApp> webLists;
    public static int POS = 0;
    ArrayList<Wallpaper> recentLists;
    private RecyclerView recRecent;
    private ImageAdapter adapter;
    private static final int REQUEST = 114;
    public static File dir;

    /*
  In App Update
   */
    AppUpdateManager appUpdateManager;
    Task<AppUpdateInfo> appUpdateInfoTask;
    private static final int MY_REQUEST_CODE = 17326;

    ReviewInfo reviewInfo;
    ReviewManager manager;

    private void checkUpdate(){
        appUpdateManager = AppUpdateManagerFactory.create(this);
        appUpdateManager.registerListener(listener);

        appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();
        appUpdateInfoTask.addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onSuccess(AppUpdateInfo appUpdateInfo) {
                Log.d("appUpdateInfo :", "packageName :"+appUpdateInfo.packageName()+ ", "+ "availableVersionCode :"+ appUpdateInfo.availableVersionCode() +", "+"updateAvailability :"+ appUpdateInfo.updateAvailability() +", "+ "installStatus :" + appUpdateInfo.installStatus() );

                if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                        && appUpdateInfo.isUpdateTypeAllowed(FLEXIBLE)){
                    requestUpdate(appUpdateInfo);
                    Log.d("UpdateAvailable","update is there ");
                }
                else if (appUpdateInfo.updateAvailability() == 3){
                    Log.d("Update","3");
                    notifyUser();
                }
            }
        });
    }
    private void requestUpdate(AppUpdateInfo appUpdateInfo){
        try {
            appUpdateManager.startUpdateFlowForResult(appUpdateInfo, AppUpdateType.FLEXIBLE, MainActivity.this,MY_REQUEST_CODE);
            resume();
        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }
    }

    InstallStateUpdatedListener listener = new InstallStateUpdatedListener() {
        @Override
        public void onStateUpdate(InstallState installState) {
            if (installState.installStatus() == InstallStatus.DOWNLOADED){
                Log.d("InstallDownloded","InstallStatus sucsses");
                notifyUser();
            }
        }
    };


    private void notifyUser() {

        Snackbar snackbar =
                Snackbar.make(findViewById(R.id.swipeRefresh),
                        "An update has just been downloaded.",
                        Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("RESTART", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appUpdateManager.completeUpdate();
            }
        });
        snackbar.setActionTextColor(
                getResources().getColor(R.color.colorPrimary));
        snackbar.show();
    }

    private void resume(){
        appUpdateManager.getAppUpdateInfo().addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
            @Override
            public void onSuccess(AppUpdateInfo appUpdateInfo) {
                if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED){
                    notifyUser();

                }

            }
        });
    }

    /*
    In app review
     */

    private void Review(){
        manager = ReviewManagerFactory.create(this);
        manager.requestReviewFlow().addOnCompleteListener(new OnCompleteListener<ReviewInfo>() {
            @Override
            public void onComplete(@NonNull Task<ReviewInfo> task) {
                if(task.isSuccessful()){
                    reviewInfo = task.getResult();
                    manager.launchReviewFlow(MainActivity.this, reviewInfo).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(Exception e) {
                            //Toast.makeText(MainActivity.this, "Rating Failed", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            // Toast.makeText(MainActivity.this, "Review Completed, Thank You!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Toast.makeText(MainActivity.this, "In-App Request Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Utils.CheckMrKim(this);
        if (ON_OFF_ADS.equals("1")){
            if(checkConnectivity()){
                loadUrlAds();
            } else {
                LOADADS();
            }

        } else {
            LOADADS();
        }
        checkUpdate();
        Review();

        recRecent = findViewById(R.id.recWall);
        recRecent.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        recRecent.setLayoutManager(mLayoutManager);
        recentLists = new ArrayList<>();





        ImageView imgHeader = findViewById(R.id.imgHeader);

        Glide.with(MainActivity.this).load("file:///android_asset/header.gif").into(imgHeader);

        webLists = new ArrayList<>();
        if (checkConnectivity()) {

        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/");
            } else {
                dir = new File(Environment.getExternalStorageDirectory() + "/");
            }
            if (!dir.exists()) {
                dir.mkdirs();
            }
            for (String signature : LibraryUtilsKt.getApkSignatures(this)) {
                Log.e("Signature", signature);
            }
            AlienNotif.LoadOneSignal(SettingsAlien.ONE_SIGNAL_API_KEY);
        } else {
            checkWriteData();
        }


        if (PROTECT_APP) {
            new PiracyChecker(this)
                    .enableGooglePlayLicensing(BASE_64_LICENSE_KEY)
                    .enableUnauthorizedAppsCheck()
                    .display(Display.DIALOG)
                    .enableInstallerId(InstallerID.GOOGLE_PLAY, InstallerID.AMAZON_APP_STORE, InstallerID.GALAXY_APPS)
                    .saveResultToSharedPreferences("my_app_preferences", "valid_license")
                    .start();
        }
        if (STATUS_APP.equals("1")) {
            String str = LINK_REDIRECT;
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(str)));
            finish();
        }



        if (SettingsAlien.ON_OFF_DATA.equals("1")){
            if (BuildConfig.APPLICATION_ID.equals(Utils.APPID)){
                if (checkConnectivity()) {
                    loadUrlData();
                    loadUrlMore();
                } else {
                    nointernetp();
                }
            } else {
                WrongPN();
            }
        } else {
            ambildata();
            ambilMore();
        }


    }

    void LOADADS (){
        Utils.CheckInitializeAds(MainActivity.this);
        AlienOpenAds.LoadOpenAds(SettingsAlien.ADMOB_OPENADS, true, SELECT_MAIN_ADS);
        Utils.LoadGDPR(MainActivity.this);
        Utils.CheckInitializeAds(MainActivity.this);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) RelativeLayout layAdsbanner = findViewById(R.id.layAds);
        Utils.ShowBannerNatives(MainActivity.this,layAdsbanner);
        Utils.LoadInterstitialAds(MainActivity.this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/");
                } else {
                    dir = new File(Environment.getExternalStorageDirectory() + "/");
                }
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                AlienNotif.LoadOneSignal(SettingsAlien.ONE_SIGNAL_API_KEY);
            }

        }
    }

    void checkWriteData(){
        if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/");
            } else {
                dir = new File(Environment.getExternalStorageDirectory() + "/");
            }
            if (!dir.exists()) {
                dir.mkdirs();
            }
            AlienNotif.LoadOneSignal(SettingsAlien.ONE_SIGNAL_API_KEY);
        }
    }
    private void loadUrlData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //finishDialog
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("Wallpaper");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jo = array.getJSONObject(i);
                            Wallpaper developers = new Wallpaper( jo.getString("wallpaper_name"), jo.getString("wallpaper_url"));
                            recentLists.add(developers);
                    }
                    adapter = new ImageAdapter(recentLists, MainActivity.this);
                    recRecent.setAdapter(adapter);
                    Collections.shuffle(recentLists);

                    if (SettingsAlien.ON_OFF_NATIVE_ON_LIST.equals("1")){
                        AdmobAdAdapter nativeAdapter = AdmobAdAdapter.Builder.with(
                                        "1",
                                        adapter,
                                        "big")
                                .adItemInterval(SettingsAlien.INTERVAL_NATIVE)//Repeat interval
                                .build();
                        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                            @Override
                            public int getSpanSize(int position) {
                                if (AdmobAdAdapter.isAdPosition(position)){
                                    return 2;
                                }
                                return 1;
                            }
                        });
                        recRecent.setAdapter(nativeAdapter);
                    }

                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
    }


    private boolean checkConnectivity() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        return info != null && info.isConnected() && info.isAvailable();
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("wallpaper.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }
    public void ambilMore() {
        try {

            JSONObject jsonObject = new JSONObject(loadJSONFromAsset());
            JSONArray array = jsonObject.getJSONArray("MoreApp");
            for (int i = 0; i < array.length(); i++) {
                JSONObject jo = array.getJSONObject(i);
                MoreApp developers = new MoreApp(jo.getInt("id"), jo.getString("title"),
                        jo.getString("image"), jo.getString("link"));
                webLists.add(developers);

            }

        } catch (JSONException e) {
            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }
    public void ambildata() {
        try {

            JSONObject jsonObject = new JSONObject(loadJSONFromAsset());
            JSONArray array = jsonObject.getJSONArray("Wallpaper");
            for (int i = 0; i < array.length(); i++) {
                JSONObject jo = array.getJSONObject(i);
                Wallpaper developers = new Wallpaper( jo.getString("wallpaper_name"), jo.getString("wallpaper_url"));
                recentLists.add(developers);
            }
            adapter = new ImageAdapter(recentLists, MainActivity.this);
            recRecent.setAdapter(adapter);
            Collections.shuffle(recentLists);
            if (SettingsAlien.ON_OFF_NATIVE_ON_LIST.equals("1")){
                AdmobAdAdapter nativeAdapter = AdmobAdAdapter.Builder.with(
                                "1",
                                adapter,
                                "big")
                        .adItemInterval(SettingsAlien.INTERVAL_NATIVE)//Repeat interval
                        .build();
                mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        if (AdmobAdAdapter.isAdPosition(position)){
                            return 2;
                        }
                        return 1;
                    }
                });
                recRecent.setAdapter(nativeAdapter);
            }

        } catch (JSONException e) {
            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }
    public void onBackPressed() {
        if (checkConnectivity()) {
            if (MODE_DIALOG_EXIT.equals("1")){
                dialogExit();
            } else {
                showExit();
            }

        } else {
           finish();
        }

    }
    void dialogExit(){
        final Dialog dialog = new Dialog(MainActivity.this,R.style.SheetDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_open);

        Button tbOpen = dialog.findViewById(R.id.tbYes);
        tbOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                System.exit(0);
            }
        });
        Utils.ShowMediumBanner(MainActivity.this, dialog.findViewById(R.id.layAds2));
        Button open = dialog.findViewById(R.id.tbRate);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="
                        + BuildConfig.APPLICATION_ID)));
            }
        });
        ImageButton tbClose = dialog.findViewById(R.id.imgExit);
        tbClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    public void showExit() {
        final Dialog dialog = new Dialog(MainActivity.this,R.style.SheetDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.exit_dialog);
        Random r = new Random();
        int i1 = r.nextInt(webLists.size());
        TextView txtTitle = dialog.findViewById(R.id.txtTitleMore);
        txtTitle.setText(webLists.get(i1).getName_app());
        ImageView imgMore = dialog.findViewById(R.id.imgMoreExit);
        Picasso.get().load(webLists.get(i1).getImage_url()).into(imgMore);
        Button btDownload = dialog.findViewById(R.id.tbDownloadExit);
        btDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(webLists.get(i1).getApp_url())));
                dialog.dismiss();
            }
        });
        ImageView exitApp = dialog.findViewById(R.id.imgCloseOpen2);
        exitApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
                dialog.dismiss();
            }
        });
        Button btRate = dialog.findViewById(R.id.tbRateExit);
        btRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="
                        + BuildConfig.APPLICATION_ID)));
                finish();
                dialog.dismiss();
            }
        });
        Button btExit = dialog.findViewById(R.id.tbExitExit);
        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String shareLink = "https://play.google.com/store/apps/details?id="
                        + BuildConfig.APPLICATION_ID;
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        getResources().getString(R.string.shareit)
                                +" "+ shareLink);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void loadUrlMore() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("MoreApp");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jo = array.getJSONObject(i);
                        MoreApp developers = new MoreApp(jo.getInt("id"), jo.getString("title"),
                                jo.getString("image"), jo.getString("link"));
                        webLists.add(developers);

                    }


                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
    }

    private void nointernetp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setIcon(R.drawable.baseline_network_check_24);
        builder.setTitle("Bad Connection");
        builder.setMessage("No internet access, please activate the internet to use the app!");
        builder.setInverseBackgroundForced(true);
        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("Reload", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void WrongPN() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setIcon(R.drawable.baseline_warning_24);
        builder.setTitle(getString(R.string.wrong_pn));
        builder.setMessage(getString(R.string.wrong_pn_description));
        builder.setInverseBackgroundForced(true);
        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();

            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void loadUrlAds() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                Utils.DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObj = new JSONObject(response);
                    JSONArray contacts = jsonObj.getJSONArray("Ads");
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        SettingsAlien.STATUS_APP = c.getString("status_app");
                        SettingsAlien.LINK_REDIRECT = c.getString("link_redirect");
                        SELECT_MAIN_ADS = c.getString("select_main_ads");
                        SettingsAlien.SELECT_BACKUP_ADS = c.getString("select_backup_ads");
                        SettingsAlien.MAIN_ADS_BANNER = c.getString("main_ads_banner");
                        SettingsAlien.MAIN_ADS_INTERTITIAL = c.getString("main_ads_intertitial");
                        SettingsAlien.MAIN_ADS_NATIVES = c.getString("main_ads_natives");
                        SettingsAlien.BACKUP_ADS_BANNER = c.getString("backup_ads_banner");
                        SettingsAlien.BACKUP_ADS_INTERTITIAL = c.getString("backup_ads_intertitial");
                        SettingsAlien.BACKUP_ADS_NATIVES = c.getString("backup_ads_natives");
                        SettingsAlien.ADMOB_OPENADS = c.getString("open_ads_admob");
                        SettingsAlien.ALIEN_OPENADS = c.getString("open_ads_alien");
                        SettingsAlien.MAX_OPENADS = c.getString("open_ads_max");
                        SettingsAlien.ON_OFF_NATIVE_ON_LIST = c.getString("on_off_native_listview");
                        SettingsAlien.INITIALIZE_MAIN_SDK = c.getString("initialize_sdk");
                        SettingsAlien.INITIALIZE_SDK_BACKUP_ADS = c.getString("initialize_sdk_backup_ads");
                        SettingsAlien.INTERVAL = c.getInt("interval_intertitial");
                        SettingsAlien.HIGH_PAYING_KEYWORD1 = c.getString("high_paying_keyword_1");
                        SettingsAlien.HIGH_PAYING_KEYWORD2 = c.getString("high_paying_keyword_2");
                        SettingsAlien.HIGH_PAYING_KEYWORD3 = c.getString("high_paying_keyword_3");
                        SettingsAlien.HIGH_PAYING_KEYWORD4 = c.getString("high_paying_keyword_4");
                        SettingsAlien.HIGH_PAYING_KEYWORD5 = c.getString("high_paying_keyword_5");


                    }
                } catch (JSONException e) {

                    e.printStackTrace();
                }

              LOADADS();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               LOADADS();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);

    }


}
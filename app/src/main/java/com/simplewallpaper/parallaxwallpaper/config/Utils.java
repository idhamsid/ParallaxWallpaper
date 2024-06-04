package com.simplewallpaper.parallaxwallpaper.config;



import static com.simplewallpaper.parallaxwallpaper.config.SettingsAlien.BACKUP_ADS_BANNER;
import static com.simplewallpaper.parallaxwallpaper.config.SettingsAlien.BACKUP_ADS_INTERTITIAL;
import static com.simplewallpaper.parallaxwallpaper.config.SettingsAlien.BACKUP_ADS_NATIVES;
import static com.simplewallpaper.parallaxwallpaper.config.SettingsAlien.CHILD_DIRECT_GDPR;
import static com.simplewallpaper.parallaxwallpaper.config.SettingsAlien.HIGH_PAYING_KEYWORD1;
import static com.simplewallpaper.parallaxwallpaper.config.SettingsAlien.HIGH_PAYING_KEYWORD2;
import static com.simplewallpaper.parallaxwallpaper.config.SettingsAlien.HIGH_PAYING_KEYWORD3;
import static com.simplewallpaper.parallaxwallpaper.config.SettingsAlien.HIGH_PAYING_KEYWORD4;
import static com.simplewallpaper.parallaxwallpaper.config.SettingsAlien.HIGH_PAYING_KEYWORD5;
import static com.simplewallpaper.parallaxwallpaper.config.SettingsAlien.INITIALIZE_MAIN_SDK;
import static com.simplewallpaper.parallaxwallpaper.config.SettingsAlien.INITIALIZE_SDK_BACKUP_ADS;
import static com.simplewallpaper.parallaxwallpaper.config.SettingsAlien.MAIN_ADS_BANNER;
import static com.simplewallpaper.parallaxwallpaper.config.SettingsAlien.MAIN_ADS_INTERTITIAL;
import static com.simplewallpaper.parallaxwallpaper.config.SettingsAlien.MAIN_ADS_NATIVES;
import static com.simplewallpaper.parallaxwallpaper.config.SettingsAlien.SELECT_BACKUP_ADS;
import static com.simplewallpaper.parallaxwallpaper.config.SettingsAlien.SELECT_MAIN_ADS;
import static com.simplewallpaper.parallaxwallpaper.config.SettingsAlien.URL_DATA;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.RelativeLayout;

import com.aliendroid.alienads.AlienGDPR;
import com.aliendroid.alienads.AlienNotif;
import com.aliendroid.alienads.AliendroidBanner;
import com.aliendroid.alienads.AliendroidInitialize;
import com.aliendroid.alienads.AliendroidIntertitial;
import com.aliendroid.alienads.AliendroidMediumBanner;
import com.aliendroid.alienads.AliendroidNative;
import com.aliendroid.alienads.AliendroidReward;
import com.aliendroid.sdkads.config.AppPromote;
import com.aliendroid.sdkads.config.QWERTY;
import com.simplewallpaper.spacewallpaper.BuildConfig;


public class Utils {
    public static String Split01 = QWERTY.ONE(URL_DATA);
    public static String Splite02 = QWERTY.TWO(URL_DATA);
    public static String APPID;
    public static String DATA;

    public static void CheckMrKim(Activity activity) {
        if (URL_DATA.startsWith("https")) {
            Utils.DATA = URL_DATA;
            Utils.APPID = BuildConfig.APPLICATION_ID;
        } else {
            Utils.DATA = QWERTY.ZXC(Utils.Split01);
            Utils.APPID = QWERTY.ZXC(Utils.Splite02);
            ;
        }
        AppPromote.initializeAppPromote(activity);

    }

    public static void LoadGDPR(Activity activity) {
        AlienGDPR.loadGdpr(activity, SELECT_MAIN_ADS, CHILD_DIRECT_GDPR);
    }

    public static void CheckInitializeAds(Activity activity) {
        switch (SELECT_MAIN_ADS) {
            case "ADMOB":
                AliendroidInitialize.SelectAdsAdmob(activity, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                break;
            case "GOOGLE-ADS":
                AliendroidInitialize.SelectAdsGoogleAds(activity, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                break;
            case "APPLOVIN-D":
                AliendroidInitialize.SelectAdsApplovinDis(activity, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                break;
            case "APPLOVIN-D-NB":
                AliendroidInitialize.SelectAdsApplovinDis(activity, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                break;
            case "APPLOVIN-M":
                AliendroidInitialize.SelectAdsApplovinMax(activity, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                break;
            case "APPLOVIN-M-NB":
                AliendroidInitialize.SelectAdsApplovinMax(activity, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                break;
            case "MOPUB":
                AliendroidInitialize.SelectAdsMopub(activity, SELECT_BACKUP_ADS, INITIALIZE_MAIN_SDK, INITIALIZE_SDK_BACKUP_ADS);
                break;
            case "IRON":
                AliendroidInitialize.SelectAdsIron(activity, SELECT_BACKUP_ADS, INITIALIZE_MAIN_SDK, INITIALIZE_SDK_BACKUP_ADS);
                break;
            case "STARTAPP":
                AliendroidInitialize.SelectAdsStartApp(activity, SELECT_BACKUP_ADS, INITIALIZE_MAIN_SDK, INITIALIZE_SDK_BACKUP_ADS);
                break;
            case "UNITY":
                AliendroidInitialize.SelectAdsUnity(activity, SELECT_BACKUP_ADS, INITIALIZE_MAIN_SDK, INITIALIZE_SDK_BACKUP_ADS);
                break;
            case "FACEBOOK":
                AliendroidInitialize.SelectAdsFAN(activity, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                break;
            case "ALIEN-M":
                AliendroidInitialize.SelectAdsAlienMediation(activity, SELECT_BACKUP_ADS, INITIALIZE_MAIN_SDK, INITIALIZE_SDK_BACKUP_ADS);
                break;
            case "ALIEN-V":
                AliendroidInitialize.SelectAdsAlienView(activity, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                break;
        }
    }

    public static void ShowBannerNatives(Activity activity, RelativeLayout layAds) {
        switch (SELECT_MAIN_ADS) {
            case "ADMOB":
                    AliendroidBanner.SmallBannerAdmob(activity, layAds, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER, HIGH_PAYING_KEYWORD1,
                            HIGH_PAYING_KEYWORD2, HIGH_PAYING_KEYWORD3, HIGH_PAYING_KEYWORD4, HIGH_PAYING_KEYWORD5);
                break;
            case "APPLOVIN-M":
                    AliendroidBanner.SmallBannerApplovinMax(activity, layAds, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                break;
            case "APPLOVIN-M-NB":
                break;
            case "APPLOVIN-D":
                AliendroidBanner.SmallBannerApplovinDis(activity, layAds, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                break;
            case "APPLOVIN-D-NB":
                break;
            case "MOPUB":
                AliendroidBanner.SmallBannerMopub(activity, layAds, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                break;
            case "STARTAPP":
                    AliendroidBanner.SmallBannerStartApp(activity, layAds, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                break;
            case "IRON":
                AliendroidBanner.SmallBannerIron(activity, layAds, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                break;
            case "FACEBOOK":
                    AliendroidBanner.SmallBannerFAN(activity, layAds, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                break;
            case "UNITY":
                AliendroidBanner.SmallBannerUnity(activity, layAds, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                break;
            case "GOOGLE-ADS":
                AliendroidBanner.SmallBannerGoogleAds(activity, layAds, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                break;
            case "ALIEN-M":
                    AliendroidBanner.SmallBannerAlienMediation(activity, layAds, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                break;
            case "ALIEN-V":
                AliendroidBanner.SmallBannerAlienView(activity, layAds, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                break;
        }
    }

    public static void LoadInterstitialAds(Activity activity) {
        switch (SELECT_MAIN_ADS) {
            case "ADMOB":
                AliendroidIntertitial.LoadIntertitialAdmob(activity, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, HIGH_PAYING_KEYWORD1,
                        HIGH_PAYING_KEYWORD2, HIGH_PAYING_KEYWORD3, HIGH_PAYING_KEYWORD4, HIGH_PAYING_KEYWORD5);
                break;
            case "APPLOVIN-M":
                AliendroidIntertitial.LoadIntertitialApplovinMax(activity, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL);
                break;
            case "APPLOVIN-M-NB":
                AliendroidIntertitial.LoadIntertitialApplovinMax(activity, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL);
                break;
            case "APPLOVIN-D":
                AliendroidIntertitial.LoadIntertitialApplovinDis(activity, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL);
                break;
            case "APPLOVIN-D-NB":
                AliendroidIntertitial.LoadIntertitialApplovinDis(activity, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL);
                break;
            case "MOPUB":
                AliendroidIntertitial.LoadIntertitialMopub(activity, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL);
                break;
            case "STARTAPP":
                AliendroidIntertitial.LoadIntertitialStartApp(activity, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL);
                break;
            case "IRON":
                AliendroidIntertitial.LoadIntertitialIron(activity, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL);
                break;
            case "FACEBOOK":
                AliendroidIntertitial.LoadIntertitialFAN(activity, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL);
                break;
            case "UNITY":
                AliendroidIntertitial.LoadIntertitialUnity(activity, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL);
                break;
            case "GOOGLE-ADS":
                AliendroidIntertitial.LoadIntertitialGoogleAds(activity, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL);
                break;
            case "ALIEN-M":
                AliendroidIntertitial.LoadIntertitialAlienMediation(activity, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL);
                break;
            case "ALIEN-V":
                AliendroidIntertitial.LoadIntertitialAlienView(activity, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL);
                break;
        }
    }

    public static void ShowInterstitialAds(Activity activity, int Interval) {
        switch (SELECT_MAIN_ADS) {
            case "ADMOB":
                AliendroidIntertitial.ShowIntertitialAdmob(activity, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, Interval,
                        HIGH_PAYING_KEYWORD1, HIGH_PAYING_KEYWORD2, HIGH_PAYING_KEYWORD3, HIGH_PAYING_KEYWORD4, HIGH_PAYING_KEYWORD5);
                break;
            case "APPLOVIN-D":
            case "APPLOVIN-D-NB":
                AliendroidIntertitial.ShowIntertitialApplovinDis(activity, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, Interval);
                break;
            case "APPLOVIN-M":
            case "APPLOVIN-M-NB":
                AliendroidIntertitial.ShowIntertitialApplovinMax(activity, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, Interval);
                break;
            case "IRON":
                AliendroidIntertitial.ShowIntertitialIron(activity, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, Interval);
                break;
            case "MOPUB":
                AliendroidIntertitial.ShowIntertitialMopub(activity, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, Interval);
                break;
            case "STARTAPP":
                AliendroidIntertitial.ShowIntertitialSartApp(activity, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, Interval);
                break;
            case "FACEBOOK":
                AliendroidIntertitial.ShowIntertitialFAN(activity, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, Interval);
                break;
            case "GOOGLE-ADS":
                AliendroidIntertitial.ShowIntertitialGoogleAds(activity, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, Interval);
                break;
            case "UNITY":
                AliendroidIntertitial.ShowIntertitialUnity(activity, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, Interval);
                break;
            case "ALIEN-V":
                AliendroidIntertitial.ShowIntertitialAlienView(activity, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, Interval);
                break;
            case "ALIEN-M":
                AliendroidIntertitial.ShowIntertitialAlienMediation(activity, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, Interval);
                break;
        }
    }


    public static void NativeAdsListview(Activity activity, RelativeLayout layAdsbanner){
        switch (SELECT_MAIN_ADS) {
            case "ADMOB":
                AliendroidNative.MediumNativeAdmob(activity, layAdsbanner, SELECT_BACKUP_ADS, MAIN_ADS_NATIVES, BACKUP_ADS_NATIVES, HIGH_PAYING_KEYWORD1,
                        HIGH_PAYING_KEYWORD2, HIGH_PAYING_KEYWORD3, HIGH_PAYING_KEYWORD4, HIGH_PAYING_KEYWORD5);
                break;
            case "APPLOVIN-M":
                AliendroidNative.MediumNativeMax(activity, layAdsbanner, SELECT_BACKUP_ADS, MAIN_ADS_NATIVES, BACKUP_ADS_NATIVES);
                break;
            case "APPLOVIN-M-NB":
                AliendroidNative.MediumNativeMax(activity, layAdsbanner, SELECT_BACKUP_ADS, MAIN_ADS_NATIVES, BACKUP_ADS_NATIVES);
                break;
            case "APPLOVIN-D":

                break;
            case "APPLOVIN-D-NB":

                break;
            case "MOPUB":

                break;
            case "STARTAPP":
                AliendroidNative.MediumNativeStartApp(activity, layAdsbanner, SELECT_BACKUP_ADS, MAIN_ADS_NATIVES, BACKUP_ADS_NATIVES);
                break;
            case "IRON":
                break;
            case "FACEBOOK":
                AliendroidNative.MediumNativeFan(activity, layAdsbanner, SELECT_BACKUP_ADS,MAIN_ADS_NATIVES, BACKUP_ADS_NATIVES);
                break;
            case "UNITY":

                break;
            case "GOOGLE-ADS":
                break;
            case "ALIEN-M":
                AliendroidNative.MediumNativeAlien(activity,layAdsbanner,SELECT_BACKUP_ADS,MAIN_ADS_NATIVES,BACKUP_ADS_NATIVES);
                break;
            case "ALIEN-V":
                break;
        }
    }

    public static void ShowMediumBanner(Activity activity, RelativeLayout layAds) {
        switch (SELECT_MAIN_ADS) {
            case "ADMOB":
                AliendroidMediumBanner.MediumBannerAdmob(activity, layAds, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER, HIGH_PAYING_KEYWORD1,
                        HIGH_PAYING_KEYWORD2, HIGH_PAYING_KEYWORD3, HIGH_PAYING_KEYWORD4, HIGH_PAYING_KEYWORD5);
                break;
            case "APPLOVIN-M":
                AliendroidMediumBanner.MediumBannerApplovinMax(activity, layAds, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                break;
            case "APPLOVIN-M-NB":
                break;
            case "APPLOVIN-D":
                AliendroidMediumBanner.MediumBannerApplovinDis(activity, layAds, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                break;
            case "APPLOVIN-D-NB":
                break;
            case "MOPUB":
                AliendroidMediumBanner.MediumBannerMopub(activity, layAds, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                break;
            case "STARTAPP":
                AliendroidMediumBanner.MediumBannerStartApp(activity, layAds, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                break;
            case "IRON":
                AliendroidMediumBanner.MediumBannerIron(activity, layAds, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                break;
            case "FACEBOOK":
                AliendroidMediumBanner.MediumBannerFAN(activity, layAds, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                break;
            case "UNITY":
                AliendroidMediumBanner.MediumBannerUnity(activity, layAds, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                break;
            case "GOOGLE-ADS":
                AliendroidMediumBanner.MediumBannerGoogleAds(activity, layAds, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                break;
            case "ALIEN-M":
                AliendroidMediumBanner.MediumBannerAlienMediation(activity, layAds, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                break;
            case "ALIEN-V":
                //AliendroidBanner.SmallBannerAlienView(activity, layAds, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                break;
            case "WORTISE":
                AliendroidBanner.SmallBannerWortise(activity, layAds, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                break;
        }
    }
}

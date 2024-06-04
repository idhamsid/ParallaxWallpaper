package com.simplewallpaper.parallaxwallpaper.config;

public class SettingsAd {

    /*
    The basic guide to Re-skin, errors, tips, and tricks https://aliendro.id/help-center/dasar-reskin

    Please upload the JSON "Ads.json" file included in the zip folder, and paste it at the URL_DATA.
    JSON files can be uploaded to various online storage services such as:
    a. Hosting
    b. Google Drive : https://www.youtube.com/watch?v=Hq3uHI8LRI4&t=12s
    c. Firebase : https://www.youtube.com/watch?v=iBB-qjYTPtM&t=61s
    d. Github, etc

    Two methods of calling JSON, choose one:
    a. Using URLs (https://) URL_DATA ="https://aliendro.id/uploads/demo/walljson/wallpaperV7.json"
    b. Using the encryption method, please encrypt the Json link at https://aliendro.id/Encryption

    WARNING!!!
    - Using encryption must be accompanied by an Application/Package Name.
    - If the encrypted Package name is different, the application cannot be used
     */
    public static final String URL_DATA = "https://aliendro.id/uploads/demo/namabayi/guide.json"; // Ads dan Guide

    /*
    Data Setting:
    a. ON_OFF_DATA = "0" offline, data will be taken from Json in the Asset folder
    b. ON_OFF_DATA = "1" online, data will be taken from the internet,
    For online mode, please upload the json file and fill in URL_DATA
     */
    public static String ON_OFF_DATA = "1";

    /*
    Ads Setting:
    a. ON_OFF_ADS ="0" offline ads, all ad settings are in SettingsAlien.java
    b. ON_OFF_ADS ="1" online ads, all ads can be managed via json files
    For online mode, please upload the json file and fill in URL_DATA
     */
    public static String ON_OFF_ADS = "0";

    /*
    Main Ads:
    All ads use mediation and Open Bidding for Facebook.
    Select one as the main ad :
    a. SELECT_MAIN_ADS="ADMOB" Mediation and Open Bidding
    b. SELECT_MAIN_ADS="APPLOVIN-M" Mediation and Open Bidding
    c. SELECT_MAIN_ADS="APPLOVIN-D" Waterfall and use Zone ID
    d. SELECT_MAIN_ADS="STARTAPP" Mediation and initialize SDK
    e. SELECT_MAIN_ADS="FACEBOOK" Waterfall
    f. SELECT_MAIN_ADS="UNITY" Mediation and initialize SDK
    g. SELECT_MAIN_ADS="ALIEN-V" Register, http://aliendroid.jambox.games/
    h. SELECT_MAIN_ADS="ALIEN-M" Google Ads Mediation, registration https://aliendroid.props.id/register

    */
    public static String SELECT_MAIN_ADS = "ADMOB";

    /*
    Backup Ads:
    All ads use mediation and Open Bidding for Facebook.
    Select one as the backup ad :

    a. SELECT_BACKUP_ADS="ADMOB" Mediation and Open Bidding
    b. SELECT_BACKUP_ADS="APPLOVIN-M" Mediation and Open Bidding
    c. SELECT_BACKUP_ADS="APPLOVIN-D" Waterfall and use Zone ID
    d. SELECT_BACKUP_ADS="STARTAPP" Mediation and initialize SDK
    e. SELECT_BACKUP_ADS="FACEBOOK" Waterfall
    f. SELECT_BACKUP_ADS="UNITY" Mediation and initialize SDK
    g. SELECT_BACKUP_ADS="ALIEN-V" Register, http://aliendroid.jambox.games/
    h. SELECT_MAIN_ADS="ALIEN-M" Google Ads Mediation, registration https://aliendroid.props.id/register
    i. SELECT_BACKUP_ADS ="null" If the ad is not used
    j. Applovin-D and Applovin-M can't backup Alien-V
    k. Admob can't backup Alien-M
     */
    public static String SELECT_BACKUP_ADS = "ALIEN-V";

    /*
    Settings for Admob and Applovin :
    Please change the ads ID in string.xml
    a. Admob, change <string name="admob_app_id">YOUR_ADS_ID</string>
    b. Applovin and jambox, change <string name="sdk_key_applovin">YOUR_SDK_ADS</string>
    */

    /*
    Placement Ads ID:
    for Admob, Facebook, Unity Ads, Applovin-D, Applovin-M, Alien-V and Alien-M
    */

    public static String MAIN_ADS_INTERTITIAL = "ca-app-pub-3940256099942544/1033173712";
    public static String MAIN_ADS_BANNER = "ca-app-pub-3940256099942544/6300978111";


    public static String BACKUP_ADS_INTERTITIAL = "test-rewarded";
    public static String BACKUP_ADS_BANNER = "test-banner";
    public static String MAIN_ADS_NATIVES ="ca-app-pub-3940256099942544/2247696110";
    public static String BACKUP_ADS_NATIVES ="PL350911184828";

    /*
    open ads only for Admob, Applovin-M, Alien-V and Alien-M as main ads
     */
    public static String OPEN_APP_ADS = "ca-app-pub-3940256099942544/9257395921";


        /*
        a. Game Ads only for ALIEN-V, please register http://aliendroid.jambox.games/
        b  please change sdk ads multy ads to sdk games Ads, open build.gradle(Module)
        c. change from implementation 'com.github.aliendroid-kim:AlienMultyAds:Proxima-xx'
        d. to implementation 'com.github.aliendroid-kim:AdapterGameAds:Titan-xx'
        e. check SDK Titan-xx version, open https://aliendro.id/help-center/dasar-reskin/sdk-games-ads
        */
    public static String ON_GAME_ADS = "1";


    /*
    Initialize sdk:
    for Alien-V use "CHANNEL ID", StartApp use "APP_ID" and Unity Ads use "GAME_ID"
    INITIALIZE_MAIN_SDK = "null" INITIALIZE_SDK_BACKUP_ADS =     "null" if you don't use Alien-V, StartApp or Unity ads
     */
    public static String INITIALIZE_MAIN_SDK = "0079000d-b42b-4e89-a1f8-7bf22f276ec6";
    public static String INITIALIZE_SDK_BACKUP_ADS = "a7c1ef20-ef2f-4fa7-9dde-1e923c6c3d51";


    /*
    High paying keyword only for Admob
    Using HPK will display ads with a high CPM, with the risk that ads will appear infrequently
     */
    public static String HIGH_PAYING_KEYWORD1 = "Insurance";
    public static String HIGH_PAYING_KEYWORD2 = "null";
    public static String HIGH_PAYING_KEYWORD3 = "null";
    public static String HIGH_PAYING_KEYWORD4 = "null";
    public static String HIGH_PAYING_KEYWORD5 = "null";

    /*
    Interval for Interstitial Ad
   */
    public static int INTERVAL = 0; //change number

    /*
    GDPR settings for family ads
    */
    public static boolean CHILD_DIRECT_GDPR = true;

    /*
     Ad filtering :
     1 = TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE / Active
     2 = TAG_FOR_CHILD_DIRECTED_TREATMENT_FALSE / Deactive
     3 = TAG_FOR_CHILD_DIRECTED_TREATMENT_UNSPECIFIED / Not specified
     4 = MAX_AD_CONTENT_RATING_G / Gerneral / Families (primarily child-directed)
     5 = MAX_AD_CONTENT_RATING_PG / Families (mixed audience)
     6 = MAX_AD_CONTENT_RATING_T / Mature
     7 = MAX_AD_CONTENT_RATING_MA / Mature
    */
    public static String TARGETING_ADS = "1";
}


package com.simplewallpaper.parallaxwallpaper.config;

public class SettingsApp {

    /*
    JSON_OR_API_MODE is used to configure the data source.
    Use 1 for data from offline or online JSON,
    Use 2 for using the Blogger Rest API / data from Blogspot.
    */
    public static final String URL_JSON_NAME = "https://aliendro.id/uploads/demo/namabayi/name.json";




    /*
    OneSignal is used to send messages to all application users. Register at https://onesignal.com/
    Please follow the guide at https://documentation.onesignal.com/docs/generate-a-google-server-api-key
    or you can check the video at https://www.youtube.com/watch?v=yPlFAvTR-Vo&t=13s
    Enter the API Key into ONE_SIGNAL_API_KEY, and if not used, please set it to null
    */
    public static String ONE_SIGNAL_API_KEY = "535dc774-9fe3-44ae-839e-09e4133aebe9";


    /*
    Redirect App:
    - only for online mode with JSON
    - disable the app and redirect to the new app link
    - use STATUS_APP = "1" if app is suspended
    - fill LINK_REDIRECT with the new app link
    */
    public static String STATUS_APP = "0";
    public static String LINK_REDIRECT = "https://play.google.com/store/apps/";
}

package com.simplewallpaper.parallaxwallpaper.adapter;

import androidx.annotation.Keep;

@Keep
public class MoreApp {

    private int id;
    private String name_app;
    private String image_url;
    private String app_url;


    public MoreApp() {

    }

    public MoreApp(int id, String jdl, String img, String ps) {
        this.id = id;
        this.name_app = jdl;
        this.image_url = img;
        this.app_url = ps;

    }

    public int getId() {
        return id;
    }

    public String getName_app() {
        return name_app;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getApp_url() {
        return app_url;
    }

}

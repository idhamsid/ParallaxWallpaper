package com.simplewallpaper.parallaxwallpaper.model;

public class Wallpaper {
    private String wallapper_name;
    private String image_url;

    public Wallpaper() {

    }


    public Wallpaper(String wn,  String iu) {
        this.wallapper_name = wn;
        this.image_url = iu;
    }


    public String getWallapper_name() {
        return wallapper_name;
    }

    public String getImage_url() {
        return image_url;
    }

}

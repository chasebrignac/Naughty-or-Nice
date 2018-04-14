package com.inducesmile.henry.listallinstalledapps;

import android.graphics.drawable.Drawable;

public class AppList {

    private String badNews;
    //private List<String> neutralNews;
    //private List<String> goodNews;
    private String name;
    Drawable icon;

    public AppList(String name, Drawable icon, String badNews) {
        this.name = name;
        this.icon = icon;
        this.badNews = badNews;
        //this.neutralNews = neutralNews;
        //this.goodNews = goodNews;
    }

    public String getName() {
        return name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public String getBadNews() {
        return badNews;
    }
}

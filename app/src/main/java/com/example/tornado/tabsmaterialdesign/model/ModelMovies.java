package com.example.tornado.tabsmaterialdesign.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tornado on 7/13/2018.
 */

public class ModelMovies {
    @SerializedName("title")
    private String title;

    @SerializedName("image")
    private String imageUrl;


    //constructor
    public ModelMovies(String title, String imageUrl) {
        this.title = title;
        this.imageUrl = imageUrl;
    }


    //getter & setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
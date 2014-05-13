package com.fer.ppij.beans;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by user00 on 4/6/14.
 */
public class Tour {
    protected int id;
    protected   String name;
    protected double rating = 0.0;
    protected   String description = "";


    public Tour(int id, String name, Double rating, String description){
        this.id = id;
        this.name = name;
        if(rating != null) {
            this.rating = rating;
        }
        if (description != null){
            this.description = description;
        }

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }


}

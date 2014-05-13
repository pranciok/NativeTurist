package com.fer.ppij.beans;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


/**
 * Created by user00 on 4/7/14.
 */
public class Location {

    protected String name;
    protected double latitude;
    protected double longitude;
    protected String pictureLocation;
    protected String audioLocation;
    protected String textInfo="";
    protected int idPlace;
    protected int id;


    public Location(int id,String name, double latitude, double longitude, String pictureLocation,
                    String audioLocation, String textInfo,int idPlace){

        this.id = id;
        this.name = name;
        this.idPlace = idPlace;
        this.latitude = latitude;
        this.longitude = longitude;
        if(pictureLocation != null) {
            this.pictureLocation = pictureLocation;
        }
        if(audioLocation != null) {
            this.audioLocation = audioLocation;
        }
        if (textInfo  != null) {
            this.textInfo = textInfo;
        }
    }

    public int getIdPlace() {
        return idPlace;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Bitmap getPicture() {
        Bitmap bitmap = null;
        if(pictureLocation != null ) {
            bitmap = BitmapFactory.decodeFile(pictureLocation);
        }

        return bitmap;
    }

    public String getAudioLocation() {
        return audioLocation;
    }

    public String getPictureLocation() {
        return pictureLocation;
    }

    public String getName() {
        return name;
    }

    public String getTextInfo() {
        return textInfo;
    }

    public int getId() {
        return id;
    }
}

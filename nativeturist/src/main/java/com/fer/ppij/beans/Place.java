package com.fer.ppij.beans;

/**
 * Created by user00 on 4/7/14.
 */
public class Place {

    protected double longitude;
    protected double latitude;
    protected double radius;
    protected   int idTour;
    protected int id;

    public Place(int id,double latitude, double longitude, int idTour,double radius){

        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.radius = radius;
        this.idTour = idTour;
    }

    public int getIdTour() {
        return idTour;
    }

    public int getId() {
        return id;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getRadius() {
        return radius;
    }
}

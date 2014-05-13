package com.fer.ppij.network;

import java.util.ArrayList;

import com.fer.ppij.beans.Tour;

/**
 * Created by user00 on 5/2/14.
 */
class Tours {
    private ArrayList<Tour> tours;
    private int count;
    private int success;

    public ArrayList<Tour> getTours() {
        return tours;
    }

    public void setTours(ArrayList<Tour> tours) {
        this.tours = tours;
    }
}

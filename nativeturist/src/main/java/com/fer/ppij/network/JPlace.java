package com.fer.ppij.network;

import com.fer.ppij.beans.Place;
import com.fer.ppij.db.DbPlaceAdapter;
import com.fer.ppij.nativeturist.NativeTuristApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user00 on 5/5/14.
 */
class JPlace extends Place {
    private ArrayList<JLocation> locations;

    public JPlace(int id,double latitude, double longitude, int idTour, double radius) {
        super(id,latitude, longitude, idTour, radius);
    }

    public ArrayList<JLocation> getLocations() {
        return this.locations;
    }

    long save(){
        DbPlaceAdapter dbPlaceAdapter = DbPlaceAdapter.getInstance();

        return  dbPlaceAdapter.save(this.id,this.latitude,this.longitude,this.idTour,this.radius);

    }
}

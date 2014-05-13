package com.fer.ppij.network;

import com.fer.ppij.beans.Tour;
import com.fer.ppij.db.DbTourAdapter;
import com.fer.ppij.nativeturist.NativeTuristApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user00 on 5/5/14.
 */
class JTour extends Tour{
   private ArrayList<JPlace> places;
    private int count;
    private int success;

    public JTour(int id, String name, Double rating, String description) {
        super(id, name, rating, description);
    }

    public ArrayList<JPlace> getPlaces() {
        return places;
    }

    public int getCount() {
        return count;
    }

    public int getSuccess() {
        return success;
    }

    long save(){
        DbTourAdapter dbTourAdapter = DbTourAdapter.getInstance();
        return dbTourAdapter.save(this.id,this.name,this.rating,this.description);

    }
}

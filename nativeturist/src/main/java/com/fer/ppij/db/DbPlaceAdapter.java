package com.fer.ppij.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fer.ppij.beans.Place;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user00 on 4/7/14.
 */
public class DbPlaceAdapter {

    private static DbPlaceAdapter ourInstance = null;
    private DbOpenHelper dbOpenHelper;
    private SQLiteDatabase db;

    private String[] allColumns = new String[] {DbOpenHelper.ID, DbOpenHelper.PLACE_LATITUDE, DbOpenHelper.PLACE_LONGITUDE,
            DbOpenHelper.PLACE_IDTOUR, DbOpenHelper.PLACE_RADIUS};



    public static DbPlaceAdapter getInstance(){
        if(ourInstance == null) {
            ourInstance = new DbPlaceAdapter();
        }
        return ourInstance;
    }

    private DbPlaceAdapter() {
        dbOpenHelper = new DbOpenHelper();
    }

    private void open() throws SQLException {
        this.db = dbOpenHelper.getWritableDatabase();
    }

    private void close() {
        dbOpenHelper.close();
    }


    public long save(int id, double latitude, double longitude, int idTour, double radius)     {
        long _id = -2;
        try {
            open();
            ContentValues values = new ContentValues();
            values.put(DbOpenHelper.ID,id);
            values.put(DbOpenHelper.PLACE_LATITUDE,latitude);
            values.put(DbOpenHelper.PLACE_LONGITUDE,longitude);
            values.put(DbOpenHelper.PLACE_IDTOUR,idTour);
            values.put(DbOpenHelper.PLACE_RADIUS,radius);
            _id = db.insert(DbOpenHelper.TABLE_PLACE, null, values);
            close();

        }catch (SQLException e){

        }
        return _id;
    }

    public ArrayList<Place> getPlaceAll(){

        ArrayList<Place> places = new ArrayList<Place>();

        try {
            open();
            Cursor cursor = db.query(DbOpenHelper.TABLE_PLACE  , allColumns , null, null,
                    null, null, null);
            if(cursor.getCount() <= 0) return  places;

            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(0);
                double latitude = cursor.getDouble(1);
                double longitude = cursor.getDouble(2);
                int idTour = cursor.getInt(3);
                double radius = cursor.getDouble(4);

                Place place = new Place(id,latitude,longitude,idTour,radius);
                places.add(place);
                cursor.moveToNext();
            }

            cursor.close();
            close();

        } catch (SQLException e) {
            e.printStackTrace();
        }



        return places;
    }



    public List<Place> getPlacesInTour(int idTour){

        ArrayList<Place> places = new ArrayList<Place>();

        try {
            open();
            Cursor cursor = db.query(DbOpenHelper.TABLE_PLACE  , allColumns ,
                    DbOpenHelper.PLACE_IDTOUR + " = " + idTour , null, null, null, null);
            if(cursor.getCount() <= 0) return  places;

            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(0);
                double latitude = cursor.getDouble(1);
                double longitude = cursor.getDouble(2);

                double radius = cursor.getDouble(4);

                Place place = new Place(id,latitude,longitude,idTour,radius);
                places.add(place);
                cursor.moveToNext();
            }

            cursor.close();
            close();

        } catch (SQLException e) {
            e.printStackTrace();
        }



        return places;
    }

}

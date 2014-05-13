package com.fer.ppij.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fer.ppij.beans.Location;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by user00 on 4/7/14.
 */
public class DbLocationAdapter {

    private static DbLocationAdapter ourInstance = null;
    private DbOpenHelper dbOpenHelper;
    private SQLiteDatabase db;

    private String[] allColumns = new String[] {DbOpenHelper.ID ,DbOpenHelper.LOCATION_NAME, DbOpenHelper.LOCATION_LATITUDE, DbOpenHelper.LOCATION_LONGITUDE,
            DbOpenHelper.LOCATION_PICTURE, DbOpenHelper.LOCATION_AUDIO, DbOpenHelper.LOCATION_TEXT_INFORMATION,
            DbOpenHelper.LOCATION_IDPLACE};



    public static DbLocationAdapter getInstance(){
        if(ourInstance == null) {
            ourInstance = new DbLocationAdapter();
        }
        return ourInstance;
    }

    private DbLocationAdapter() {
        dbOpenHelper = new DbOpenHelper();
    }

    private void open() throws SQLException {
        this.db = dbOpenHelper.getWritableDatabase();
    }

    private void close() {
        dbOpenHelper.close();
    }


    public long save(int id, String name, double latitude, double longitude, String pictureLocation, String audioLocation,
                     String textInfo, int idPlace) {
        long _id = -2;
        try {
            open();
            ContentValues values = new ContentValues();
            values.put(DbOpenHelper.ID,id);
            values.put(DbOpenHelper.LOCATION_NAME,name);
            values.put(DbOpenHelper.LOCATION_LATITUDE,latitude);
            values.put(DbOpenHelper.LOCATION_LONGITUDE,longitude);
            values.put(DbOpenHelper.LOCATION_PICTURE, pictureLocation);
            values.put(DbOpenHelper.LOCATION_AUDIO, audioLocation);
            values.put(DbOpenHelper.LOCATION_TEXT_INFORMATION,textInfo);
            values.put(DbOpenHelper.LOCATION_IDPLACE,idPlace);
            _id = db.insert(DbOpenHelper.TABLE_LOCATION, null, values);
            close();

        }catch (SQLException e){

        }
        return _id;
    }

    public ArrayList<Location> getLocationAll(){

        ArrayList<Location> locations = new ArrayList<Location>();

        try {
            open();
            Cursor cursor = db.query(DbOpenHelper.TABLE_LOCATION  , allColumns , null, null,
                    null, null, null);
            if(cursor.getCount() <= 0) return  locations;
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                double latitude = cursor.getDouble(2);
                double longitude = cursor.getDouble(3);
                String pictureLocation = cursor.getString(4);
                String audioLocation = cursor.getString(5);
                String textInfo = cursor.getString(6);
                int _idPlace = cursor.getInt(7);

                Location location = new Location(id,name,latitude,longitude,pictureLocation,audioLocation,textInfo,_idPlace);
                locations.add(location);
                cursor.moveToNext();
            }

            cursor.close();
            close();

        } catch (SQLException e) {
            e.printStackTrace();
        }



        return locations;
    }

    /*
    @return location sa njezinim podacima koji su u bazi if true
    @return null if false
     */
    public Location getLocationInPlace( int idPlace){

        Location location = null;

        try {
            open();
            Cursor cursor = db.query(DbOpenHelper.TABLE_LOCATION  , allColumns ,
                    DbOpenHelper.LOCATION_IDPLACE + "=" + idPlace , null, null, null, null);
            if(cursor.getCount() <= 0) return null;
            cursor.moveToFirst();


            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            double latitude = cursor.getDouble(2);
            double longitude = cursor.getDouble(3);
            String pictureLocation = cursor.getString(4);
            String audioLocation = cursor.getString(5);
            String textInfo = cursor.getString(6);
            int _idPlace = cursor.getInt(7);

            location = new Location(id,name,latitude,longitude,pictureLocation,audioLocation,textInfo,_idPlace);


            cursor.close();
            close();

        } catch (SQLException e) {
            e.printStackTrace();
        }



        return location;
    }


}

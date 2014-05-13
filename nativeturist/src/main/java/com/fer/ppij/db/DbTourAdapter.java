package com.fer.ppij.db;

import java.sql.SQLException;
import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.fer.ppij.beans.Tour;

/**
 * Created by user00 on 4/6/14.
 */
public class DbTourAdapter {

    private static DbTourAdapter ourInstance = null;
    private DbOpenHelper dbOpenHelper;
    private SQLiteDatabase db;

    private String[] allColumns = new String[] { DbOpenHelper.ID, DbOpenHelper.TOUR_NAME,
            DbOpenHelper.TOUR_RATING, DbOpenHelper.TOUR_DESCRIPTION};



    public static DbTourAdapter getInstance(){
        if(ourInstance == null) {
            ourInstance = new DbTourAdapter();
        }
        return ourInstance;
    }

    private DbTourAdapter() {
        dbOpenHelper = new DbOpenHelper();
    }

    private void open() throws SQLException {
        this.db = dbOpenHelper.getWritableDatabase();
    }

    private void close() {
        dbOpenHelper.close();
    }


    public long save(int id,String name, Double rating, String description)     {
        long _id = -2;
        try {
            open();
            ContentValues values = new ContentValues();
            values.put(DbOpenHelper.ID,id);
            values.put(DbOpenHelper.TOUR_NAME,name);
            values.put(DbOpenHelper.TOUR_RATING,rating);
            values.put(DbOpenHelper.TOUR_DESCRIPTION,description);
            _id = db.insert(DbOpenHelper.TABLE_TOUR, null, values);
            close();

        }catch (SQLException e){

        }
        return _id;
    }

    public ArrayList<Tour> getTourAll(){

        ArrayList<Tour> tours = new ArrayList<Tour>();

        try {
            open();
            Cursor cursor = db.query(DbOpenHelper.TABLE_TOUR, allColumns , null, null,
                    null, null, null);

            if(cursor.getCount() <= 0) return  tours;
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                Integer id = cursor.getInt(0);
                String name = cursor.getString(1);
                double rating = cursor.getDouble(2);
                String description = cursor.getString(3);


                Tour tour = new Tour(id,name,rating,description);
                tours.add(tour);
                cursor.moveToNext();
            }

            cursor.close();
            close();

        } catch (SQLException e) {
            e.printStackTrace();
        }



        return tours;
    }

}

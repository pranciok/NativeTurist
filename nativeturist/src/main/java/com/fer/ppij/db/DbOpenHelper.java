package com.fer.ppij.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fer.ppij.nativeturist.NativeTuristApplication;

/**
 * Created by user00 on 4/4/14.
 */
public class DbOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "nativetouristdatabase.db";
    private static final int VERSION = 1;

    public static final String ID = "id";

    public static final String TOUR_PICTURE = "picture";
    public static final String TOUR_NAME = "name";
    public static final String TOUR_DESCRIPTION = "description";
    public static final String TOUR_RATING = "rating";

    public static final String LOCATION_NAME = "name";
    public static final String LOCATION_LONGITUDE = "longitude";
    public static final String LOCATION_LATITUDE = "latitude";
    public static final String LOCATION_PICTURE = "picture";
    public static final String LOCATION_AUDIO = "audio";
    public static final String LOCATION_TEXT_INFORMATION = "textinfo";
    public static final String LOCATION_IDPLACE = "idplace";

    public static final String PLACE_LONGITUDE = "longitude";
    public static final String PLACE_LATITUDE = "latitude";
    public static final String PLACE_IDTOUR = "idtour";
    public static final String PLACE_RADIUS = "radius";




    public static final String TABLE_TOUR = "tour";
    public static final String TABLE_LOCATION = "location";
    public static final String TABLE_PLACE = "place";

    private static final String createTourTable = "CREATE TABLE " + TABLE_TOUR
            + "( " + ID + " INTEGER PRIMARY KEY , "
            + TOUR_NAME + " VARCHAR(64) NOT NULL , "
            + TOUR_RATING + " REAL , "
            + TOUR_DESCRIPTION + " TEXT "
            +");";


    private static final String createLocationTable = "CREATE TABLE " + TABLE_LOCATION
            + "(" + ID + " INTEGER PRIMARY KEY ,  "
            + LOCATION_NAME + " VARCHAR(64) NOT NULL, "
            + LOCATION_LATITUDE + " REAL NOT NULL , "
            + LOCATION_LONGITUDE + " REAL NOT NULL , "
            + LOCATION_PICTURE + " VARCHAR(256) , "
            + LOCATION_AUDIO + " VARCHAR(256) , "
            + LOCATION_TEXT_INFORMATION + " TEXT , "
            + LOCATION_IDPLACE + " INTEGER NOT NULL "
            +");";


    private static final String createPlaceTable = "CREATE TABLE " + TABLE_PLACE
            + "(" + ID + " INTEGER PRIMARY KEY ,  "
            + PLACE_LATITUDE + " REAL NOT NULL , "
            + PLACE_LONGITUDE + " REAL NOT NULL , "
            + PLACE_IDTOUR + " INTEGER NOT NULL , "
            + PLACE_RADIUS + " REAL NOT NULL "
            +");";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTourTable);
        db.execSQL(createLocationTable);
        db.execSQL(createPlaceTable);
    }


    public DbOpenHelper() {
        super(NativeTuristApplication.getInstance().getAppContext(), DATABASE_NAME, null, VERSION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TOUR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLACE);
        onCreate(db);
    }
}
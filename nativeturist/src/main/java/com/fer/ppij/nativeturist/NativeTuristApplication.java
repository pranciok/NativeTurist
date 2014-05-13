package com.fer.ppij.nativeturist;

import android.app.Application;
import android.content.Context;

/**
 * Created by user00 on 4/6/14.
 */
public class NativeTuristApplication extends Application {

    private static NativeTuristApplication instance = null;
    private static Context _context;

    public static String NativeTouristPath = "/sdcard/nativetourist/";

    public static String ToursURL = "http://marvin.kset.org/~pyro/ppijserver/get_tours.php";
    public static String TourDownloadURL = "http://marvin.kset.org/~pyro/ppijserver/get_tour.php";

    public void onCreate(){
        super.onCreate();
        NativeTuristApplication._context = getApplicationContext();
    }

    public static NativeTuristApplication getInstance(){
        if(instance == null){
            instance = new NativeTuristApplication();
        }
        return instance;
    }

    public static Context getAppContext() {
        return NativeTuristApplication._context;
    }
}

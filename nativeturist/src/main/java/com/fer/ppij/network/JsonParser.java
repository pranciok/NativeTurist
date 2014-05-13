package com.fer.ppij.network;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;


/**
 * Created by user00 on 5/2/14.
 */
class JsonParser {


    public static Tours parseTours (InputStream is){

        String doc = getStringFromInputStream(is);

        Log.d("json: ", "A: " + doc);

        return new Gson().fromJson(doc, Tours.class);

    }

    public static JTour parseJTour (InputStream is){

        String doc = getStringFromInputStream(is);

        Log.d("json: ", "A: " + doc);

        return new Gson().fromJson(doc, JTour.class);

    }


    public static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }
}

package com.fer.ppij.network;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import com.fer.ppij.adapterlist.TourAdapter;
import com.fer.ppij.nativeturist.DownloadActivity;
import com.fer.ppij.nativeturist.NativeTuristApplication;
import com.fer.ppij.nativeturist.R;

/**
 * Created by user00 on 5/6/14.
 */
public class LoadTours extends AsyncTask<String, Void, String> {

    private Context context;
    private boolean loadingTours;
    private ProgressDialog pDialog;
    private Tours tours;
    private DownloadActivity activity;


    public LoadTours(Context context, DownloadActivity activity){
        this.context = context;
        this.activity = activity;
        loadingTours=true;
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Downloading tour. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        try {
            pDialog.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(String... arg0) {

        HttpResponse response=null;
        HttpClient dhc = new DefaultHttpClient();
        HttpPost request = new HttpPost(NativeTuristApplication.ToursURL);
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
        // nameValuePairs.add(new BasicNameValuePair("timestamp", ""+System.currentTimeMillis()));
        try {
            request.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            response=dhc.execute(request);

            tours= JsonParser.parseTours(response.getEntity().getContent());



        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected void onPostExecute(String file_url) {

        //TODO ovdje updejtaj listu za ture

        if(pDialog.isShowing())
            pDialog.dismiss();

        ListView toursList = (ListView) activity.findViewById(R.id.tours_list);

        toursList.setAdapter(new TourAdapter(context, tours.getTours()));


        //   Intent intent = new Intent(LoginActivity.this,NewQuizzesActivity.class);
        // startActivity(intent);
        loadingTours=false;
//            finish();

    }



}

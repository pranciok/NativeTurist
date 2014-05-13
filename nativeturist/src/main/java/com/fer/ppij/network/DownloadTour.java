package com.fer.ppij.network;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import com.fer.ppij.nativeturist.NativeTuristApplication;

/**
 * Created by user00 on 5/6/14.
 */
public class DownloadTour extends AsyncTask<String, Void, String> {

    private Context context;
    private boolean loadingTour;
    private ProgressDialog pDialog;
    private JTour jTour;
    private int downloadId;

    public DownloadTour(Context context, int downloadId){
        this.context = context;
        this.downloadId = downloadId;
        loadingTour=true;
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

        String tourName;
        JLocation jLocation;
        String fileName;


        HttpResponse response=null;
        HttpClient dhc = new DefaultHttpClient();
        HttpPost request = new HttpPost(NativeTuristApplication.TourDownloadURL);
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
        nameValuePairs.add(new BasicNameValuePair("id", Integer.toString(downloadId)));
        try {
            request.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            response=dhc.execute(request);

            jTour= JsonParser.parseJTour(response.getEntity().getContent());

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tourName = jTour.getName();
        File path_file = new File(NativeTuristApplication.NativeTouristPath +tourName);
        if(!path_file.exists()){
            path_file.mkdirs();
        }
        for(JPlace place : jTour.getPlaces()){
            // TODO spremi place
            jLocation = place.getLocations().get(0);
            try {
                fileName = new File(jLocation.getServerAudio()).getName();
                downloadFile(jLocation.getServerAudio(),NativeTuristApplication.NativeTouristPath +tourName+"/"+ fileName);
                jLocation.setAudioLocation(NativeTuristApplication.NativeTouristPath +tourName+"/"+ fileName);

                fileName = new File(jLocation.getServerPicture()).getName();
                downloadFile(jLocation.getServerPicture(),NativeTuristApplication.NativeTouristPath +tourName +"/"+ fileName);
                jLocation.setPictureLocation(NativeTuristApplication.NativeTouristPath +tourName+"/"+ fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        save(jTour);

        return null;
    }

    protected void onPostExecute(String file_url) {
        if(pDialog.isShowing())
            pDialog.dismiss();

        //   Intent intent = new Intent(LoginActivity.this,NewQuizzesActivity.class);
        // startActivity(intent);
        loadingTour=false;
//            finish();

    }

    private void save(JTour jTour){
        //mogao bi rijesiti sa exceptionima al nisam siguran jel bi bilo bolje ili ne :/
        if(jTour.save()>0){
            for(JPlace jPlace : jTour.getPlaces()){
                if(jPlace.save() > 0){
                    //u principu bi trebala biti samo jedna lokacija u placu
                    jPlace.getLocations().get(0).save();
                }
            }
        }

    }

    private void downloadFile(String url, String dest_file_path) throws IOException {

        File dest_file = new File(dest_file_path);


        if(!dest_file.exists()){
            URL u = new URL(url);
            URLConnection conn = u.openConnection();
            int contentLength = conn.getContentLength();
            DataInputStream stream = new DataInputStream(u.openStream());
            byte[] buffer = new byte[contentLength];
            stream.readFully(buffer);
            stream.close();
            DataOutputStream fos = new DataOutputStream(new FileOutputStream(dest_file));
            fos.write(buffer);
            fos.flush();
            fos.close();
        }

    }



}


package com.fer.ppij.nativeturist;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class HTTPTest extends Activity {
    String dwnload_file_path = "http://marvin.kset.org/~pyro/server/sponzor/test/pic/logo.png";
    String dest_file_path = "/sdcard/dwnloaded_file.png";

    String dwnload_file_path2 = "http://marvin.kset.org/~pyro/server/sponzor/test/enteprise.mp3";
    String dest_file_path2 = "/sdcard/dwnloaded_music.mp3";


    Button b1;
    ProgressDialog dialog = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_httptest);

        b1 = (Button)findViewById(R.id.Button01);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog = ProgressDialog.show(HTTPTest.this, "", "Downloading file...", true);
/*                new Thread(new Runnable() {
                    public void run() {
                        downloadFile(dwnload_file_path, dest_file_path);
                    }
                }).start();

                ImageView pic = (ImageView) findViewById(R.id.downloadedPic);
                Bitmap bitmap = BitmapFactory.decodeFile(dest_file_path);
                pic.setImageBitmap(bitmap);
*/
                new Thread(new Runnable() {
                    public void run() {
                        downloadFile(dwnload_file_path2, dest_file_path2);
                    }
                }).start();

                MediaPlayer mp = new MediaPlayer();
                try {
                    mp.setDataSource(dest_file_path2);
                    mp.prepare();
                    mp.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
    }

    public void downloadFile(String url, String dest_file_path) {
        try {
            File dest_file = new File(dest_file_path);
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
            hideProgressIndicator();

        } catch(FileNotFoundException e) {
            hideProgressIndicator();
            return;
        } catch (IOException e) {
            hideProgressIndicator();
            return;
        }
    }

    void hideProgressIndicator(){
        runOnUiThread(new Runnable() {
            public void run() {
                dialog.dismiss();
            }
        });
    }
}
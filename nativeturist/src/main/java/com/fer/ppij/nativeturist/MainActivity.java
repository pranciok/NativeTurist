package com.fer.ppij.nativeturist;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends ActionBarActivity {


    MediaPlayer mp = new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void locateMe(View v){
       // Intent download = new Intent(this, SelectTour.class);
       // startActivity(download);
    }
    public void myTours (View v){
        Intent download = new Intent(this, SelectTour.class);
        startActivity(download);
    }
    public void searchTours(View v){
        Intent download = new Intent(this, DownloadActivity.class);
        startActivity(download);
    }
    public void settings(View v){
       // Intent download = new Intent(this, SelectTour.class);
       // startActivity(download);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

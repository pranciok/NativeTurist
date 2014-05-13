package com.fer.ppij.nativeturist;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.fer.ppij.beans.Location;
import com.fer.ppij.db.DbLocationAdapter;

import java.io.IOException;

public class LocationPlayAudio extends ActionBarActivity {


    private MediaPlayer mp = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_play_audio);

        Intent intent = getIntent();
        int idPlace = intent.getIntExtra("idPlace",0);
        DbLocationAdapter dbLocationAdapter = DbLocationAdapter.getInstance();

        Location location = dbLocationAdapter.getLocationInPlace(idPlace);

        try {
            mp.setDataSource(location.getAudioLocation());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.location_play_audio, menu);
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

    public void onClickPlayAudio(View v){
        if(mp.isPlaying()){
            mp.stop();
        }else {
            try {
                mp.prepare();
                mp.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}

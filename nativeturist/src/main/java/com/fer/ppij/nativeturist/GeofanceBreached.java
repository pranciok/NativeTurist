package com.fer.ppij.nativeturist;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class GeofanceBreached extends ActionBarActivity {

    private int idPlace;
    private int idTure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geofance_breached);
        Intent intent = getIntent();
        idPlace = intent.getIntExtra("idPlace",0);
        idTure = intent.getIntExtra("idTure",0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.geofance_breached, menu);
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

    public void onClickLocationDisplayText(View v){
        Intent intent = new Intent(this,LocationDisplayText.class);
        intent.putExtra("idPlace",idPlace);
        this.startActivity(intent);
    }

    public void onClickLocationPlayAudio(View v){
        Intent intent = new Intent(this,LocationPlayAudio.class);
        intent.putExtra("idPlace",idPlace);
        this.startActivity(intent);
    }

    public void onClickDisplayMap(View v){
        Intent intent = new Intent(this, NativeTouristMap.class);
        intent.putExtra("idTure", idTure);
        intent.putExtra("idPlace", idPlace);
        this.startActivity(intent);
    }


}

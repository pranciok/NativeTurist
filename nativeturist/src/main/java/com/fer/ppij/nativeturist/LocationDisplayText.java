package com.fer.ppij.nativeturist;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.fer.ppij.beans.Location;
import com.fer.ppij.db.DbLocationAdapter;

public class LocationDisplayText extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_display_text);

        Intent intent = getIntent();
        int idPlace = intent.getIntExtra("idPlace",0);
        DbLocationAdapter dbLocationAdapter = DbLocationAdapter.getInstance();

        Location location = dbLocationAdapter.getLocationInPlace(idPlace);

        TextView name = (TextView) findViewById(R.id.location_display_name);
        ImageView image = (ImageView) findViewById(R.id.location_display_picture);
        TextView text = (TextView) findViewById(R.id.location_display_text);

        name.setText(location.getName());
        image.setImageBitmap(location.getPicture());
        text.setText(location.getTextInfo());


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.location_display_text, menu);
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

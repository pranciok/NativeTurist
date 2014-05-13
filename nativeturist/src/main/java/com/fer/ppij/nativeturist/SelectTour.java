package com.fer.ppij.nativeturist;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import com.fer.ppij.adapterlist.SelectTourAdapter;
import com.fer.ppij.db.DbTourAdapter;

public class SelectTour extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_tour);

        DbTourAdapter dbTourAdapter = DbTourAdapter.getInstance();
        ListView toursList = (ListView) findViewById(R.id.select_tour_list);
        dbTourAdapter.getTourAll();
        toursList.setAdapter(new SelectTourAdapter(this, dbTourAdapter.getTourAll()));



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.select_tour, menu);
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

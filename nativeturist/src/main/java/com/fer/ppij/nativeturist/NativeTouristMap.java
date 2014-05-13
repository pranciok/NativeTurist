package com.fer.ppij.nativeturist;

import java.util.List;
import java.util.Stack;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;
import com.fer.ppij.beans.Place;
import com.fer.ppij.db.DbPlaceAdapter;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class NativeTouristMap extends Activity implements LocationListener{

    private GoogleMap googleMap;
    private LocationManager locationManager;
    private static final long MIN_TIME = 200;
    private static final float MIN_DISTANCE = (float)0.3;
    String provider;
    private LatLng latLng;
    private List<Place> myPlaces;
    private int idTure;
    private int idPlacePlayed = -2;
    private int idLockedBy = -1;
    private boolean Zastavica = false;
    static Stack stog = new Stack();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Intent parentIntent = getIntent();
        idTure = parentIntent.getIntExtra("idTure",0);

        idPlacePlayed = parentIntent.getIntExtra("idPlace",-1);

        if(idPlacePlayed != -1) stog.pop();
        else stog.push(Integer.valueOf(-2));
        Zastavica = false;

        myPlaces = DbPlaceAdapter.getInstance().getPlacesInTour(idTure);

        initializeMap();
        googleMap.setMyLocationEnabled(true);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        boolean enabledGPS = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!enabledGPS) {
            Toast.makeText(this, "GPS signal not found", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }
        provider = LocationManager.GPS_PROVIDER.toString();
        Location location = locationManager.getLastKnownLocation(provider);

        if (location != null) {
            this.onLocationChanged(location);
        }
        locationManager.requestLocationUpdates(provider, MIN_TIME, MIN_DISTANCE, this);

        putPlaces();
    }

    private void putPlaces(){
        for(Place myPlace : myPlaces){

            MarkerOptions marker = new MarkerOptions()
                .position(new LatLng(myPlace.getLatitude(), myPlace.getLongitude()))
                .title("10m oko mene");

            CircleOptions circle = new CircleOptions()
                .center( new LatLng(myPlace.getLatitude(), myPlace.getLongitude()) )
                .radius(myPlace.getRadius())
                .fillColor(0x40ff0000)
                .strokeColor(Color.TRANSPARENT)
                .strokeWidth(2);

            googleMap.addMarker(marker);
            googleMap.addCircle(circle);
        }
    }
    private void initializeMap(){
        if (googleMap == null){
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            if(googleMap == null){
                Toast.makeText(getApplicationContext(), "Greska ne mogu stvoriti kartu!", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onLocationChanged(Location location) {
        latLng = new LatLng(location.getLatitude(), location.getLongitude());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 17);
        googleMap.animateCamera(cameraUpdate);
        for (Place myPlace : myPlaces){
            Location pointLocation = new Location("POINT_LOCATION");
            pointLocation.setLatitude( myPlace.getLatitude());
            pointLocation.setLongitude(myPlace.getLongitude());
            if (pointLocation!=null){
                float distance = location.distanceTo(pointLocation);

                if (distance < (float)myPlace.getRadius()){
                    if (stog.peek().equals(Integer.valueOf(-1))){
                        if(!stog.contains(Integer.valueOf(myPlace.getId())) && !Zastavica){
                            Zastavica = true;
                            Toast.makeText(this,
                                    "Trazim sviranje! ID:"+myPlace.getId(), Toast.LENGTH_LONG).show();
                        }
                    }
                    else if(!stog.contains(Integer.valueOf(myPlace.getId()))){
                            stog.push(Integer.valueOf(myPlace.getId()));
                            stog.push(Integer.valueOf(-1));
                            Intent intent = new Intent(this, GeofanceBreached.class);
                            intent.putExtra("idPlace",myPlace.getId());
                            intent.putExtra("idTure", idTure);
                            this.startActivity(intent);
                            Toast.makeText(this,
                                    "Probio Geofence! ID:"+myPlace.getId(), Toast.LENGTH_LONG).show();
                    }
                }

//                if (distance < 10000){ //(float)myPlace.getRadius()
//                    if (idPlacePlayed != -1 && idPlacePlayed != myPlace.getId()){
//                        idPlacePlayed = -1;
//                        idLockedBy = myPlace.getId();
//                        Toast.makeText(this,
//                            "Probio Geofence!"+myPlace.getId(), Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(this, GeofanceBreached.class);
//                        intent.putExtra("idPlace",myPlace.getId());
//                        intent.putExtra("idTure", idTure);
//                        this.startActivity(intent);
//                    }
//                    else if(idPlacePlayed == -1 && idLockedBy != myPlace.getId() && !Zastavica){
//                        Toast.makeText(this,
//                                "Trazim sviranje! ID:"+myPlace.getId(), Toast.LENGTH_LONG).show();
//                        Zastavica = true;
//                    }
//                }
//                else {
//                   if(idPlacePlayed == myPlace.getId()) idPlacePlayed = -2;
//                }
            }
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) { }

    @Override
    public void onProviderEnabled(String s) {
        stog.clear();
        Intent intent = new Intent(this, NativeTouristMap.class);
        intent.putExtra("idTure", idTure);
        this.startActivity(intent);
    }

    @Override
    public void onProviderDisabled(String s) {
        stog.clear();
    }
}
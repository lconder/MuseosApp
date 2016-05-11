package com.lconde.museosapp.Activities;

import android.content.Context;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.lconde.museosapp.R;

public class locationActivity extends AppCompatActivity
{
    Toolbar toolbar;
    GoogleMap map;
    LocationManager mlocManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        final Bundle extras = getIntent().getExtras();

        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SupportMapFragment spmp = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        map=spmp.getMap();

        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        map.setMyLocationEnabled(true);

        mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(18.997264, -98.203131)));

        if (Build.VERSION.SDK_INT < 19)
        {
            FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
            statusBar.setVisibility(View.GONE);
        }

        map.addMarker(new MarkerOptions()
                .position(new LatLng(Double.parseDouble(extras.getString("latitud")),Double.parseDouble(extras.getString("longitud"))))
                .title(extras.getString("nombre")).icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker)));

    }

}

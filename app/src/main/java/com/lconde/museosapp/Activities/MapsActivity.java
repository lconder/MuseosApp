package com.lconde.museosapp.Activities;

import android.content.Context;
import android.location.LocationManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.support.v7.widget.Toolbar;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.lconde.museosapp.Museo;
import com.lconde.museosapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity {

    private GoogleMap mMap;
    Toolbar toolbar;
    GoogleMap map;
    LocationManager mlocManager;
    private List<Museo> museos;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        museos = new ArrayList<Museo>();

        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SupportMapFragment spmp = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        map=spmp.getMap();

        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.setMyLocationEnabled(true);

        mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        // LocationListener mlocListener = new MyLocationListener();
        //mlocManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 0, 0, mlocListener);

        try {
            readJson();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        createMarkers();

        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(18.997264, -98.203131)));

        if (Build.VERSION.SDK_INT < 19) {
            FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
            statusBar.setVisibility(View.GONE);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void readJson() throws IOException, JSONException
    {
        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File(sdcard,"cache.json");
        StringBuilder jsonBuilder = new StringBuilder();

        BufferedReader jsonReader = new BufferedReader(new FileReader(file));

        for (String line = null; (line = jsonReader.readLine()) != null;)
        {
            jsonBuilder.append(line).append("\n");
        }
        JSONTokener tokener = new JSONTokener(jsonBuilder.toString());
        JSONArray jsonArray = new JSONArray(tokener);

        for(int i=0;i<jsonArray.length();i++)
        {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String nombre =jsonObject.getString("nombre");
            String imagen = jsonObject.getString("imagen");
            String latitud = jsonObject.getString("latitud");
            String longitud = jsonObject.getString("longitud");
            int id = jsonObject.getInt("id");
            Museo temp = new Museo(nombre, id, imagen,latitud,longitud);
            museos.add(temp);
            temp = null;
        }

        System.out.println("Archivo cache: " + jsonBuilder);
    }

    public void createMarkers()
    {
        for(Museo i: museos)
        {
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(Double.parseDouble(i.getLatitud()),Double.parseDouble(i.getLongitud())))
                    .title(i.getNombre()).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));
        }
    }
}

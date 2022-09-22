package com.example.phonelocator;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.example.phonelocator.model.Location;
import com.example.phonelocator.model.ResponseModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.phonelocator.databinding.ActivityMapsBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    Date d;
    SimpleDateFormat formatIncoming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap)  {


        mMap = googleMap;
        LatLng sydney = null;

        for (Location location : ResponseModel.locations) {
            System.out.println(location.id);
            System.out.println(location.deviceId);
            // Add a marker in Sydney and move the camera
            sydney = new LatLng(location.latitude, location.longitude);
            d =new Date(location.created);
            formatIncoming =  new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
            SimpleDateFormat formatOutgoing = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            TimeZone tz = TimeZone.getTimeZone("Asia/Jakarta");
            formatOutgoing.setTimeZone(tz);
            String s = "" ;
            try {
              s = formatOutgoing.format(formatIncoming.parse(""+d));
                mMap.addMarker(new MarkerOptions().position(sydney).title((s)));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            } catch (ParseException e) {
                mMap.addMarker(new MarkerOptions().position(sydney).title((""+d)));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            }

        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,17.5f));
        if (ResponseModel.hybrid==true)
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

    }
}
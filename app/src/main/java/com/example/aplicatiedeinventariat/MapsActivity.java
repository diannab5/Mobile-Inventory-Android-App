package com.example.aplicatiedeinventariat;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
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
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in printsv and move the camera
        final LatLng printsv = new LatLng(44.438493, 26.106934);
        mMap.addMarker(new MarkerOptions().position(printsv).title("Printing Services"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(printsv));

        final LatLng copyPr = new LatLng(44.455015, 26.104275);
        mMap.addMarker(new MarkerOptions().position(copyPr).title("Copy Service"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(copyPr));

        final LatLng devCenter = new LatLng(44.443417, 26.118698);
        mMap.addMarker(new MarkerOptions().position(devCenter).title("Device Center"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(devCenter));

        final LatLng spw = new LatLng(44.432746, 26.145533);
        mMap.addMarker(new MarkerOptions().position(spw).title("SPW Automotive"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(spw));

    }
}

package com.example.caitlinyang.m4.controllers;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.caitlinyang.m4.R;
import com.example.caitlinyang.m4.model.Locations;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * MapActivity
 */
public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private List<Locations> locations;
    private final MapActivity mapActivity = this;
    private SupportMapFragment mapFragment;

    /**
     * onCreate method
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mDatabase.addValueEventListener(new ValueEventListener() {
            /**
             * onDataChange method
             * @param dataSnapshot DataSnapshot
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                locations = new ArrayList<>();
                DataSnapshot data = dataSnapshot.child("locations");
                Log.d("TEST", "hi");
                for (DataSnapshot snapshot : data.getChildren()) {
                    Locations location = snapshot.getValue(Locations.class);
                    locations.add(location);
                }
                mapFragment = (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);
                if (mapFragment != null) {
                    mapFragment.getMapAsync(mapActivity);
                }
            }
            /**
             * onCancelled method
             * @param databaseError DatabaseError
             */
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

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
        GoogleMap mMap;
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        for (Locations instanceLoc : locations) {
            LatLng instanceLatLng =
                    new LatLng(instanceLoc.getLatitude(), instanceLoc.getLongitude());
            mMap.addMarker(new MarkerOptions().position(instanceLatLng)
                    .title(instanceLoc.getLocationName() + '\n' + instanceLoc.getPhoneNumber()));
        }
        Locations instanceLoc = locations.get(0);
        LatLng instanceLatLng = new LatLng(instanceLoc.getLatitude(), instanceLoc.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLng(instanceLatLng));

    }
}

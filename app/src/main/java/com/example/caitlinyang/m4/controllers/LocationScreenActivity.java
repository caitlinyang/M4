package com.example.caitlinyang.m4.controllers;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.example.caitlinyang.m4.R;
import com.example.caitlinyang.m4.model.Locations;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * LocationScreenActivity
 */
public class LocationScreenActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView listView;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private DatabaseReference mDatabase;
    private HashMap<String, Object> user;
    private List<Locations> locations;
    private CustomAdapter customAdapter;
    private Locations instanceLoc;

    /**
     * onCreate method
     * @param savedInstanceState Bundle savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("TEST", "LocationScreen");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        drawerLayout = findViewById(R.id.drawer_view2);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        NavigationView navigationView = findViewById(R.id.nav_view2);
        navigationView.setNavigationItemSelectedListener(this);
        
        if (readLocationData()) {
            Log.d("TEST", "Locations logged");
        }
        listView = findViewById(R.id.locations_list);
        Intent intent = getIntent();
        if (intent.hasExtra("key")) {
            user = (HashMap<String, Object>) intent.getSerializableExtra("key");
            Log.d("Test", (String) user.get("name"));
        }
        mDatabase.addValueEventListener(new ValueEventListener() {
            /**
             * onDataChange method
             * @param dataSnapshot Datasnapshot
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
                customAdapter = new CustomAdapter();
                listView.setAdapter(customAdapter);
            }

            /**
             * onCancelled Method
             * @param databaseError databaseError
             */
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        Button mapButton = findViewById(R.id.viewMaps);
        mapButton.setOnClickListener(new View.OnClickListener() {
            /**
             * onClick method
             * @param v View v
             */
            @Override
            public void onClick(View v) {
                Intent map = new Intent(getBaseContext(), MapActivity.class);
                startActivity(map);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * onItemClick method
             * @param parent parent AdapterView
             * @param view View view
             * @param position position
             * @param id id
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listView.setSelected(true);
                Log.d("TEST", "" + position);
                instanceLoc = locations.get(position);
                Log.d("TEST", instanceLoc.getAddress());
                onViewButtonPressed();
            }
        });
    }

    /**
     * onNavigationItemSelected method
     * @param item MenuItem
     * @return boolean
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.hp:
                Intent main = new Intent(getBaseContext(), UserHomeActivity.class);
                main.putExtra("key", user);
                startActivity(main);
                break;
            case R.id.loc_list:
                Intent main2 = new Intent(getBaseContext(), LocationScreenActivity.class);
                main2.putExtra("key", user);
                startActivity(main2);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * onOptionsItemSelected method
     * @param item menu item
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return toggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    /**
     * CustomAdapter class
     */
    class CustomAdapter extends BaseAdapter {

        /**
         * getCount method
         * @return locations size
         */
        @Override
        public int getCount() {
            return locations.size();
        }

        /**
         * getItem method
         * @param position position
         * @return null
         */
        @Override
        public Object getItem(int position) {
            return null;
        }

        /**
         * getItemId method
         * @param position position
         * @return long
         */
        @Override
        public long getItemId(int position) {
            return 0;
        }

        /**
         * getView method
         * @param position position
         * @param convertView convertView
         * @param parent parent
         * @return view
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View newView = getLayoutInflater().inflate(R.layout.list_view_layout,parent, false);

            TextView locName = convertView.findViewById(R.id.textView_location_name);
            TextView address = convertView.findViewById(R.id.textView_address);
            TextView phoneNum = convertView.findViewById(R.id.textView_phone_number);

            locName.setText(locations.get(position).getLocationName());
            address.setText("Address: " + locations.get(position).getAddress());
            phoneNum.setText("Phone Number " + locations.get(position).getPhoneNumber());

            return newView;
        }
    }

    /**
     * onViewButtonPressed Method
     */
    private void onViewButtonPressed() {
        Intent intent = null;
        if ("Location Employee".equals(user.get("userType"))) {
            intent = new Intent(this, LocEmployeeLocationsActivity.class);
            intent.putExtra("location", instanceLoc);
        } else if ("User".equals(user.get("userType"))) {
            intent = new Intent(this, LocationActivity.class);
            intent.putExtra("location", instanceLoc);
            intent.putExtra("key", user);
        }
        startActivity(intent);
    }


    /**
     * readLocationData method
     * @return boolean
     */
    private boolean readLocationData() {

        InputStream inputStream = getResources().openRawResource(R.raw.locationdata);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, Charset.forName("UTF-8"))
        );
        String line = "";
        try {
            // Skip the Headers from csv file
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                Log.d("MyActivity", "Line: " + line);
                // Split by commas
                String[] tokens = line.split(",");
                Log.d("MyActivity", "token[0] " + tokens[0] + tokens[9]);

                // Read the data
                double lat = Double.parseDouble(tokens[2]);
                double lon = Double.parseDouble(tokens[3]);
                int key = Integer.parseInt(tokens[0]);

                mDatabase.child("locations").child(Integer.toString(key))
                        .setValue(new Locations(key, tokens[1], lon, lat, tokens[4],
                                tokens[8], tokens[9]));

            }
            reader.close();
            return true;
        } catch (IOException e) {
            Log.wtf("HomeScreenActivity", "Error reading data file on line " + line, e);
            return false;
        }
    }

}

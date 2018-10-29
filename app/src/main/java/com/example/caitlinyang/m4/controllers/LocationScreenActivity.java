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
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.example.caitlinyang.m4.R;
import com.example.caitlinyang.m4.model.DatabaseSingleton;
import com.example.caitlinyang.m4.model.Item;
import com.example.caitlinyang.m4.model.Locations;
import com.example.caitlinyang.m4.model.SimpleModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LocationScreenActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ListView listView;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private DatabaseReference mDatabase;
    private HashMap<String, Object> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_view2);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.nav_view2);
        navigationView.setNavigationItemSelectedListener(this);
        
        readLocationData();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<String, Object> data = (HashMap<String, Object>) dataSnapshot.getValue();
                users = (HashMap<String, Object>) data.get("users");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        listView = (ListView) findViewById(R.id.locationslist);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listView.setSelected(true);
                Log.d("KMyAct", "" + position);
                Locations instanceLoc = SimpleModel.getInstance().getItems().get(position);
                SimpleModel.getInstance().setPositionTracker(position);
                if (instanceLoc != null) {
                    Locations.getInstance().addAttributes(instanceLoc.getLocationName(),
                            instanceLoc.getLongitude(), instanceLoc.getLatitude(),
                            instanceLoc.getAddress(), instanceLoc.getLocationType(),
                            instanceLoc.getPhoneNumber());
                }
                onViewButtonPressed();
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.hp:
                Intent main = null;
                if (DatabaseSingleton.getInstance().getCurrentUser().getUserType().equals("User")) {
                    main = new Intent(getBaseContext(), UserHomeActivity.class);
                } else if (DatabaseSingleton.getInstance().getCurrentUser().getUserType().equals("Location Employee")) {
                    main = new Intent(getBaseContext(), LocEmployeeActivity.class);
                }
                startActivity(main);
                break;
            case R.id.loclist:
                Intent main2 = new Intent(getBaseContext(), LocationScreenActivity.class);
                startActivity(main2);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            //return SimpleModel.getInstance().getItems().get(SimpleModel.getInstance().getItems().size() - 1).getKey();
            return users.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.list_view_layout,null);

            TextView locName = (TextView) convertView.findViewById(R.id.textView_location_name);
            TextView address = (TextView) convertView.findViewById(R.id.textView_address);
            TextView phoneNum = (TextView) convertView.findViewById(R.id.textView_phone_number);

            locName.setText(SimpleModel.getInstance().getItems().get(position).getLocationName());
            address.setText("Address: " + SimpleModel.getInstance().getItems().get(position).getAddress());
            phoneNum.setText("Phone Number " + SimpleModel.getInstance().getItems().get(position).getPhoneNumber());

            return convertView;
        }
    }

    public void onViewButtonPressed() {
        Intent intent = null;
        if (DatabaseSingleton.getInstance().getCurrentUser().getUserType().equals("Location Employee")) {
            intent = new Intent(this, LocEmployeeLocationsActivity.class);
        } else if (DatabaseSingleton.getInstance().getCurrentUser().getUserType().equals("User")) {
            intent = new Intent(this, LocationActivity.class);
        }
        startActivity(intent);
    }


    private void readLocationData() {

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

                mDatabase.child("locations").child(Integer.toString(key)).setValue(new Locations(key, tokens[1], lon, lat, tokens[4], tokens[8], tokens[9]));

                Log.d("HomeScreenActivity", "Just created: " + SimpleModel.getInstance());

            }
            reader.close();
        } catch (IOException e) {
            Log.wtf("HomeScreenActivity", "Error reading data file on line " + line, e);
        }
    }

}

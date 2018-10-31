package com.example.caitlinyang.m4.controllers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.caitlinyang.m4.R;
import com.example.caitlinyang.m4.model.Locations;
import com.example.caitlinyang.m4.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class UserHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private DatabaseReference mDatabase;
    private HashMap<String, Object> users;
    private HashMap<String, Object> user;
    private Intent main;
    private Spinner filter1;
    private Spinner filter2;
    private Spinner filter3;
    private List<String> locations;
    private static List<String> filter1list = Arrays.asList("Search By Item", "Search By Category");
    private static List<String> filter2list = Arrays.asList("Search All Locations", "Search Specific Location");
    private ArrayAdapter<String> adapter1;
    private ArrayAdapter<String> adapter2;
    private ArrayAdapter<String> adapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_user_home);
        filter1 = (Spinner) findViewById(R.id.filter1);
        filter2 = (Spinner) findViewById(R.id.filter2);
        filter3 = (Spinner) findViewById(R.id.filter3);
        //filter1 spinner
        adapter1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, filter1list);
        adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, filter2list);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        filter1.setAdapter(adapter1);
        filter2.setAdapter(adapter2);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                locations = new ArrayList<>();
                DataSnapshot data = dataSnapshot.child("locations");
                Log.d("TEST", "hi");
                locations.add("N/A");
                for (DataSnapshot snapshot : data.getChildren()) {
                    Locations location = snapshot.getValue(Locations.class);
                    locations.add(location.getLocationName());
                }
                Log.d("TEST", Integer.toString(locations.size()));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

//        adapter3 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, locations);
//        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        filter3.setAdapter(adapter3);
        TextView welcome = (TextView) findViewById(R.id.welcomeUser);
        if (intent.hasExtra("key")) {
            user = (HashMap<String, Object>) intent.getSerializableExtra("key");
            Log.d("TEST", (String) user.get("name"));
            //Fix later
            welcome.setText(String.valueOf("Welcome " + (String) user.get("name")));
        }

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_view);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final SearchView search = findViewById(R.id.search);
        search.setSubmitButtonEnabled(true);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String s) {
                String query = s;
                Intent main = new Intent(getBaseContext(), ViewItemsActivity.class);
                if (filter1.getSelectedItem() == "Search By Item") {
                    main.putExtra("filter1", "item");
                } else {
                    main.putExtra("filter1", "category");
                }
                if (filter2.getSelectedItem() == "Search All Locations") {
                    main.putExtra("filter2", "all");
                } else {
                    main.putExtra("filter2", "one");
                    main.putExtra("location", (String) filter3.getSelectedItem());
                }
                main.putExtra("search", query);
                startActivity(main);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.loclist:
                main = new Intent(getBaseContext(), LocationScreenActivity.class);
                main.putExtra("key", user);
                startActivity(main);
                break;
            case R.id.hp:
                Intent main2 = new Intent(getBaseContext(), UserHomeActivity.class);
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
}

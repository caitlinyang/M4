package com.example.caitlinyang.m4.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.caitlinyang.m4.R;
import com.example.caitlinyang.m4.model.Locations;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class LocEmployeeLocationsActivity extends AppCompatActivity {

    private Locations location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loc_employee_locations);

        ListView listView = findViewById(R.id.location_information);


        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        Button addItem = findViewById(R.id.add_item);
        Intent intent = getIntent();
        if (intent.hasExtra("location")) {
            Log.d("TEST", "bye");
            location = (Locations) intent.getSerializableExtra("location");
            Log.d("TEST", location.getLocationName());
        }
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(getBaseContext(), AddItemActivity.class);
                main.putExtra("location", location);
                startActivity(main);
            }
        });

        Button viewItem = findViewById(R.id.view_items);

        viewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(getBaseContext(), ViewItemsActivity.class);
                main.putExtra("location", location);
                startActivity(main);
            }
        });
        LocEmployeeLocationsActivity.CustomAdapter customAdapter =
                new LocEmployeeLocationsActivity.CustomAdapter();
        listView.setAdapter(customAdapter);
    }


    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 1;
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
            View newView = getLayoutInflater()
                    .inflate(R.layout.loc_employee_individual_location, parent, false);

            TextView locName = convertView.findViewById(R.id.textView_ind_location_name);
            TextView locType = convertView.findViewById(R.id.textView_ind_location_type);
            TextView address = convertView.findViewById(R.id.textView_ind_location_address);
            TextView phoneNum = convertView.findViewById(R.id.textView_ind_location_phone_number);

            TextView lonText = convertView.findViewById(R.id.textView_ind_location_longitude);
            TextView latText = convertView.findViewById(R.id.textView_ind_location_latitude);


            locName.setText(location.getLocationName());
            locType.setText("Location Type: " + location.getLocationType());
            lonText.setText("Longitude: " + Double.toString(location.getLongitude()));
            latText.setText("Latitude: " + Double.toString(location.getLatitude()));
            address.setText("Address: " + location.getAddress());
            phoneNum.setText("Phone Number: " + location.getPhoneNumber());

            return newView;
        }
    }


}

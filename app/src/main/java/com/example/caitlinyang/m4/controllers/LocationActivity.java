package com.example.caitlinyang.m4.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.caitlinyang.m4.R;
import com.example.caitlinyang.m4.model.Locations;

/**
 * LocationActivity
 */
public class LocationActivity extends AppCompatActivity {

    private Locations location;

    /**
     * onCreate method
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ListView listView;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        Intent intent = getIntent();
        if (intent.hasExtra("location")) {
            location = (Locations) intent.getSerializableExtra("location");
        }
        listView = findViewById(R.id.location_information);
        CustomAdapter customAdapter = new CustomAdapter();

        listView.setAdapter(customAdapter);
    }

    /**
     * customAdapter class
     */
    class CustomAdapter extends BaseAdapter {
        /**
         * getCount method
         * @return count
         */
        @Override
        public int getCount() {
            return 1;
        }

        /**
         * getItem method
         * @param position position
         * @return Object
         */
        @Override
        public Object getItem(int position) {
            return null;
        }

        /**
         * getItemId method
         * @param position position
         * @return 0
         */
        @Override
        public long getItemId(int position) {
            return 0;
        }

        /**
         * getView method
         * @param position position
         * @param convertView convertView
         * @param parent ViewGroup parent
         * @return
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View newView = getLayoutInflater()
                    .inflate(R.layout.individual_location_layout, parent, false);

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

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

/**
 *LocEmployeeLocationsActivity class
 */
public class LocEmployeeLocationsActivity extends AppCompatActivity {

    private Locations location;

    /**
     * onCreate method
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loc_employee_locations);

        ListView listView = findViewById(R.id.location_information);

        Button addItem = findViewById(R.id.add_item);
        Intent intent = getIntent();
        if (intent.hasExtra("location")) {
            Log.d("TEST", "bye");
            location = (Locations) intent.getSerializableExtra("location");
            Log.d("TEST", location.getLocationName());
        }
        addItem.setOnClickListener(new View.OnClickListener() {
            /**
             * onClick method
             * @param v View v
             */
            @Override
            public void onClick(View v) {
                Intent main = new Intent(getBaseContext(), AddItemActivity.class);
                main.putExtra("location", location);
                startActivity(main);
            }
        });

        Button viewItem = findViewById(R.id.view_items);

        viewItem.setOnClickListener(new View.OnClickListener() {
            /**
             * onClick method
             * @param v View v
             */
            @Override
            public void onClick(View v) {
                Intent main = new Intent(getBaseContext(), ViewItemsActivity.class);
                main.putExtra("location", location);
                startActivity(main);
            }
        });
        android.widget.ListAdapter customAdapter =
                new LocEmployeeLocationsActivity.CustomAdapter();
        listView.setAdapter(customAdapter);
    }

    /**
     * customAdapter class
     */
    class CustomAdapter extends BaseAdapter {

        /**
         * getcount method
         * @return 1
         */
        @Override
        public int getCount() {
            return 1;
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
         * @param parent ViewGroup
         * @return view
         */
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

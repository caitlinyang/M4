package com.example.caitlinyang.m4.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.caitlinyang.m4.R;
import com.example.caitlinyang.m4.model.Locations;


public class LocEmployeeLocationsActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loc_employee_locations);

        listView = (ListView) findViewById(R.id.location_information);
        LocEmployeeLocationsActivity.CustomAdapter customAdapter = new LocEmployeeLocationsActivity.CustomAdapter();

        listView.setAdapter(customAdapter);

        Button addItem = (Button) findViewById(R.id.add_item);

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(getBaseContext(), AddItemActivity.class);
                startActivity(main);
            }
        });

        Button viewItem = (Button) findViewById(R.id.view_items);

        viewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(getBaseContext(), ViewItemsActivity.class);
                startActivity(main);
            }
        });

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
            convertView = getLayoutInflater().inflate(R.layout.loc_employee_individual_location, parent, false);

            TextView locName = (TextView) convertView.findViewById(R.id.textView_ind_location_name);
            TextView locType = (TextView) convertView.findViewById(R.id.textView_ind_location_type);
            TextView address = (TextView) convertView.findViewById(R.id.textView_ind_location_address);
            TextView phoneNum = (TextView) convertView.findViewById(R.id.textView_ind_location_phone_number);

            TextView lonText = (TextView) convertView.findViewById(R.id.textView_ind_location_longitude);
            TextView latText = (TextView) convertView.findViewById(R.id.textView_ind_location_latitude);


            locName.setText(Locations.getInstance().getLocationName());
            locType.setText("Location Type: " + Locations.getInstance().getLocationType());
            lonText.setText("Longitude: " + Double.toString(Locations.getInstance().getLongitude()));
            latText.setText("Latitude: " + Double.toString(Locations.getInstance().getLatitude()));
            address.setText("Address: " + Locations.getInstance().getAddress());
            phoneNum.setText("Phone Number: " + Locations.getInstance().getPhoneNumber());

            return convertView;
        }
    }


}

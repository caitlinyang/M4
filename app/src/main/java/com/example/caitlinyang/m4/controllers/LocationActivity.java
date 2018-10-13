package com.example.caitlinyang.m4.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.caitlinyang.m4.R;
import com.example.caitlinyang.m4.model.Locations;
import com.example.caitlinyang.m4.model.SimpleModel;

import org.w3c.dom.Text;

public class LocationActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        listView = (ListView) findViewById(R.id.location_information);
        CustomAdapter customAdapter = new CustomAdapter();

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
            convertView = getLayoutInflater().inflate(R.layout.individual_location_layout, parent, false);

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

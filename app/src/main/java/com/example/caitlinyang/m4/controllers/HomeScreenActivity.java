package com.example.caitlinyang.m4.controllers;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import java.util.List;
import com.example.caitlinyang.m4.R;
import com.example.caitlinyang.m4.model.Locations;
import com.example.caitlinyang.m4.model.SimpleModel;

public class HomeScreenActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        
        readLocationData();

        listView = (ListView) findViewById(R.id.locationslist);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listView.setSelected(true);
                Log.d("KMyAct", "" + position);
                Locations instanceLoc = SimpleModel.getInstance().getItems().get(position);
                if (instanceLoc != null) {
                    Locations.getInstance().addAttributes(instanceLoc.getLocationName(),
                            instanceLoc.getLongitude(), instanceLoc.getLatitude(),
                            instanceLoc.getAddress(), instanceLoc.getLocationType(),
                            instanceLoc.getPhoneNumber());
                }
            }
        });

        Button viewLocations = (Button) findViewById(R.id.viewButton);
        viewLocations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!listView.isSelected()) {
                    Toast.makeText(getApplicationContext(), "Please select a location,",
                            Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    onViewButtonPressed();
                }
            }
        });

    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return SimpleModel.getInstance().getItems().get(SimpleModel.getInstance().getItems().size() - 1).getKey();
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
        Intent intent = new Intent(this, LocationActivity.class);
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
                SimpleModel.getInstance().addItem(new Locations(key, tokens[1], lon, lat, tokens[4], tokens[8], tokens[9]));

                Log.d("HomeScreenActivity", "Just created: " + SimpleModel.getInstance());

            }
            reader.close();
        } catch (IOException e) {
            Log.wtf("HomeScreenActivity", "Error reading data file on line " + line, e);
        }
    }

}

package com.example.caitlinyang.m4.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.caitlinyang.m4.R;
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
            return 5;
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
            convertView = getLayoutInflater().inflate(R.layout.individual_location_layout, null);

            TextView locName = (TextView) convertView.findViewById(R.id.textView_ind_location_name);
            TextView locType = (TextView) convertView.findViewById(R.id.textView_ind_location_type);
            EditText lon = (EditText) convertView.findViewById(R.id.editText_ind_location_longitude);
            EditText lat = (EditText) convertView.findViewById(R.id.editText_ind_location_latitude);
            TextView address = (TextView) convertView.findViewById(R.id.textView_ind_location_address);
            TextView phoneNum = (TextView) convertView.findViewById(R.id.textView_ind_location_phone_number);

            String lonVar = Double.toString(Double.parseDouble(lon.getText().toString()));
            String latVar = Double.toString(Double.parseDouble(lat.getText().toString()));

            locName.setText(SimpleModel.getInstance().getSelectedInstance().getItems().get(position).getLocationName());
            locType.setText(SimpleModel.getInstance().getSelectedInstance().getItems().get(position).getLocationType());
            lon.setText(lonVar);
            lat.setText(latVar);
            address.setText(SimpleModel.getInstance().getSelectedInstance().getItems().get(position).getAddress());
            phoneNum.setText(SimpleModel.getInstance().getSelectedInstance().getItems().get(position).getPhoneNumber());

            return convertView;
        }
    }
}

package com.example.caitlinyang.m4.controllers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.AlertDialog.Builder;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.caitlinyang.m4.R;
import com.example.caitlinyang.m4.model.Item;
import com.example.caitlinyang.m4.model.Locations;
import com.example.caitlinyang.m4.model.SimpleModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddItemActivity extends AppCompatActivity implements DialogInterface.OnCancelListener {

    private ListView listView;
    public static List<String> categories = new ArrayList<>();
    private String categoryInput;

//    private String loc_name;
//    private String name;
//    private String time;
//    private String value;
      private Spinner categorySpinner;
//    private String category;
//    private String shortDes;
//    private String fullDes;

    private EditText nameInput;
    private EditText timeStamp;
    private EditText valueInput;
    private EditText shortDesc;
    private EditText fullDesc;

    private ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        viewFlipper = findViewById(R.id.viewFlipper);

        final List<String> items = Arrays.asList("Clothing", "Hat", "Kitchen", "Electronics", "Household", "Other");
        categories.add("Clothing"); categories.add("Hat"); categories.add("Kitchen");
        categories.add("Electronics"); categories.add("Household"); categories.add("Other");

        nameInput = (EditText) findViewById(R.id.item_name_input);
        timeStamp = (EditText) findViewById(R.id.time_stamp_input);
        valueInput = (EditText) findViewById(R.id.valueInput);
        categorySpinner = (Spinner) findViewById(R.id.categorySpinner);


        // Taking in the inputs from User for time stamp and value in dollars.

        // Updating the Spinner w/ required information.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        // Get selected items from spinner

        shortDesc = (EditText) findViewById(R.id.shortDesInput);
        fullDesc = (EditText) findViewById(R.id.fullDescInput);


        // Goes to create a category screen
        Button createCategory = (Button) findViewById(R.id.add_category);
        createCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddItemActivity.this);
                builder.setTitle("Create a Category");
                final EditText input = new EditText(AddItemActivity.this);
                builder.setView(input);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        categoryInput = input.getText().toString();
                        items.remove("Other");
                        items.add(categoryInput);
                        items.add("Other");
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

        Button submitItem = (Button) findViewById(R.id.submitItem);
        submitItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameInput.getText().toString().trim().equals("") || timeStamp.getText().toString().trim().equals("") || valueInput.getText().toString().trim().equals("")
                        || categorySpinner.getSelectedItem().toString().trim().equals("")
                        || shortDesc.getText().toString().trim().equals("") || fullDesc.getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please fill out all of the information!",Toast.LENGTH_SHORT).show();
                } else {
                    final String loc_name = Locations.getInstance().getLocationName();
                    final String name = nameInput.getText().toString();
                    final String time = timeStamp.getText().toString();
                    final String value = valueInput.getText().toString();
                    final String category = categorySpinner.getSelectedItem().toString();
                    final String shortDes = shortDesc.getText().toString();
                    final String fullDes = fullDesc.getText().toString();
                    SimpleModel.getInstance().addDonation(new Item(loc_name, name, time, value, category, shortDes, fullDes));
                    SimpleModel.getInstance().getItems().get(SimpleModel.getInstance().getPositionTracker()).addItemToList(
                            new Item(loc_name, name, time, value, category, shortDes, fullDes));
                    Log.d("MYACTLOOK", name + " " + time + " " + value);
                    Log.d("MYACTLOOK", " " + SimpleModel.getInstance().getPositionTracker());
                    Log.d("MYACTLOOK", SimpleModel.getInstance().getItems().get(SimpleModel.getInstance().getPositionTracker()).getListOfItems().get(0).getItem_name());
                    Log.d("MYACTLOOK", SimpleModel.getInstance().getItems().get(SimpleModel.getInstance().getPositionTracker()).getLocationName());
                    Intent main = new Intent(getBaseContext(), LocEmployeeLocationsActivity.class);
                    startActivity(main);
                }
            }
        });
    }

    @Override
    public void onCancel(DialogInterface dialog) {
    }

    public void prev(View view) {
        viewFlipper.showPrevious();
    }

    public void next(View view) {
        viewFlipper.showNext();
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

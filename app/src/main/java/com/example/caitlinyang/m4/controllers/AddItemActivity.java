package com.example.caitlinyang.m4.controllers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.caitlinyang.m4.R;
import com.example.caitlinyang.m4.model.Item;
import com.example.caitlinyang.m4.model.Locations;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddItemActivity extends AppCompatActivity implements DialogInterface.OnCancelListener {

    private final static List<String> categories = new ArrayList<>();
    private String categoryInput;
    private Spinner categorySpinner;
    private EditText nameInput;
    private EditText timeStamp;
    private EditText valueInput;
    private EditText shortDesc;
    private EditText fullDesc;

    private ViewFlipper viewFlipper;
    private DatabaseReference mDatabase;
    private Locations location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        viewFlipper = findViewById(R.id.viewFlipper);

        final List<String> items = Arrays.asList("Clothing", "Hat", "Kitchen",
                "Electronics", "Household", "Other");
        categories.add("Clothing"); categories.add("Hat"); categories.add("Kitchen");
        categories.add("Electronics"); categories.add("Household"); categories.add("Other");

        nameInput = findViewById(R.id.item_name_input);
        timeStamp = findViewById(R.id.time_stamp_input);
        valueInput = findViewById(R.id.valueInput);
        categorySpinner = findViewById(R.id.categorySpinner);

        Intent intent = getIntent();
        if (intent.hasExtra("location")) {
            Log.d("TEST", "bye");
            location = (Locations) intent.getSerializableExtra("location");
            Log.d("TEST", location.getLocationName());
        }


        // Taking in the inputs from User for time stamp and value in dollars.

        // Updating the Spinner w/ required information.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        // Get selected items from spinner

        shortDesc = findViewById(R.id.shortDesInput);
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
                if ("".equals(nameInput.getText().toString().trim())
                        || "".equals(timeStamp.getText()
                        .toString().trim()) || "".equals(valueInput
                        .getText().toString().trim())
                        || "".equals(categorySpinner.getSelectedItem().toString().trim())
                        || "".equals(shortDesc.getText().toString().trim())
                        || "".equals(fullDesc.getText().toString().trim())) {
                    Toast.makeText(getApplicationContext(),
                            "Please fill out all of the information!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    final String loc_name = location.getLocationName();
                    final String name = nameInput.getText().toString();
                    final String time = timeStamp.getText().toString();
                    final String value = valueInput.getText().toString();
                    final String category = categorySpinner.getSelectedItem().toString();
                    final String shortDes = shortDesc.getText().toString();
                    final String fullDes = fullDesc.getText().toString();
                    Log.d("TEST", "AddItemActivity" + location.getLocationName());
                    Item item = new Item(loc_name, name, time, value, category, shortDes, fullDes);
                    mDatabase.child("items").child(item.getKey()).setValue(item);
                    Intent main = new Intent(getBaseContext(), LocEmployeeLocationsActivity.class);
                    main.putExtra("location", location);
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

    private class CustomAdapter extends BaseAdapter {

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
            convertView = getLayoutInflater()
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

            return convertView;
        }
    }
}

package com.example.caitlinyang.m4.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.caitlinyang.m4.R;
import com.example.caitlinyang.m4.model.Item;
import com.example.caitlinyang.m4.model.Locations;
import com.example.caitlinyang.m4.model.SimpleModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewItemsActivity extends AppCompatActivity {

    private ListView listView;
    private DatabaseReference mDatabase;
    private List<Item> items;
    private Item instanceItem;
    private Intent intent;
    private Locations location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_items);
        listView = (ListView) findViewById(R.id.items_list_listView);
        intent = getIntent();
        if (intent.hasExtra("location")) {
            location = (Locations) intent.getSerializableExtra("location");
        }
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                items = new ArrayList<>();
                DataSnapshot data = dataSnapshot.child("items");
                Log.d("TEST", "hi");
                for (DataSnapshot snapshot : data.getChildren()) {
                    Item item = snapshot.getValue(Item.class);
                    if (intent.hasExtra("filter1")) {
                        if (intent.getStringExtra("filter1").equals("item") && intent.getStringExtra("filter2").equals("all")) {
                            if (item.getItem_name().trim().toLowerCase().equals(intent.getStringExtra("search").toLowerCase().trim())) {
                                items.add(item);
                            }
                        } else if (intent.getStringExtra("filter1").equals("category") && intent.getStringExtra("filter2").equals("all")){
                            if (item.getCategory().trim().toLowerCase().equals(intent.getStringExtra("search").toLowerCase().trim())) {
                                items.add(item);
                            }
                        } else if (intent.getStringExtra("filter1").equals("item") && intent.getStringExtra("filter2").equals("all")) {
                            if (item.getItem_name().trim().toLowerCase().equals(intent.getStringExtra("search").toLowerCase().trim()) && item.getLoc_name().equals(intent.getStringExtra("location"))) {
                                items.add(item);
                            }
                        } else if (intent.getStringExtra("filter1").equals("category") && intent.getStringExtra("filter2").equals("one")){
                            if (item.getCategory().trim().toLowerCase().equals(intent.getStringExtra("search").toLowerCase().trim()) && item.getLoc_name().equals(intent.getStringExtra("location"))) {
                                items.add(item);
                            }
                        }
                    }
                    else {
                        if (item.getLoc_name().equals(location.getLocationName())) {
                            items.add(item);
                        }
                    }
                    ViewItemsActivity.CustomAdapter customAdapter1 = new ViewItemsActivity.CustomAdapter();
                    listView.setAdapter(customAdapter1);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView < ? > parent, View view,int position, long id){
                listView.setSelected(true);
                Log.d("KMyAct", "" + position);
                instanceItem = items.get(position);
                onViewButtonPressed();
            }
        });
    }
    class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return items.size();
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
            convertView = getLayoutInflater().inflate(R.layout.individual_item_layout, parent, false);

            TextView nameOfItem = (TextView) convertView.findViewById(R.id.individual_item);
            nameOfItem.setText(items.get(position).getItem_name());

            TextView time = (TextView) convertView.findViewById(R.id.time_donation_came);
            time.setText(items.get(position).getTime_stamp());

            TextView value = (TextView) convertView.findViewById(R.id.value_donation_came);
            value.setText(items.get(position).getValueDollars());

            TextView category = (TextView) convertView.findViewById(R.id.category_donation_came);
            category.setText(items.get(position).getCategory());

            TextView shortDescription = (TextView) convertView.findViewById(R.id.shortDes_donation_came);
            shortDescription.setText(items.get(position).getShortDes());

            TextView fullDescription = (TextView) convertView.findViewById(R.id.fullDes_donation_came);
            fullDescription.setText(items.get(position).getLongDes());

            return convertView;
        }
    }
    public void onViewButtonPressed() {
        Intent intent = new Intent(this, IndividualItemActivity.class);
        intent.putExtra("item", instanceItem);
        startActivity(intent);
    }
}

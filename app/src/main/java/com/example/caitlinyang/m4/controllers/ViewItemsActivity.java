package com.example.caitlinyang.m4.controllers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.caitlinyang.m4.R;
import com.example.caitlinyang.m4.model.Item;
import com.example.caitlinyang.m4.model.Locations;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewItemsActivity
 */
public class ViewItemsActivity extends AppCompatActivity {

    private List<Item> items;
    private ListView listView;
    private Item instanceItem;
    private Intent intent;
    private Locations location;

    /**
     * onCreate method
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_items);
        listView = findViewById(R.id.items_list_listView);
        intent = getIntent();
        if (intent.hasExtra("location")) {
            location = (Locations) intent.getSerializableExtra("location");
        }
        mDatabase.addValueEventListener(new ValueEventListener() {
            /**
             * onDataChange method
             * @param dataSnapshot DataSnapshot
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                items = new ArrayList<>();
                DataSnapshot data = dataSnapshot.child("items");
                Log.d("TEST", "hi");
                for (DataSnapshot snapshot : data.getChildren()) {
                    Item item = snapshot.getValue(Item.class);
                    if (intent.hasExtra("filter1")) {
                        if ("item".equals(intent.getStringExtra("filter1"))
                                && "all".equals(intent.getStringExtra("filter2"))) {
                            if ((item != null)
                                    && (item.getItem_name().trim().toLowerCase()
                                    .equals(intent.getStringExtra("search")
                                            .toLowerCase().trim()))) {
                                items.add(item);
                            }
                        } else if ("category".equals(intent.getStringExtra("filter1"))
                                && "all".equals(intent.getStringExtra("filter2"))){
                            if ((item != null)
                                    && (item.getCategory().trim().toLowerCase().equals(intent
                                    .getStringExtra("search").toLowerCase().trim()))) {
                                items.add(item);
                            }
                        } else if ("item".equals(intent.getStringExtra("filter1"))
                                && "one".equals(intent.getStringExtra("filter2"))) {
                            if ((item != null)
                                    && (item.getItem_name().trim().toLowerCase().equals(intent
                                    .getStringExtra("search").toLowerCase().trim())
                                    && item.getLoc_name().toLowerCase().trim().equals(intent
                                    .getStringExtra("filter3").toLowerCase().trim()))) {
                                items.add(item);
                            }
                        } else if ("category".equals(intent.getStringExtra("filter1"))
                                && "one".equals(intent.getStringExtra("filter2"))){
                            if ((item != null)
                                    && (item.getCategory().trim().toLowerCase().equals(intent
                                    .getStringExtra("search").toLowerCase().trim())
                                    && item.getLoc_name().toLowerCase().trim()
                                    .equals(intent.getStringExtra("filter3")
                                            .toLowerCase().trim()))) {
                                items.add(item);
                            }
                        }
                    }
                    else {
                        if ((item != null)
                            && (item.getLoc_name().equals(location.getLocationName()))) {
                            items.add(item);
                        }
                    }
                    ViewItemsActivity.CustomAdapter customAdapter1 =
                            new ViewItemsActivity.CustomAdapter();
                    listView.setAdapter(customAdapter1);
                }
            }

            /**
             * onCancelled method
             * @param databaseError DatabaseError
             */
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * onItemClick method
             * @param parent AdapterView
             * @param view View
             * @param position int
             * @param id long
             */
            @Override
            public void onItemClick (AdapterView < ? > parent, View view,int position, long id){
                listView.setSelected(true);
                Log.d("KMyAct", "" + position);
                instanceItem = items.get(position);
                onViewButtonPressed();
            }
        });
    }

    /**
     * CustomAdapter class
     */
    protected class CustomAdapter extends BaseAdapter {

        /**
         * gets count
         * @return count
         */
        @Override
        public int getCount() {
            return items.size();
        }

        /**
         * getItem method
         * @param position position in adapter
         * @return null
         */
        @Override
        public Object getItem(int position) {
            return null;
        }

        /**
         * getItemID Method
         * @param position position in adapter
         * @return 0
         */
        @Override
        public long getItemId(int position) {
            return 0;
        }

        /**
         * GetView Method
         * @param position position in adapter
         * @param convertView convertView object
         * @param parent ViewGroup parent
         * @return view for adapter
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View newView = getLayoutInflater()
                    .inflate(R.layout.individual_item_layout, parent, false);

            TextView nameOfItem = convertView.findViewById(R.id.individual_item);
            nameOfItem.setText(items.get(position).getItem_name());

            TextView time = convertView.findViewById(R.id.time_donation_came);
            time.setText(items.get(position).getTime_stamp());

            TextView category = convertView.findViewById(R.id.category_donation_came);
            category.setText(items.get(position).getCategory());

            TextView shortDescription = convertView.findViewById(R.id.shortDes_donation_came);
            shortDescription.setText(items.get(position).getShortDes());

            return newView;
        }
    }

    /**
     * onViewButtonPressed method
     */
    private void onViewButtonPressed() {
        Intent intent = new Intent(this, IndividualItemActivity.class);
        intent.putExtra("item", instanceItem);
        startActivity(intent);
    }
}

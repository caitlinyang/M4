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
import com.example.caitlinyang.m4.model.Item;

/**
 * IndividualItemActivity class
 */
public class IndividualItemActivity extends AppCompatActivity {
    private Item item;

    /**
     * creates individual item activity
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ListView listView;
        Intent intent;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_individual_item_layout);
        intent = getIntent();
        if (intent.hasExtra("item")) {
            item = (Item) intent.getSerializableExtra("item");
        }
        listView = findViewById(R.id.list_individual_items);
        android.widget.ListAdapter customAdapter =
                new IndividualItemActivity.CustomAdapter();

        listView.setAdapter(customAdapter);
    }

    /**
     * CustomAdapter
     */
    class CustomAdapter extends BaseAdapter {
        /**
         * gets count
         * @return count
         */
        @Override
        public int getCount() {
            return 1;
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
                    .inflate(R.layout.individual_item_detailed_information, parent,
                            false);

            TextView nameOfItem = convertView.findViewById(R.id.textView_ind_item_view_item_name);
            nameOfItem.setText("Item Name: " + item.getItem_name());

            TextView time = convertView.findViewById(R.id.textView_ind_item_view_time_stamp);
            time.setText("Time stamp: " + item.getTime_stamp());

            TextView value = convertView.findViewById(R.id.textView_ind_item_view_valueDollars);
            value.setText("Value: " + item.getValueDollars());

            TextView category = convertView.findViewById(R.id.textView_ind_item_view_category);
            category.setText("Category: " + item.getCategory());

            TextView shortDescription = convertView
                    .findViewById(R.id.textView_ind_item_view_shortDes);
            shortDescription.setText("Short Description: " + item.getShortDes());

            TextView fullDescription = convertView
                    .findViewById(R.id.textView_ind_item_view_longDes);
            fullDescription.setText("Full Description: " + item.getLongDes());

            return newView;
        }
    }
}

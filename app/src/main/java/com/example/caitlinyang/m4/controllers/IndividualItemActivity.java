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

public class IndividualItemActivity extends AppCompatActivity {
    private ListView listView;
    private Intent intent;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_individual_item_layout);
        intent = getIntent();
        if (intent.hasExtra("item")) {
            item = (Item) intent.getSerializableExtra("item");
        }
        listView = findViewById(R.id.list_individual_items);
        IndividualItemActivity.CustomAdapter customAdapter =
                new IndividualItemActivity.CustomAdapter();

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
            convertView = getLayoutInflater()
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

            return convertView;
        }
    }
}

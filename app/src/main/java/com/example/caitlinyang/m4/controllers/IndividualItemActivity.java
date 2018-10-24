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
import com.example.caitlinyang.m4.model.DatabaseSingleton;
import com.example.caitlinyang.m4.model.Locations;
import com.example.caitlinyang.m4.model.SimpleModel;

import org.w3c.dom.Text;

public class IndividualItemActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_individual_item_layout);

        listView = (ListView) findViewById(R.id.list_individual_items);
        IndividualItemActivity.CustomAdapter customAdapter = new IndividualItemActivity.CustomAdapter();

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
            convertView = getLayoutInflater().inflate(R.layout.individual_item_detailed_information, parent, false);

            TextView nameOfItem = (TextView) convertView.findViewById(R.id.textView_ind_item_view_item_name);
            nameOfItem.setText("Item Name: " + SimpleModel.getInstance().getItems().get(SimpleModel.getInstance().getPositionTracker()).getListOfItems().
                    get(position).getItem_name());

            TextView time = (TextView) convertView.findViewById(R.id.textView_ind_item_view_time_stamp);
            time.setText("Time stamp: " + SimpleModel.getInstance().getItems().get(SimpleModel.getInstance().getPositionTracker()).getListOfItems().
                    get(position).getTime_stamp());

            TextView value = (TextView) convertView.findViewById(R.id.textView_ind_item_view_valueDollars);
            value.setText("Value: " + SimpleModel.getInstance().getItems().get(SimpleModel.getInstance().getPositionTracker()).getListOfItems().
                    get(position).getValueDollars());

            TextView category = (TextView) convertView.findViewById(R.id.textView_ind_item_view_category);
            category.setText("Category: " + SimpleModel.getInstance().getItems().get(SimpleModel.getInstance().getPositionTracker()).getListOfItems().
                    get(position).getCategory());

            TextView shortDescription = (TextView) convertView.findViewById(R.id.textView_ind_item_view_shortDes);
            shortDescription.setText("Short Description: " + SimpleModel.getInstance().getItems().get(SimpleModel.getInstance().getPositionTracker()).getListOfItems().
                    get(position).getShortDes());

            TextView fullDescription = (TextView) convertView.findViewById(R.id.textView_ind_item_view_longDes);
            fullDescription.setText("Full Description: " + SimpleModel.getInstance().getItems().get(SimpleModel.getInstance().getPositionTracker()).getListOfItems().
                    get(position).getLongDes());

            return convertView;
        }
    }
}

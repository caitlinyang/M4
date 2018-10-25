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
import com.example.caitlinyang.m4.model.SimpleModel;

import java.util.List;

public class ViewItemsActivity extends AppCompatActivity {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_items);

        listView = (ListView) findViewById(R.id.items_list_listView);
        CustomAdapter customAdapter1 = new CustomAdapter();

        listView.setAdapter(customAdapter1);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listView.setSelected(true);
                Log.d("KMyAct", "" + position);
                Item instanceItem = SimpleModel.getInstance().getItems().get(SimpleModel.getInstance().getPositionTracker()).getListOfItems().get(position);
                SimpleModel.getInstance().setPositionTracker(position);

                if (instanceItem != null) {
                    instanceItem.getInstance().addAttributes(instanceItem.getLoc_name(),
                            instanceItem.getItem_name(), instanceItem.getTime_stamp(),
                            instanceItem.getValueDollars(), instanceItem.getCategory(),
                            instanceItem.getShortDes(), instanceItem.getLongDes());
                }
                onViewButtonPressed();
            }
        });
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return SimpleModel.getInstance().getItems().get(SimpleModel.getInstance().getPositionTracker()).getListOfItems().size();
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

            List<Item> s = SimpleModel.getInstance().getItems().get(SimpleModel.getInstance().getPositionTracker()).getListOfItems();

            TextView nameOfItem = (TextView) convertView.findViewById(R.id.individual_item);
            nameOfItem.setText(SimpleModel.getInstance().getItems().get(SimpleModel.getInstance().getPositionTracker()).getListOfItems().
                    get(position).getItem_name());

            TextView time = (TextView) convertView.findViewById(R.id.time_donation_came);
            time.setText(SimpleModel.getInstance().getItems().get(SimpleModel.getInstance().getPositionTracker()).getListOfItems().
                    get(position).getTime_stamp());

            TextView value = (TextView) convertView.findViewById(R.id.value_donation_came);
            value.setText(SimpleModel.getInstance().getItems().get(SimpleModel.getInstance().getPositionTracker()).getListOfItems().
                    get(position).getValueDollars());

            TextView category = (TextView) convertView.findViewById(R.id.category_donation_came);
            category.setText(SimpleModel.getInstance().getItems().get(SimpleModel.getInstance().getPositionTracker()).getListOfItems().
                    get(position).getCategory());

            TextView shortDescription = (TextView) convertView.findViewById(R.id.shortDes_donation_came);
            shortDescription.setText(SimpleModel.getInstance().getItems().get(SimpleModel.getInstance().getPositionTracker()).getListOfItems().
                    get(position).getShortDes());

            TextView fullDescription = (TextView) convertView.findViewById(R.id.fullDes_donation_came);
            fullDescription.setText(SimpleModel.getInstance().getItems().get(SimpleModel.getInstance().getPositionTracker()).getListOfItems().
                    get(position).getLongDes());

            return convertView;
        }
    }
    public void onViewButtonPressed() {
        Intent intent = new Intent(this, IndividualItemActivity.class);
        startActivity(intent);
    }
}

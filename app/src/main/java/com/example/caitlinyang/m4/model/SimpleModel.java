package com.example.caitlinyang.m4.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SimpleModel {
    public static final SimpleModel INSTANCE = new SimpleModel();

    private List<Locations> items;

    private SimpleModel() {
        items = new ArrayList<>();
    }

    public void addItem(Locations item) { items.add(item); }

    public List<Locations> getItems() {
        return items;
    }

    public static SimpleModel getInstance() {
        return INSTANCE;
    }

    public Locations findItemByKey(int key) {
        for (Locations l : items) {
            if (l.getKey() == key) return l;
        }
        Log.d("MyApp", "Warning! Did not find the location via the key: " + key);
        return null;
    }
}

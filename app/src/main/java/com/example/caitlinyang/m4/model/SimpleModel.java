package com.example.caitlinyang.m4.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SimpleModel {
    public static final SimpleModel INSTANCE = new SimpleModel();
    private int positionTracker = 0;

    private List<Locations> items;
    private List<Item> donations;
    private SimpleModel() {
        items = new ArrayList<>();
        donations = new ArrayList<>();
    }

    public void addItem(Locations item) {
        items.add(item);
    }

    public void addDonation(Item item) { donations.add(item); }

    public List<Locations> getItems() {
        return items;
    }
    public List<Item> getDonations() { return donations; }

    public int getPositionTracker() { return positionTracker; }
    public void setPositionTracker(int position) { positionTracker = position; }

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

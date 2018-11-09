package com.example.caitlinyang.m4.model;

import java.io.Serializable;
import java.util.Random;

public class Item implements Serializable{
    private static final Item SPEC_ITEM = new Item();

    private String loc_name;
    private String item_name;
    private String time_stamp;
    private String valueDollars;
    private String category;
    private String shortDes;
    private String longDes;
    private String key;
    private Random rand;

    public Item() {}

    public Item(String loc_name, String item_name, String time_stamp, String valueDollars,
                String category, String shortDes, String longDes) {
        this.loc_name = loc_name;
        this.item_name = item_name;
        this.time_stamp = time_stamp;
        this.valueDollars = valueDollars;
        this.category = category;
        this.shortDes = shortDes;
        this.longDes = longDes;
        rand = new Random();
        key = Integer.toString(rand.nextInt(100000));
    }

    public String getLoc_name() { return loc_name; }

    public String getItem_name() { return item_name; }

    public String getTime_stamp() {
        return time_stamp;
    }

    public String getValueDollars() {
        return valueDollars;
    }

    public String getCategory() {
        return category;
    }

    public String getShortDes() {
        return shortDes;
    }

    public String getLongDes() {
        return longDes;
    }

    public String getKey() {return key;}
}

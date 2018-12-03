package com.example.caitlinyang.m4.model;

import java.io.Serializable;
import java.util.Random;

/**
 * Item Class
 */
public class Item implements Serializable{

    private String loc_name;
    private String item_name;
    private String time_stamp;
    private String valueDollars;
    private String category;
    private String shortDes;
    private String longDes;
    private String key;

    /**
     * no arguments constructor
     */
    public Item() {}

    /**
     * Constructor for Item
     * @param loc_name String
     * @param item_name String
     * @param time_stamp String
     * @param valueDollars String
     * @param category String
     * @param shortDes String
     * @param longDes String
     */
    public Item(String loc_name, String item_name, String time_stamp, String valueDollars,
                String category, String shortDes, String longDes) {
        this.loc_name = loc_name;
        this.item_name = item_name;
        this.time_stamp = time_stamp;
        this.valueDollars = valueDollars;
        this.category = category;
        this.shortDes = shortDes;
        this.longDes = longDes;
        Random rand = new Random();

        int keyUpperBound = 100000;
        key = Integer.toString(rand.nextInt(keyUpperBound));
    }

    /**
     * getter for location name
     * @return loc_name
     */
    public String getLoc_name() { return loc_name; }

    /**
     * setter for item name
     * @param item_name String
     */
    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    /**
     * getter for item name
     * @return item name
     */
    public String getItem_name() { return item_name; }

    /**
     * getter for the time stamp
     * @return time stamp
     */
    public String getTime_stamp() {
        return time_stamp;
    }

    /**
     * getter for value
     * @return value
     */
    public String getValueDollars() {
        return valueDollars;
    }

    /**
     * getter for the category
     * @return category
     */
    public String getCategory() {
        return category;
    }

    /**
     * getter for short description
     * @return short des
     */
    public String getShortDes() {
        return shortDes;
    }

    /**
     * getter for the long description
     * @return long des
     */
    public String getLongDes() {
        return longDes;
    }

    /**
     * getter for key
     * @return key
     */
    public String getKey() {return key;}
}

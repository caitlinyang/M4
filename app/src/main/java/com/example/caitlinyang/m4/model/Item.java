package com.example.caitlinyang.m4.model;

public class Item {
    public static final Item SPEC_ITEM = new Item();

    private String loc_name;
    private String item_name;
    private String time_stamp;
    private String valueDollars;
    private String category;
    private String shortDes;
    private String longDes;

    public Item() {}

    public Item(String loc_name, String item_name, String time_stamp, String valueDollars, String category,
                String shortDes, String longDes) {
        this.loc_name = loc_name;
        this.item_name = item_name;
        this.time_stamp = time_stamp;
        this.valueDollars = valueDollars;
        this.category = category;
        this.shortDes = shortDes;
        this.longDes = longDes;
    }
    public static Item getInstance() {
        return SPEC_ITEM;
    }

    public void addAttributes(String loc_name, String item_name, String time_stamp, String valueDollars, String category,
                              String shortDes, String longDes) {
        SPEC_ITEM.loc_name = loc_name;
        SPEC_ITEM.item_name = item_name;
        SPEC_ITEM.time_stamp = time_stamp;
        SPEC_ITEM.valueDollars = valueDollars;
        SPEC_ITEM.category = category;
        SPEC_ITEM.shortDes = shortDes;
        SPEC_ITEM.longDes = longDes;
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
}
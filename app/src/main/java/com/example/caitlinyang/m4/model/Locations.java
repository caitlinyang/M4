package com.example.caitlinyang.m4.model;

import java.io.Serializable;

public class Locations implements Serializable {

    private String locationName;
    private String locationType;
    private double longitude;
    private double latitude;
    private String address;
    private String phoneNumber;
    private int key;

    public Locations() {
    }

    public Locations(int key, String locationName, double longitude, double latitude,
                    String address, String locationType, String phoneNumber) {
        this.locationName = locationName;
        this.locationType = locationType;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.key = key;
    }

    public String getLocationName() {
        return locationName;
    }

    public String getLocationType() {
        return locationType;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getKey() { return key;}

    @Override
    public String toString() {
        return "Locations{" +
                "locationName='" + locationName + '\'' +
                ", locationType='" + locationType + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", address='" + address + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}

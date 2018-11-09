package com.example.caitlinyang.m4.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Locations implements Serializable {
    private static final Locations SPEC_LOCATION = new Locations();

    private String locationName;
    private String locationType;
    private double longitude;
    private double latitude;
    private String address;
    private String phoneNumber;

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

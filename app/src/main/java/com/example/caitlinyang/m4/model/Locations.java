package com.example.caitlinyang.m4.model;

public class Locations {
    public static final Locations SPEC_LOCATION = new Locations();

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
        this.key = key;
        this.locationName = locationName;
        this.locationType = locationType;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public static Locations getInstance() {
        return SPEC_LOCATION;
    }

    public void addAttributes(String locationName, double longitude, double latitude,
                              String address, String locationType, String phoneNumber) {
        SPEC_LOCATION.locationName = locationName;
        SPEC_LOCATION.longitude = longitude;
        SPEC_LOCATION.latitude = latitude;
        SPEC_LOCATION.address = address;
        SPEC_LOCATION.locationType = locationType;
        SPEC_LOCATION.phoneNumber = phoneNumber;
    }

    public int getKey() {
        return key;
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

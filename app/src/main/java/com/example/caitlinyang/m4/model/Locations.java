package com.example.caitlinyang.m4.model;

import java.io.Serializable;

/**
 * Locations class
 */
public class Locations implements Serializable {

    private String locationName;
    private String locationType;
    private double longitude;
    private double latitude;
    private String address;
    private String phoneNumber;
    private int key;

    /**
     * no arguments constructor
     */
    public Locations() {
    }

    /**
     * Constructor for locations
     * @param key int
     * @param locationName String
     * @param longitude double
     * @param latitude double
     * @param address address
     * @param locationType String
     * @param phoneNumber String
     */
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

    /**
     * getter for location name
     * @return location name
     */
    public String getLocationName() {
        return locationName;
    }

    /**
     * getter for location type
     * @return location type
     */
    public String getLocationType() {
        return locationType;
    }

    /**
     * getter for longitude
     * @return longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * getter for latitude
     * @return latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * getter for address
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * getter for phone number
     * @return phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * getter for key
     * @return key
     */
    public int getKey() { return key;}

    /**
     * to string method for locations
     * @return string
     */
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

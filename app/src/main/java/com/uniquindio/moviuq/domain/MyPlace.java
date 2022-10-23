package com.uniquindio.moviuq.domain;

import com.google.android.gms.maps.model.LatLng;

public class MyPlace {
    private String name;
    private double latitude;
    private double longitude;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyPlace(String name, double latitude, double longitude, String address) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MyPlace() {
    }
}

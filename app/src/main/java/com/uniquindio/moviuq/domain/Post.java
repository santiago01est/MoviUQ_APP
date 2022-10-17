package com.uniquindio.moviuq.domain;

import java.util.Date;

public class Post {

    private String id;
    private String date;
    private String title;
    private String description;
    private VehicleType vehicleType;
    private int seats;
    private String startPoint;
    private String arrivalPoint;

    public Post() {
    }

    public Post(String id, String fecha, String title, String description, VehicleType vehicleType, int seats, String startPoint, String arrivalPoint) {
        this.id = id;
        this.date = fecha;
        this.title = title;
        this.description = description;
        this.vehicleType = vehicleType;
        this.seats = seats;
        this.startPoint = startPoint;
        this.arrivalPoint = arrivalPoint;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getArrivalPoint() {
        return arrivalPoint;
    }

    public void setArrivalPoint(String arrivalPoint) {
        this.arrivalPoint = arrivalPoint;
    }
}

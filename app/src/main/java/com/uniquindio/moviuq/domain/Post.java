package com.uniquindio.moviuq.domain;

import java.util.Date;

public class Post {

    private String id;
    private String idUser;
    private String creationDate;
    private String title;
    private String description;
    private String dateTravel;
    private String hourTravel;
    private Rute rute;
    private VehicleType vehicleType;
    private int seats;

    public Post() {
    }

    public Post(String id, String idUser, String creationDate, String title, String description, String dateTravel, String hourTravel, Rute rute, VehicleType vehicleType, int seats) {
        this.id = id;
        this.idUser = idUser;
        this.creationDate = creationDate;
        this.title = title;
        this.description = description;
        this.dateTravel = dateTravel;
        this.hourTravel = hourTravel;
        this.rute = rute;
        this.vehicleType = vehicleType;
        this.seats = seats;
    }

    public String getDateTravel() {
        return dateTravel;
    }

    public void setDateTravel(String dateTravel) {
        this.dateTravel = dateTravel;
    }

    public String getHourTravel() {
        return hourTravel;
    }

    public void setHourTravel(String hourTravel) {
        this.hourTravel = hourTravel;
    }

    public Rute getRute() {
        return rute;
    }

    public void setRute(Rute rute) {
        this.rute = rute;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
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

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}

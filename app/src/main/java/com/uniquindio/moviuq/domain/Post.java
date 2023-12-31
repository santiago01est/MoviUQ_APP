package com.uniquindio.moviuq.domain;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable {

    private String id;
    private String idUser;
    private String nameUser;
    private String photoUser;
    private String creationDate;
    private String title;
    private String description;
    private String dateTravel;
    private String hourTravel;
    private Rute rute;
    private VehicleType vehicleType;
    private int seats;
    private EstadoPost estadoPost;


    public Post() {
    }

    public Post(String id, String idUser,String name,String photoUser, String creationDate, String title, String description, String dateTravel, String hourTravel, Rute rute, VehicleType vehicleType, int seats, EstadoPost estadoPost) {
        this.id = id;
        this.idUser = idUser;
        this.photoUser = photoUser;
        this.creationDate = creationDate;
        this.title = title;
        this.description = description;
        this.dateTravel = dateTravel;
        this.hourTravel = hourTravel;
        this.rute = rute;
        this.vehicleType = vehicleType;
        this.seats = seats;
        this.estadoPost=estadoPost;
        this.nameUser=name;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPhotoUser() {
        return photoUser;
    }

    public void setPhotoUser(String photoUser) {
        this.photoUser = photoUser;
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

    public EstadoPost getEstadoPost() {
        return estadoPost;
    }

    public void setEstadoPost(EstadoPost estadoPost) {
        this.estadoPost = estadoPost;
    }
}

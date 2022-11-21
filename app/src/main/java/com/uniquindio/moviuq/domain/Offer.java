package com.uniquindio.moviuq.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Offer extends Post implements Serializable {


    private List<Condition> myConditions;
    private String token;
    private int date;

    public Offer(){
        super();
    }
    public Offer(String id, String idUser,String name,String photoUser,String creationDate, String title, String description, String dateTravel, String hourTravel, Rute rute, VehicleType vehicleType, int seats, EstadoPost estadoPost,List<Condition> myConditions, String token,int datee) {
        super( id, idUser,name,photoUser,creationDate,  title,  description,  dateTravel,  hourTravel,  rute,  vehicleType,  seats,estadoPost);
        this.myConditions = myConditions;
        this.token=token;
        this.date=datee;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Condition> getMyConditions() {
        return myConditions;
    }

    public void setMyConditions(List<Condition> myConditions) {
        this.myConditions = myConditions;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}

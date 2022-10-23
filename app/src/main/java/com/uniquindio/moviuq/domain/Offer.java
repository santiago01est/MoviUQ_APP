package com.uniquindio.moviuq.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Offer extends Post implements Serializable {

    private List<Condition> myConditions;

    public Offer(){
        super();
    }
    public Offer(String id, String idUser,String creationDate, String title, String description, String dateTravel, String hourTravel, Rute rute, VehicleType vehicleType, int seats, EstadoPost estadoPost,List<Condition> myConditions) {
        super( id, idUser, creationDate,  title,  description,  dateTravel,  hourTravel,  rute,  vehicleType,  seats,estadoPost);
        this.myConditions = myConditions;
    }

    public List<Condition> getMyConditions() {
        return myConditions;
    }

    public void setMyConditions(List<Condition> myConditions) {
        this.myConditions = myConditions;
    }
}

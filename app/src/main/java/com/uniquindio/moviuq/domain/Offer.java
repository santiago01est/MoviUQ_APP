package com.uniquindio.moviuq.domain;

import java.util.Date;
import java.util.List;

public class Offer extends Post {

    private List<Condition> myConditions;

    public Offer(){
        super();
    }
    public Offer(String id, String creationDate, String title, String description, String dateTravel, String hourTravel, Rute rute, VehicleType vehicleType, int seats, List<Condition> myConditions) {
        super( id,  creationDate,  title,  description,  dateTravel,  hourTravel,  rute,  vehicleType,  seats);
        this.myConditions = myConditions;
    }

    public List<Condition> getMyConditions() {
        return myConditions;
    }

    public void setMyConditions(List<Condition> myConditions) {
        this.myConditions = myConditions;
    }
}

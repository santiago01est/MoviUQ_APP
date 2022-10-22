package com.uniquindio.moviuq.domain;

import java.util.Date;

public class Offer extends Post {

    private Rute rute;

    public Offer(){
        super();
    }
    public Offer(String id, Date date, String title, String description, VehicleType vehicleType, int seats, String startPoint, String arrivalPoint,Rute rute) {
        super(id, date, title, description, vehicleType, seats, startPoint, arrivalPoint);
        this.rute = rute;
    }

    public Rute getRute() {
        return rute;
    }

    public void setRute(Rute rute) {
        this.rute = rute;
    }
}

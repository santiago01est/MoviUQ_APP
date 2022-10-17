package com.uniquindio.moviuq.domain;

import java.util.Date;

public class Offer extends Post {

    private double score;

    public Offer(){
        super();
    }
    public Offer(String id, String date, String title, String description, VehicleType vehicleType, int seats, String startPoint, String arrivalPoint,double score) {
        super(id, date, title, description, vehicleType, seats, startPoint, arrivalPoint);
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}

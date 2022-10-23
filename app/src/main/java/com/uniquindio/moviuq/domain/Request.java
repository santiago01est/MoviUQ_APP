package com.uniquindio.moviuq.domain;

import java.util.Date;

public class Request extends Post{

    public Request() {
    }

    public Request(String id, String fecha, String title, String description, VehicleType vehicleType, int seats, String startPoint, String arrivalPoint) {
        super(id, fecha, title, description, vehicleType, seats, startPoint, arrivalPoint);
    }


}

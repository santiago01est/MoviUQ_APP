package com.uniquindio.moviuq.domain;

import java.util.Date;
import java.util.List;

public class Request extends Post{

    public Request() {
    }

    public Request(String id, String idUser,String creationDate, String title, String description, String dateTravel, String hourTravel, Rute rute, VehicleType vehicleType, int seats, EstadoPost estadoPost) {
        super( id,  idUser, creationDate,  title,  description,  dateTravel,  hourTravel,  rute,  vehicleType,  seats, estadoPost);
    }


}

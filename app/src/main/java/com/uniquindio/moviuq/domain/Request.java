package com.uniquindio.moviuq.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Request extends Post implements Serializable {

    private int date;
    public Request() {

    }

    public Request(String id, String idUser,String nameUser,String photoUser,String creationDate, String title, String description, String dateTravel, String hourTravel, Rute rute, VehicleType vehicleType, int seats, EstadoPost estadoPost,int datee) {
        super( id,  idUser,nameUser, photoUser,creationDate,  title,  description,  dateTravel,  hourTravel,  rute,  vehicleType,  seats, estadoPost);
        this.date=datee;
    }


}

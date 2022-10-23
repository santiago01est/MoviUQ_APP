package com.uniquindio.moviuq.domain;

import java.io.Serializable;

public enum VehicleType implements Serializable {

    CARRO, MOTO;

    public String tostring(VehicleType vehicleType){
        String vehicle="moto";
        if(vehicleType==CARRO){
            vehicle="carro";
        }
        return vehicle;
    }
}
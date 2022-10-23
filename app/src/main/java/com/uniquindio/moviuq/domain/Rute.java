package com.uniquindio.moviuq.domain;

import com.google.android.gms.maps.model.Marker;
import com.google.android.libraries.places.api.model.Place;

import java.io.Serializable;

public class Rute implements Serializable {

    private String id;
    private String idViaje;
    private MyPlace placeFrom;
    private MyPlace placeTo;

    public Rute() {
    }

    public Rute(String id, String idViaje, MyPlace placeFrom, MyPlace placeTo) {
        this.id = id;
        this.idViaje = idViaje;

        this.placeFrom = placeFrom;
        this.placeTo = placeTo;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(String idViaje) {
        this.idViaje = idViaje;
    }


    public MyPlace getPlaceFrom() {
        return placeFrom;
    }

    public void setPlaceFrom(MyPlace placeFrom) {
        this.placeFrom = placeFrom;
    }

    public MyPlace getPlaceTo() {
        return placeTo;
    }

    public void setPlaceTo(MyPlace placeTo) {
        this.placeTo = placeTo;
    }
}

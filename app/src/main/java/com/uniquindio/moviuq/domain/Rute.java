package com.uniquindio.moviuq.domain;

import com.google.android.gms.maps.model.Marker;
import com.google.android.libraries.places.api.model.Place;

public class Rute {

    private String id;
    private String idViaje;
    private Marker mMarkerFrom;
    private Marker mMarkerTo;
    private Place placeFrom;
    private Place placeTo;

    public Rute() {
    }

    public Rute(String id, String idViaje, Marker mMarkerFrom, Marker mMarkerTo, Place placeFrom, Place placeTo) {
        this.id = id;
        this.idViaje = idViaje;
        this.mMarkerFrom = mMarkerFrom;
        this.mMarkerTo = mMarkerTo;
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

    public Marker getmMarkerFrom() {
        return mMarkerFrom;
    }

    public void setmMarkerFrom(Marker mMarkerFrom) {
        this.mMarkerFrom = mMarkerFrom;
    }

    public Marker getmMarkerTo() {
        return mMarkerTo;
    }

    public void setmMarkerTo(Marker mMarkerTo) {
        this.mMarkerTo = mMarkerTo;
    }

    public Place getPlaceFrom() {
        return placeFrom;
    }

    public void setPlaceFrom(Place placeFrom) {
        this.placeFrom = placeFrom;
    }

    public Place getPlaceTo() {
        return placeTo;
    }

    public void setPlaceTo(Place placeTo) {
        this.placeTo = placeTo;
    }
}

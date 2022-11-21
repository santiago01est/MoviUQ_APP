package com.uniquindio.moviuq.domain;

public class PlaceMain {

    private String nombre;
    private String foto;

    public PlaceMain() {
    }

    public PlaceMain(String nombre, String foto) {
        this.nombre = nombre;
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}

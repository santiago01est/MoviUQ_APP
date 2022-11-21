package com.uniquindio.moviuq.domain;

import java.io.Serializable;

public class Chat implements Serializable {

    private String id;
    private String usernameYou;
    private String usernameMe;
    private String idMe;
    private String idYou;
    private String perfilYou;
    private String perfilMe;
    private String fecha;
    private String ultimoMensaje;

    public Chat() {
    }

    public Chat(String id, String usernameYou, String usernameMe, String idMe, String idYou, String perfilYou, String perfilMe, String fecha, String ultimoMensaje) {
        this.id = id;
        this.usernameYou = usernameYou;
        this.usernameMe = usernameMe;
        this.idMe = idMe;
        this.idYou = idYou;
        this.perfilYou = perfilYou;
        this.perfilMe = perfilMe;
        this.fecha = fecha;
        this.ultimoMensaje = ultimoMensaje;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsernameYou() {
        return usernameYou;
    }

    public void setUsernameYou(String usernameYou) {
        this.usernameYou = usernameYou;
    }

    public String getUsernameMe() {
        return usernameMe;
    }

    public void setUsernameMe(String usernameMe) {
        this.usernameMe = usernameMe;
    }

    public String getIdMe() {
        return idMe;
    }

    public void setIdMe(String idMe) {
        this.idMe = idMe;
    }

    public String getIdYou() {
        return idYou;
    }

    public void setIdYou(String idYou) {
        this.idYou = idYou;
    }

    public String getPerfilYou() {
        return perfilYou;
    }

    public void setPerfilYou(String perfilYou) {
        this.perfilYou = perfilYou;
    }

    public String getPerfilMe() {
        return perfilMe;
    }

    public void setPerfilMe(String perfilMe) {
        this.perfilMe = perfilMe;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUltimoMensaje() {
        return ultimoMensaje;
    }

    public void setUltimoMensaje(String ultimoMensaje) {
        this.ultimoMensaje = ultimoMensaje;
    }
}

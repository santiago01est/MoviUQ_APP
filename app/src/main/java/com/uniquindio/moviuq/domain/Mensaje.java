package com.uniquindio.moviuq.domain;

import java.io.Serializable;

public class Mensaje implements Serializable {

    private String id;
    private String idChat;
    private String username;
    private String foto;
    private String fecha;
    private String mensaje;


    public Mensaje() {
    }

    public Mensaje(String id, String idChat, String username, String foto, String fecha, String mensaje) {
        this.id = id;
        this.idChat = idChat;
        this.username = username;
        this.foto = foto;
        this.fecha = fecha;
        this.mensaje = mensaje;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdChat() {
        return idChat;
    }

    public void setIdChat(String idChat) {
        this.idChat = idChat;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}

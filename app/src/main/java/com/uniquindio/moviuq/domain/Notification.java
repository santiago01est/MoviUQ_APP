package com.uniquindio.moviuq.domain;

import java.io.Serializable;
import java.util.Date;

public class Notification implements Serializable {

    private String id;
    private String date;
    private String description;
    private String PhotouserTransmitter;
    private TypeNotification type;
    private String emailUserReceiver;
    private String tokenRespuesta;


    public Notification() {

    }

    public Notification(String id, String date, String description, String photouserTransmitter, TypeNotification type, String emailUserReceiver, String tokenRespuesta) {
        this.id = id;
        this.date = date;
        this.description = description;
        PhotouserTransmitter = photouserTransmitter;
        this.type = type;
        this.emailUserReceiver = emailUserReceiver;
        this.tokenRespuesta = tokenRespuesta;
    }

    public String getTokenRespuesta() {
        return tokenRespuesta;
    }

    public void setTokenRespuesta(String tokenRespuesta) {
        this.tokenRespuesta = tokenRespuesta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmailUserReceiver() {
        return emailUserReceiver;
    }

    public void setEmailUserReceiver(String emailUserReceiver) {
        this.emailUserReceiver = emailUserReceiver;
    }

    public TypeNotification getType() {
        return type;
    }

    public void setType(TypeNotification type) {
        this.type = type;
    }

    public String getPhotouserTransmitter() {
        return PhotouserTransmitter;
    }

    public void setPhotouserTransmitter(String photouserTransmitter) {
        PhotouserTransmitter = photouserTransmitter;
    }
}

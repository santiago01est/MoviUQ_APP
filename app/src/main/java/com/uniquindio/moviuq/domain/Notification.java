package com.uniquindio.moviuq.domain;

import java.io.Serializable;
import java.util.Date;

public class Notification implements Serializable {

    private String id;
    private String date;
    private String description;
    //private User userTransmitter;
    //private TypeNotification type;
    private String emailUserReceiver;

    public Notification() {

    }

    public Notification(String id, String date, String description, String emailUserReceiver) {
        this.id = id;
        this.date = date;
        this.description = description;

        this.emailUserReceiver = emailUserReceiver;
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
}

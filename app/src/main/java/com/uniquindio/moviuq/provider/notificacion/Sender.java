package com.uniquindio.moviuq.provider.notificacion;

public class Sender {

    private String token;
    private Notification notification;

    public Sender() {
    }

    public Sender(String token, Notification notification) {
        this.token = token;
        this.notification = notification;
    }

    public String gettoken() {
        return token;
    }

    public void settoken(String token) {
        this.token = token;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }
}

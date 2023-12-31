package com.uniquindio.moviuq.domain;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    private String name;
    private String last_name;
    private String photo;
    private String mail;
    private long phoneNumber;
    private int years;
    private String city;
    private String token;


    public User() {
    }

    public User(String name, String last_name, String photo, String mail, long phoneNumber, int years, String city, String token) {
        this.name = name;
        this.last_name = last_name;
        this.photo = photo;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.years = years;
        this.city = city;
        this.token=token;

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


}

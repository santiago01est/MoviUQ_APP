package com.uniquindio.moviuq.data;

import android.app.Activity;
import android.widget.TextView;

import com.uniquindio.moviuq.domain.User;

public interface ProfileService {


    void crearPerfil(String photo, String name, String last_name, int years, long phoneNumber, int city, Activity activity);
    void logOutFromProfile(Activity activity);
    void updateInformation (String name, String lastName, String numberPhone, String city, String years, Activity activity);
}

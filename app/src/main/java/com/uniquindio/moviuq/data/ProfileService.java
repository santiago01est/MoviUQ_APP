package com.uniquindio.moviuq.data;

import android.app.Activity;

public interface ProfileService {


    void crearPerfil(String photo, String name, String last_name, int years, long phoneNumber, int city, Activity activity);
}

package com.uniquindio.moviuq.data;

import android.app.Activity;
import android.widget.TextView;

public interface ProfileService {


    void crearPerfil(String photo, String name, String last_name, int years, long phoneNumber, int city, Activity activity);
    void cargarDatosPerfil(TextView txv_nameProfileUser, Activity activity);
}

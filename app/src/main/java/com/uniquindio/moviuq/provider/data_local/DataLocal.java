package com.uniquindio.moviuq.provider.data_local;

import android.util.Log;

import com.uniquindio.moviuq.domain.User;

/** Clase que usa el patron singleton para guardar datos globales
 * y aumentar la disponibilidad, optimización y rendimiento
 * de la representación de la información en pantalla**/
public class DataLocal {

    private static User user;
    private static String token;

    public DataLocal() {
    }
    public static User getUser() {

        if (user != null) {
            return user;
        } else {
            return null;
        }
    }


    public static void setUser(User user) {
        DataLocal.user = user;
    }

    /** token*/
    public static String getToken() {
        return DataLocal.token;
    }

    public static void setToken(String token) {
        DataLocal.token = token;
        Log.d("tokenbdl",token);
    }
}

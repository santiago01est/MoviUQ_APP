package com.uniquindio.moviuq.data;

import android.app.Activity;

import com.google.firebase.auth.FirebaseUser;

public interface UserService {

    void registrarUsuario(String email, String password, Activity activity);
    void iniciarSesionUsuario(String email, String password, Activity activity);
    String getEmailUser();
}

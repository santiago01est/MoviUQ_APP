package com.uniquindio.moviuq.data;

import android.app.Activity;


public interface UserService {

    void registrarUsuario(String email, String password, Activity activity);

    void iniciarSesionUsuario(String email, String password, Activity activity);

    String getEmailUser();

    void getUser(String email);

}

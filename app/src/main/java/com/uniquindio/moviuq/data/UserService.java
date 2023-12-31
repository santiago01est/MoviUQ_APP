package com.uniquindio.moviuq.data;

import android.app.Activity;

import com.uniquindio.moviuq.domain.User;


public interface UserService {

    void registrarUsuario(String email, String password, Activity activity);

    void iniciarSesionUsuario(String email, String password, Activity activity);

    String getEmailUser();

    void getUser();

    void updateToken(User user);

}

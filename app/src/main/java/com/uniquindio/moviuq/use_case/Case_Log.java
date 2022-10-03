package com.uniquindio.moviuq.use_case;

import android.app.Activity;
import android.content.Intent;

import com.google.firebase.auth.FirebaseUser;
import com.uniquindio.moviuq.data.UserImpl;
import com.uniquindio.moviuq.data.UserService;
import com.uniquindio.moviuq.presentation.MainActivity;


public class Case_Log {

    private Activity activity;
    private UserService userService = new UserImpl();

    public Case_Log(Activity activity) {
        this.activity = activity;
    }

    public void login_user(String email, String pass) {
        userService.iniciarSesionUsuario(email, pass, activity);
    }

    public void updateUI(FirebaseUser user) {

        if (user != null) {
            Intent intent = new Intent(activity, MainActivity.class);
            activity.startActivity(intent);
            activity.finish();
        }
    }

    public void lanzarWelcome() {
        Intent i = new Intent(activity, MainActivity.class);
        activity.startActivity(i);
        //activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        activity.finish();
    }
}

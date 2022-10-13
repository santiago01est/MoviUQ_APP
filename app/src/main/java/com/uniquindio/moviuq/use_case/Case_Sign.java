package com.uniquindio.moviuq.use_case;

import android.app.Activity;
import android.content.Intent;

import com.uniquindio.moviuq.data.UserImpl;
import com.uniquindio.moviuq.data.UserService;
import com.uniquindio.moviuq.presentation.activity.CreateProfileActivity;
import com.uniquindio.moviuq.presentation.activity.LoginActivity;
import com.uniquindio.moviuq.presentation.activity.MainActivity;

public class Case_Sign {

    private Activity activity;
    private UserService userService=new UserImpl();

    public Case_Sign(Activity activity) {
        this.activity = activity;

    }



    public void sign_user(String email, String password) {

        userService.registrarUsuario(email,password,activity);

    }

    public void lanzarCreateProfile() {
        Intent i = new Intent(activity, CreateProfileActivity.class);
        activity.startActivity(i);
        activity.finish();
    }

    public void lanzarLogin() {
        Intent i = new Intent(activity, LoginActivity.class);
        activity.startActivity(i);
        //activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        activity.finish();
    }
    public void lanzarWelcome() {
        Intent i = new Intent(activity, MainActivity.class);
        activity.startActivity(i);
        //activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        activity.finish();
    }


}

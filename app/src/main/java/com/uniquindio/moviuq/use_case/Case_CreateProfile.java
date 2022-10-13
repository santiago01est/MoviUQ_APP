package com.uniquindio.moviuq.use_case;

import android.app.Activity;
import android.content.Intent;

import com.uniquindio.moviuq.data.ProfileImpl;
import com.uniquindio.moviuq.data.ProfileService;
import com.uniquindio.moviuq.data.UserImpl;
import com.uniquindio.moviuq.data.UserService;
import com.uniquindio.moviuq.presentation.activity.HomeActivity;
import com.uniquindio.moviuq.presentation.activity.MainActivity;

public class Case_CreateProfile {

    private Activity activity;
    private ProfileService profileService = new ProfileImpl();

    public Case_CreateProfile(Activity activity){
        this.activity= activity;
    }

    public void create_profile(String photo,String name, String last_name, int years, long phoneNumber, int city){
        profileService.crearPerfil(photo, name, last_name, years, phoneNumber, city, activity);
    }

    public void lanzarHome() {
        Intent i = new Intent(activity, HomeActivity.class);
        activity.startActivity(i);
        //activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        activity.finish();
    }

}

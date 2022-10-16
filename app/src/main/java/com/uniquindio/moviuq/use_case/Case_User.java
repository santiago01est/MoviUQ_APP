package com.uniquindio.moviuq.use_case;

import android.app.Activity;

import com.uniquindio.moviuq.data.UserImpl;
import com.uniquindio.moviuq.data.UserService;

public class Case_User {

    private Activity activity;
    private UserService userService = new UserImpl();

    public Case_User(Activity activity) {
        this.activity = activity;
    }

    public String getEmailUser (){
        return userService.getEmailUser();
    }
}

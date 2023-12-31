package com.uniquindio.moviuq.use_case;

import android.app.Activity;

import com.uniquindio.moviuq.data.UserImpl;
import com.uniquindio.moviuq.data.UserService;
import com.uniquindio.moviuq.provider.data_local.DataLocal;

public class Case_User  {

    private Activity activity;
    private UserService userService = new UserImpl();

    public Case_User(Activity activity) {
        this.activity = activity;
    }

    public String getEmailUser (){
        return userService.getEmailUser();
    }

    public void getUser(){
        userService.getUser();
    }

    public void updateToken(){
        userService.updateToken(DataLocal.getUser());
    }




}

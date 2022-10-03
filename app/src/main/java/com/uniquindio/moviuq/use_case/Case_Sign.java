package com.uniquindio.moviuq.use_case;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.firestore.QuerySnapshot;
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.data.UserImpl;
import com.uniquindio.moviuq.data.UserService;
import com.uniquindio.moviuq.presentation.LoginActivity;
import com.uniquindio.moviuq.presentation.MainActivity;
import com.uniquindio.moviuq.provider.services.firebase.FirebaseAuthService;
import com.uniquindio.moviuq.provider.services.firebase.FirebaseCFDBService;

public class Case_Sign {

    private Activity activity;
    private UserService userService=new UserImpl();

    public Case_Sign(Activity activity) {
        this.activity = activity;

    }


    public void sign_user(String email, String password) {

        userService.registrarUsuario(email,password,activity);

    }
/*
    public void create_user() {
        Intent i = new Intent(activity, CreatePerfilActivity.class);
        activity.startActivity(i);
        activity.finish();
    }
 */
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

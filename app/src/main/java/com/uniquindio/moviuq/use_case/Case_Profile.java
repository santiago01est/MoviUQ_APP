package com.uniquindio.moviuq.use_case;

import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

import com.uniquindio.moviuq.data.ProfileImpl;
import com.uniquindio.moviuq.data.ProfileService;
import com.uniquindio.moviuq.data.UserImpl;
import com.uniquindio.moviuq.data.UserService;
import com.uniquindio.moviuq.presentation.activity.HomeActivity;
import com.uniquindio.moviuq.presentation.activity.MainActivity;

public class Case_Profile {

    private Activity activity;
    private ProfileService profileService = new ProfileImpl();


    /** Inicializa la actividad de caso de uso**/
    public Case_Profile(Activity activity){
        this.activity= activity;
    }

    /** Metodo para redireccionar los datos obtenidos atravez de la activity createProfileActivity
     *  a la capa de datos.
     **/

    public void create_profile(String photo,String name, String last_name, int years, long phoneNumber, int city){
        profileService.crearPerfil(photo, name, last_name, years, phoneNumber, city, activity);
    }
    public void loadData(TextView txv_nameProfileUser){
        profileService.cargarDatosPerfil(txv_nameProfileUser, activity);
    }
    /** Metodo para entrar en la ventana del home**/
    public void lanzarHome() {
        Intent i = new Intent(activity, HomeActivity.class);
        activity.startActivity(i);
        //activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        activity.finish();
    }

}

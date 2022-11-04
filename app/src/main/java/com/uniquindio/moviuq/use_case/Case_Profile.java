package com.uniquindio.moviuq.use_case;

import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.data.ProfileImpl;
import com.uniquindio.moviuq.data.ProfileService;
import com.uniquindio.moviuq.data.UserImpl;
import com.uniquindio.moviuq.data.UserService;
import com.uniquindio.moviuq.domain.User;
import com.uniquindio.moviuq.presentation.activity.EditProfileUserActivity;
import com.uniquindio.moviuq.presentation.activity.HomeActivity;
import com.uniquindio.moviuq.presentation.activity.MainActivity;
import com.uniquindio.moviuq.presentation.activity.MyOfferListActivity;
import com.uniquindio.moviuq.presentation.activity.MyRequestListActivity;

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

    public void create_profile(String photo,String name, String last_name, int years, long phoneNumber, String city){
        profileService.crearPerfil(photo, name, last_name, years, phoneNumber, city, activity);
    }
    /** Metodo para accionar la funcionalidad de cerrar sesion desde profileUserFragment
     **/
    public void logOutFromProfile(){
        profileService.logOutFromProfile(activity);
    }

    /** Metodo para redireccionar los datos al servicio e implementacion de actualizacion de informacion
     *  accionada en el editProfileUserActivity
     **/
    public void updateInformation(String name, String lastName, String numberPhone, String city, String years){
        profileService.updateInformation(name, lastName, numberPhone, city, years, activity);
    }

    /** Metodo para entrar en la ventana del home**/
    public void lanzarHome() {
        Intent i = new Intent(activity, HomeActivity.class);
        activity.startActivity(i);

    }

    /** Metodo para entrar en la ventana de mi solicitudes**/
    public void lanzarMyRequest() {
        Intent i = new Intent(activity, MyRequestListActivity.class);
        activity.startActivity(i);

    }

    /** Metodo para entrar en la ventana de mi solicitudes**/
    public void lanzarMyOffer() {
        Intent i = new Intent(activity, MyOfferListActivity.class);
        activity.startActivity(i);

    }

    /** Metodo para entrar en la ventana de editar perfil del usurio**/
    public void lanzarEditProfileUser() {
        Intent i = new Intent(activity, EditProfileUserActivity.class);
        activity.startActivity(i);

    }



}

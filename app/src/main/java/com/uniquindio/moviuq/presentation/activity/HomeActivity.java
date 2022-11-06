package com.uniquindio.moviuq.presentation.activity;

import static com.uniquindio.moviuq.R.id.inicio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.presentation.fragments.HomeFragment;
import com.uniquindio.moviuq.presentation.fragments.OfferTravelFragment;
import com.uniquindio.moviuq.presentation.fragments.RequestFragment;
import com.uniquindio.moviuq.presentation.fragments.ProfileUserFragment;
import com.uniquindio.moviuq.use_case.Case_User;

public class HomeActivity extends AppCompatActivity {

    /** Casos de uso**/
    private Case_User case_user;

    /** Elementos UI**/
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /** Iniciacion de variables**/
        case_user=new Case_User(this);
        /**Obtener usuario en sesion para ser guardado localmente **/
        getUserGlobal();

        bottomNavigationView=findViewById(R.id.bottomNavigationView);


        replaceFragments(new HomeFragment().newInstance(this));
        bottomNavigationView.setOnItemSelectedListener(item -> {

            int id= item.getItemId();
            if(id== R.id.inicio){
                replaceFragments(new HomeFragment().newInstance(this));
            }else if(id == R.id.viajes){
                replaceFragments(new OfferTravelFragment().newInstance());
            }else if(id == R.id.solicitud){
                replaceFragments(new RequestFragment().newInstance());
            }else if(id == R.id.chats){

            }else if(id == R.id.perfil){
                replaceFragments(new ProfileUserFragment().newInstance());
            }else{

            }


            return true;
        });
    }


    private void getUserGlobal(){

        case_user.getUser();
    }

    /** Metodo encargado de inflar el fragment correspondiente al boton indicado del menu
     *  Documentación de Android Develop**/
    private void replaceFragments(Fragment fragment){
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_home, fragment);
        fragmentTransaction.commit();

    }
}
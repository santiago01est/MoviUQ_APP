package com.uniquindio.moviuq.presentation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.presentation.fragments.HomeFragment;
import com.uniquindio.moviuq.presentation.fragments.ProfileUserFragment;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        BottomNavigationView menu=findViewById(R.id.bottomNavigationView);

        replaceFragments(new HomeFragment().newInstance());
        bottomNavigationView.setOnItemSelectedListener(item -> {


            switch (item.getItemId()){

                case R.id.inicio:
                    replaceFragments(new HomeFragment().newInstance());
                    break;
                case R.id.viajes:
                   // replaceFragments(new GalleryFragment().newInstance(toobar,bottomNavigationView,user.getEmail()));
                    break;
                case R.id.solicitud:
                   // replaceFragments(new SearchFragment().newInstance(toobar,bottomNavigationView,user.getEmail()));
                    break;
                case R.id.chats:
                   // replaceFragments(new SocialFragment().newInstance(toobar,bottomNavigationView,user.getEmail()));
                    break;
                case R.id.perfil:
                    replaceFragments(new ProfileUserFragment().newInstance());
                    break;

            }

            return true;
        });
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
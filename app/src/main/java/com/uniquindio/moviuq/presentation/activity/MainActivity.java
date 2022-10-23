package com.uniquindio.moviuq.presentation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseUser;
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.provider.services.firebase.FirebaseAuthService;

public class MainActivity extends AppCompatActivity {

    LottieAnimationView animation;

    @Override
    protected void onStart() {
        super.onStart();


        FirebaseUser user = FirebaseAuthService.getAuth().getCurrentUser();

        if(user!=null){
            Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
            startActivity(intent);
            finish();
        }


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animation=findViewById(R.id.lottie_main);
        animation.playAnimation();



    }

    public void lanzarLogin(View view) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        /*
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);

         */
     //   overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }

    public void lanzarSign(View view) {
        Intent i = new Intent(this, SignActivity.class);
        startActivity(i);
        //   overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }
}
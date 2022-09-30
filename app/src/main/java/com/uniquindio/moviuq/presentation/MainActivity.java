package com.uniquindio.moviuq.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.uniquindio.moviuq.R;

public class MainActivity extends AppCompatActivity {

    LottieAnimationView animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animation=findViewById(R.id.lottie_main);
        animation.playAnimation();
    }

    public void lanzarLogin(View view) {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
     //   overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }

    public void lanzarSign(View view) {
        Intent i = new Intent(this, Sign.class);
        startActivity(i);
        //   overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }
}
package com.uniquindio.moviuq.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.uniquindio.moviuq.R;

public class Login extends AppCompatActivity {

    LottieAnimationView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        img=findViewById(R.id.lottie_login);
        img.playAnimation();
    }
}
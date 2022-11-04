package com.uniquindio.moviuq.presentation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.provider.services.firebase.FirebaseAuthService;
import com.uniquindio.moviuq.use_case.Case_User;

public class MainActivity extends AppCompatActivity {

    /** Casos de uso**/
    private Case_User case_user;
    private Button login,sign;

    LottieAnimationView animation;

    @Override
    protected void onStart() {
        super.onStart();
        case_user=new Case_User(this);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        login=findViewById(R.id.bttn_iniciar);
        sign=findViewById(R.id.bttn_registrar);

        if(user!=null){
            case_user.getUser();
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
            finish();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SignActivity.class);
                startActivity(i);
            }
        });


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animation=findViewById(R.id.lottie_main);
        animation.playAnimation();


    }




}
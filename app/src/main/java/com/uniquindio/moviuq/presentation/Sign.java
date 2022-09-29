package com.uniquindio.moviuq.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.textfield.TextInputEditText;
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.use_case.Case_Sign;

public class Sign extends AppCompatActivity {

    LottieAnimationView animation;
    Case_Sign case_sign;
    TextInputEditText sign_email, sign_pass, sign_pass_confirmar;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        case_sign= new Case_Sign(this);
        animation=findViewById(R.id.lottie_sign);
        animation.playAnimation();

        sign_email=findViewById(R.id.login_correo);
        sign_pass=findViewById(R.id.sign_password);
        sign_pass_confirmar=findViewById(R.id.sign_confirm_password);
        progressBar = findViewById(R.id.progressBar_sign);
    }


    public void signUser(View view){


        if(sign_pass.getText().toString().equals(sign_pass_confirmar.getText().toString())){
            if(sign_pass.getText().toString().length()<6){
                Toast.makeText(this, "La contraseña debe tener minimo 8 caracteres", Toast.LENGTH_SHORT).show();
            }else{
                case_sign.sign_user(sign_email.getText().toString(), sign_pass.getText().toString());
                progressBar.setVisibility(View.VISIBLE);
            }

        }else{
            Toast.makeText(this, "Vuelve a escribir tu contraseña", Toast.LENGTH_SHORT).show();
        }



    }
}
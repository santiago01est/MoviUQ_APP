package com.uniquindio.moviuq.presentation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.textfield.TextInputEditText;
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.data.VerificationImpl;
import com.uniquindio.moviuq.data.VerificationService;
import com.uniquindio.moviuq.use_case.Case_Sign;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SignActivity extends AppCompatActivity {

    LottieAnimationView animation;
    Case_Sign case_sign;
    TextInputEditText sign_email, sign_pass, sign_pass_confirmar;
    private VerificationService verificationService = new VerificationImpl();
    private ProgressBar progressBar;
    private Button sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        case_sign = new Case_Sign(this);
        animation = findViewById(R.id.lottie_sign);
        animation.playAnimation();

        sign_email = findViewById(R.id.sign_correo);
        sign_pass = findViewById(R.id.sign_password);
        sign_pass_confirmar = findViewById(R.id.sign_confirm_password);
        progressBar = findViewById(R.id.progressBar_sign);
        sign = findViewById(R.id.btn_login_iniciar);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass = sign_pass.getText().toString();
                String passConfirm = sign_pass_confirmar.getText().toString();
                String email = sign_email.getText().toString();
                List<String> campos = new ArrayList<>(Arrays.asList(pass, passConfirm));

                if (verificationService.camposVacios(campos, -2)) {
                    Toast.makeText(SignActivity.this, "Hay campos vacios, porfavor llene todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    if (verificationService.verificarContraseñaSignIn(pass, passConfirm)) {
                        if (verificationService.verificarCorreo(email)) {
                            if (verificationService.contraseñaMinima(pass)) {
                                Toast.makeText(SignActivity.this, "La contraseña debe tener minimo 8 caracteres", Toast.LENGTH_SHORT).show();
                            } else {

                                case_sign.sign_user(email, passConfirm);
                                progressBar.setVisibility(View.VISIBLE);
                            }
                        } else {

                            Toast.makeText(SignActivity.this, "El correo no pertenece a la universidad del Quindio", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignActivity.this, "Vuelve a escribir tu contraseña", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}
package com.uniquindio.moviuq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void lanzarLogin(View view) {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
     //   overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }
}
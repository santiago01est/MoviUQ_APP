package com.uniquindio.moviuq.presentation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.use_case.Case_CreateProfile;

public class CreateProfileActivity extends AppCompatActivity {

    private Case_CreateProfile case_createProfile;
    TextInputEditText crProf_name, crProf_lastName, crProf_years, crProf_phoneNumber, crProf_city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        /** USE CASE **/
        case_createProfile=new Case_CreateProfile(this);

        /** REFERENCE ELEMETS**/
        crProf_name =findViewById(R.id.profile_name);
        crProf_lastName =findViewById(R.id.profile_lastname);
        crProf_years = findViewById(R.id.profile_years);
        crProf_phoneNumber = findViewById(R.id.profile_phone);
        crProf_city = findViewById(R.id.profile_city);
    }

    public void createProfile(View view){
        if(crProf_name.getText().toString().isEmpty() || crProf_lastName.getText().toString().isEmpty()
            || crProf_years.getText().toString().isEmpty() || crProf_phoneNumber.getText().toString().isEmpty()
            || crProf_city.getText().toString().isEmpty()){

            Toast.makeText(this, "Ningun campo puede estar vacio, llena los campos porfavor", Toast.LENGTH_SHORT).show();
        }else{
            String name= crProf_name.getText().toString();
            String last_name= crProf_lastName.getText().toString();
            int years = Integer.parseInt(crProf_years.getText().toString());
            long phoneNumber= Long.parseLong(crProf_years.getText().toString());
            int city= Integer.parseInt(crProf_city.getText().toString());
            String photo="default";
            case_createProfile.create_profile(photo, name, last_name, years, phoneNumber, city);
        }

    }

}
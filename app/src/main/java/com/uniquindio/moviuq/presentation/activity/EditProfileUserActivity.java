package com.uniquindio.moviuq.presentation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.domain.User;
import com.uniquindio.moviuq.provider.data_local.DataLocal;
import com.uniquindio.moviuq.provider.services.firebase.FirebaseCFDBService;
import com.uniquindio.moviuq.use_case.Case_Profile;
import com.uniquindio.moviuq.use_case.Case_User;

public class EditProfileUserActivity extends AppCompatActivity {

    private Case_Profile case_profile;
    private Case_User case_user;
    private TextInputEditText txil_name_editProfileUser, txil_lastName_editProfileUser, txil_city_editProfileUser,
            txil_numberPhone_editProfileUser, txil_years_editProfileUser;
    private Button btn_actualizarPerfil;
    private ImageView photoUser;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_user);

        /** USE CASE **/
        case_profile= new Case_Profile(this);
        case_user= new Case_User(this);
        user=DataLocal.getUser();

        /** REFERENCE ELEMETS**/
        txil_name_editProfileUser= findViewById(R.id.txiet_name_editProfileUser);
        txil_lastName_editProfileUser= findViewById(R.id.txiet_lastName_editProfileUser);
        txil_numberPhone_editProfileUser= findViewById(R.id.txiet_numberPhone_editProfileUser);
        txil_city_editProfileUser= findViewById(R.id.txiet_city_editProfileUser);
        txil_years_editProfileUser= findViewById(R.id.txiet_years_editProfileUser);
        photoUser=findViewById(R.id.shiv_photoProfileUser);






        btn_actualizarPerfil= findViewById(R.id.btn_actualizarPerfil);


        /** Obtener datos del usuario en sesion**/
        updateDate();

        /** Funcionalidad que se acciona en el diseño de edit_profile_user_activity,
         *  se obtienen los datos que se quieren editar del perfil ingresados en los campos de texto y
         *  se llama el caso de uso con el metodo correspondiente a dicha funcionalidad
         **/
        btn_actualizarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=txil_name_editProfileUser.getText().toString();
                String lastName=txil_lastName_editProfileUser.getText().toString();
                String numberPhone=txil_numberPhone_editProfileUser.getText().toString();
                String city=txil_city_editProfileUser.getText().toString();
                String years=txil_years_editProfileUser.getText().toString();

                case_profile.updateInformation(name, lastName, numberPhone, city, years);


            }
        });

    }

    /** updateDate
     *
     *  Este metodo es propio de esta actividad y se encarga de mantener actualizado los datos
     *  cuando el usuario haga una edicion a algun tipo de su informacion
     *
     **/
    public void updateDate(){
                txil_name_editProfileUser.setText(user.getName());
                txil_lastName_editProfileUser.setText(user.getLast_name());
                txil_numberPhone_editProfileUser.setText(String.valueOf(user.getPhoneNumber()));
                txil_city_editProfileUser.setText(user.getCity());
                txil_years_editProfileUser.setText(String.valueOf(user.getYears()));
                /** Get photo**/
                Glide.with(this )
                        .load(DataLocal.getUser().getPhoto())
                        .into(photoUser);

    }


}
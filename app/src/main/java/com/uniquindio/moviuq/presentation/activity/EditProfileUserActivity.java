package com.uniquindio.moviuq.presentation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.domain.User;
import com.uniquindio.moviuq.provider.services.firebase.FirebaseCFDBService;
import com.uniquindio.moviuq.use_case.Case_Profile;
import com.uniquindio.moviuq.use_case.Case_User;

public class EditProfileUserActivity extends AppCompatActivity {

    private Case_Profile case_profile;
    private Case_User case_user;
    private TextInputLayout txil_name_editProfileUser, txil_lastName_editProfileUser, txil_city_editProfileUser,
            txil_numberPhone_editProfileUser, txil_years_editProfileUser;
    private TextInputEditText txiet_name_editProfileUser, txiet_lastName_editProfileUser, txiet_city_editProfileUser,
            txiet_numberPhone_editProfileUser, txiet_years_editProfileUser;
    private Button btn_actualizarPerfil;
    private String name, lastName, numberPhone, city, years;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_user);

        case_profile= new Case_Profile(this);
        case_user= new Case_User(this);

        txil_name_editProfileUser= findViewById(R.id.txil_name_editProfileUser);
        txil_lastName_editProfileUser= findViewById(R.id.txil_lastName_editProfileUser);
        txil_numberPhone_editProfileUser= findViewById(R.id.txil_numberPhone_editProfileUser);
        txil_city_editProfileUser= findViewById(R.id.txil_city_editProfileUser);
        txil_years_editProfileUser= findViewById(R.id.txil_years_editProfileUser);

        txiet_name_editProfileUser= findViewById(R.id.txiet_name_editProfileUser);
        txiet_lastName_editProfileUser= findViewById(R.id.txiet_lastName_editProfileUser);
        txiet_numberPhone_editProfileUser= findViewById(R.id.txiet_numberPhone_editProfileUser);
        txiet_city_editProfileUser= findViewById(R.id.txiet_city_editProfileUser);
        txiet_years_editProfileUser= findViewById(R.id.txiet_years_editProfileUser);

        btn_actualizarPerfil= findViewById(R.id.btn_actualizarPerfil);
        updateDate();
        btn_actualizarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=txiet_name_editProfileUser.getText().toString();
                lastName=txiet_lastName_editProfileUser.getText().toString();
                numberPhone=txiet_numberPhone_editProfileUser.getText().toString();
                city=txiet_city_editProfileUser.getText().toString();
                years=txiet_years_editProfileUser.getText().toString();
                case_profile.updateInformation(name, lastName, numberPhone, city, years);
                updateDate();

            }
        });

    }

    public void updateDate(){
        DocumentReference docRef = FirebaseCFDBService.getBD().collection("user").document(case_user.getEmailUser());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                user = documentSnapshot.toObject(User.class);
                txil_name_editProfileUser.setHint(user.getName());
                txil_lastName_editProfileUser.setHint(user.getLast_name());
                txil_numberPhone_editProfileUser.setHint(""+user.getPhoneNumber());
                txil_city_editProfileUser.setHint(""+user.getCity());
                txil_years_editProfileUser.setHint(""+user.getYears());
            }
        });
    }
}
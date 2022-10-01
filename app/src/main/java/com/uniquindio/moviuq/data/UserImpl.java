package com.uniquindio.moviuq.data;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.firestore.QuerySnapshot;
import com.uniquindio.moviuq.provider.services.firebase.FirebaseAuthService;
import com.uniquindio.moviuq.provider.services.firebase.FirebaseCFDBService;
import com.uniquindio.moviuq.use_case.Case_Sign;

public class UserImpl implements UserService{

    private Case_Sign case_sign;


    @Override
    public void registrarUsuario(String email, String password,Activity activity) {

        case_sign= new Case_Sign(activity);


        FirebaseCFDBService.getBD().collection("user").whereEqualTo("correo", email).
                get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            QuerySnapshot query=task.getResult();
                            if (query.size() == 0) {
                                FirebaseAuthService.getAuth().
                                        createUserWithEmailAndPassword(email, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(activity, "Registro Exitoso!", Toast.LENGTH_SHORT).show();
                                                    //User user=new User("santy",email,"ES",0,0,null,null,null,null);
                                                    //FirebaseCFDBService.getBD().collection("users").document(email).set(user);
                                                    //create_user();

                                                    case_sign.lanzarWelcome();
                                                } else {

                                                    Toast.makeText(activity, "Ups! hubo un error inesperado", Toast.LENGTH_SHORT).show();
                                                }

                                            }
                                        });
                            } else {
                                Toast.makeText(activity, "Ya existe un usuario con el correo ingresado", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                });

    }
}

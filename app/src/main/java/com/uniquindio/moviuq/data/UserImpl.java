package com.uniquindio.moviuq.data;

import static com.android.volley.VolleyLog.TAG;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.QuerySnapshot;
import com.uniquindio.moviuq.provider.services.firebase.FirebaseAuthService;
import com.uniquindio.moviuq.provider.services.firebase.FirebaseCFDBService;
import com.uniquindio.moviuq.use_case.Case_Log;
import com.uniquindio.moviuq.use_case.Case_Sign;

public class UserImpl  implements UserService {

    private Case_Sign case_sign;
    private Case_Log case_log;



    @Override
    public void registrarUsuario(String email, String password, Activity activity) {

        case_sign = new Case_Sign(activity);


        FirebaseCFDBService.getBD().collection("user").whereEqualTo("email", email).
                get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot query = task.getResult();
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

                                                    case_sign.lanzarCreate_user();
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

    @Override
    public void iniciarSesionUsuario(String email, String password, Activity activity) {

        case_log = new Case_Log(activity);

        FirebaseAuthService.getAuth().signInWithEmailAndPassword(email, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuthService.getAuth().getCurrentUser();
                    case_log.updateUI(user);
                    //session enviarle email
                } else {
                    Toast.makeText(activity, "error al ingresar, Intentalo de nuevo", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public String getEmailUser() {

        FirebaseUser userSession= FirebaseAuthService.getAuth().getCurrentUser();
        String email= userSession.getEmail();
        return email;
    }




}

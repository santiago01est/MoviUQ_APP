package com.uniquindio.moviuq.data;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.messaging.FirebaseMessaging;
import com.uniquindio.moviuq.domain.User;
import com.uniquindio.moviuq.provider.data_local.DataLocal;
import com.uniquindio.moviuq.provider.notificacion.MyFirebaseMessagingService;
import com.uniquindio.moviuq.provider.services.firebase.FirebaseAuthService;
import com.uniquindio.moviuq.provider.services.firebase.FirebaseCFDBService;
import com.uniquindio.moviuq.use_case.Case_Log;
import com.uniquindio.moviuq.use_case.Case_Sign;

public class UserImpl  implements UserService {

    private Case_Sign case_sign;
    private Case_Log case_log;



    @Override
    public void getUser() {


            FirebaseUser usersesion = FirebaseAuthService.getAuth().getCurrentUser();
            FirebaseCFDBService.getBD().collection("user").document(usersesion.getEmail()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                    if(documentSnapshot.exists()){

                        User user = documentSnapshot.toObject(User.class);
                        updateToken(user);
                        DataLocal.setUser(user);

                    }
                }
            });



    }

    private void updateToken(User user) {

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();
                        DocumentReference userUpdate = FirebaseCFDBService.getBD().collection("users").document(user.getMail());
                        userUpdate.update("token", token);
                        // Log and toast

                        user.setToken(token);
                        DataLocal.setUser(user);
                        DataLocal.setToken(token);
                        Log.d("updatetokenhomeactivi",token);
                        MyFirebaseMessagingService mfms=new MyFirebaseMessagingService();
                        mfms.onNewToken(token);
                        updateTokenGlobal(token, user);

                    }
                });
    }



    private void updateTokenGlobal(String token, User user) {

        FirebaseCFDBService.getBD().collection("offers").whereEqualTo("idUser", user.getMail()).
                get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            /** recolectar lista de ids*/
                            for (QueryDocumentSnapshot query : task.getResult()) {

                                DocumentReference offerUpdate = FirebaseCFDBService.getBD().collection("offers").document((String) query.get("id"));
                                offerUpdate.update("token", token);

                            }
                        }


                    }

                });


    }

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

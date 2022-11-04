package com.uniquindio.moviuq.data;


import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;
import com.uniquindio.moviuq.domain.User;
import com.uniquindio.moviuq.presentation.activity.MainActivity;
import com.uniquindio.moviuq.provider.data_local.DataLocal;
import com.uniquindio.moviuq.provider.services.firebase.FirebaseAuthService;
import com.uniquindio.moviuq.provider.services.firebase.FirebaseCFDBService;
import com.uniquindio.moviuq.use_case.Case_Profile;
import com.uniquindio.moviuq.use_case.Case_User;
import com.google.firebase.firestore.Query;

public class ProfileImpl implements ProfileService{


    private Case_User case_user;
    private FirebaseUser userSession;
    private Case_Profile case_createProfile;


    /** Metodo CrearPerfil
     *
     *  Metodo que recibe toda la informacion obtenida respecto a la creacion del perfil del usuario mediante capas anteriores
     *  los cuales, se manejan dentro de la capa de datos para su respectivo objetivo "Crear perfil"
     *
     **/


    @Override
    public void crearPerfil(String photo, String name, String last_name, int years, long phoneNumber, String city, Activity activity) {

        //Se crea el caso de uso para tener acceso al metodo de lanzarHome (Entrar a la siguiente ventana de la app)
        case_createProfile= new Case_Profile(activity);

        //Se obtiene al usuario en sesion activa
        userSession= FirebaseAuth.getInstance().getCurrentUser();
        String email= userSession.getEmail();
        User user= new User(name, last_name, photo, email, phoneNumber, years, city,"");

        /**Se obtiene la coleccion del usuario con dicho email para setearle los datos ingresados en la creacion de perfil,
          ademas se utiliza un listener para comprobar si hubo algun error durante el proceso de creacion de perfil
         */
        FirebaseFirestore.getInstance().collection("user").document(email).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                obtenerToken(email);
                case_createProfile.lanzarHome();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(activity, "Hubo un error inesperado, vuelva a intentarlo", Toast.LENGTH_SHORT).show();

            }
        });

    }

    /** Metodo de la documentación de google
     * para obtener un token unico para cada usuario**/
    private void obtenerToken(String email) {

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {

                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();
                        DocumentReference userUpdate = FirebaseFirestore.getInstance().collection("user").document(email);
                        userUpdate.update("token", token);
                        // Log and toast


                        //Toast.makeText(HomeActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });
    }










    /** Metodo logOutFromProfile
     *
     *  Metodo que solamente cierra la sesion del usuario activo y que posteriormente le redirige al la pantalla principal de
     *  la aplicacion
     *
     **/
    @Override
    public void logOutFromProfile(Activity activity) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    /** Metodo updateInformation
     *
     *  Metodo para actualizar la informacion del perfil, este obtiene toda la informacion necesaria, verificando campos
     *  vacios que si en tal caso hay 1 se advierte al usuario, y en caso contrario se obtiene el documento de dicho
     *  usuario referenciado con el email a la base de datos y que con update se actualiza cada campo con la informacion
     *  ya obtenida.
     *
     **/
    @Override
    public void updateInformation(String name, String lastName, String numberPhone, String city, String years, Activity activity) {

        if(name.isEmpty()||lastName.isEmpty()||numberPhone.isEmpty()||city.isEmpty()||years.isEmpty()){
            Toast.makeText(activity, "No puedes actualizar con campos vacios", Toast.LENGTH_SHORT).show();
        } else{
            userSession= FirebaseAuth.getInstance().getCurrentUser();
            String email= DataLocal.getUser().getMail();
            DocumentReference userUpdate = FirebaseFirestore.getInstance().collection("user").document(email);
            userUpdate.update("name", name);
            userUpdate.update("last_name", lastName);
            userUpdate.update("phoneNumber", Long.parseLong(numberPhone));
            userUpdate.update("city", Integer.parseInt(city));
            userUpdate.update("years", Integer.parseInt(years));
            //obtenerToken(email);
        }


    }

}

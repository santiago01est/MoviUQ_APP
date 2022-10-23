package com.uniquindio.moviuq.data;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.uniquindio.moviuq.domain.User;
import com.uniquindio.moviuq.provider.services.firebase.FirebaseAuthService;
import com.uniquindio.moviuq.provider.services.firebase.FirebaseCFDBService;
import com.uniquindio.moviuq.use_case.Case_Profile;
import com.uniquindio.moviuq.use_case.Case_User;
import com.google.firebase.firestore.Query;

public class ProfileImpl implements ProfileService{

    private Case_Profile case_createProfile;
    private Case_User case_user;
    private User user;
    private FirebaseUser userSession;


    /** Metodo CrearPerfil
     *
     *  Metodo que recibe toda la informacion obtenida respecto a la creacion del perfil del usuario mediante capas anteriores
     *  los cuales, se manejan dentro de la capa de datos para su respectivo objetivo "Crear perfil"
     *
     **/


    @Override
    public void crearPerfil(String photo, String name, String last_name, int years, long phoneNumber, int city, Activity activity) {

        //Se crea el caso de uso para tener acceso al metodo de lanzarHome (Entrar a la siguiente ventana de la app)
        case_createProfile= new Case_Profile(activity);

        //Se obtiene al usuario en sesion activa
        userSession= FirebaseAuthService.getAuth().getCurrentUser();
        String email= userSession.getEmail();
        User user= new User(name, last_name, photo, email, phoneNumber, years, city, null, null);

        /*Se obtiene la coleccion del usuario con dicho email para setearle los datos ingresados en la creacion de perfil,
          ademas se utiliza un listener para comprobar si hubo algun error durante el proceso de creacion de perfil
         */
        FirebaseCFDBService.getBD().collection("user").document(email).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                case_createProfile.lanzarHome();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(activity, "Hubo un error inesperado, vuelva a intentarlo", Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public void cargarDatosPerfil(TextView txv_nameProfileUser, Activity activity) {
        userSession= FirebaseAuthService.getAuth().getCurrentUser();
        String email= userSession.getEmail();

        DocumentReference documentReference = FirebaseCFDBService.getBD().collection("user").document(email);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                user = documentSnapshot.toObject(User.class);
                txv_nameProfileUser.setText(user.getName());
            }
        });

    }
}

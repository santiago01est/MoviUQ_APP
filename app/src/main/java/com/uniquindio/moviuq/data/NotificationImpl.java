package com.uniquindio.moviuq.data;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.uniquindio.moviuq.domain.Notification;

public class NotificationImpl implements NotificationService {

    @Override
    public Query getNotificationUser(String emailUser) {

        Query query;
        query = FirebaseFirestore.getInstance().collection("notification").whereEqualTo("emailUserReceiver", emailUser);

        return query;

    }

    @Override
    public void enviarNotificacion(Notification notification, Activity activity) {
        FirebaseUser userSession = FirebaseAuth.getInstance().getCurrentUser();
        String email= userSession.getEmail();

        FirebaseFirestore.getInstance().collection("notifications").document(notification.getId()).set(notification).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

               // case_createProfile.lanzarHome();
                Toast.makeText(activity, "Se ha eviado tu solicitud", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(activity, "Hubo un error inesperado, vuelva a intentarlo", Toast.LENGTH_SHORT).show();

            }
        });
    }


}

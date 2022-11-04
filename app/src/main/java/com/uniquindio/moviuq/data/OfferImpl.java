package com.uniquindio.moviuq.data;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.uniquindio.moviuq.domain.Offer;
import com.uniquindio.moviuq.provider.services.firebase.FirebaseCFDBService;
import com.uniquindio.moviuq.use_case.Case_Offer;

public class OfferImpl implements OfferService{
    Case_Offer case_offer;

    @Override
    public void createOffer(Offer offer, String email, Activity activity) {

        case_offer=new Case_Offer(activity);

        FirebaseFirestore.getInstance().collection("offers").document(offer.getId()).set(offer).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
               case_offer.lanzarHome();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(activity, "Hubo un error inesperado, vuelva a intentarlo", Toast.LENGTH_SHORT).show();

            }
        });

    }
}

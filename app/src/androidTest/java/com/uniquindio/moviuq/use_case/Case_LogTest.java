package com.uniquindio.moviuq.use_case;

import static org.junit.Assert.*;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.uniquindio.moviuq.domain.User;

import org.junit.Test;
import org.mockito.Mockito;


public class Case_LogTest {

    @Test
    public void someTest() {

        FirebaseFirestore.getInstance().collection("user").document("nikorodriguezutles@gmail.com").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if (documentSnapshot.exists()) {

                    User user = documentSnapshot.toObject(User.class);
                    assertNotNull(user);

                }
            }
        });

    }
}
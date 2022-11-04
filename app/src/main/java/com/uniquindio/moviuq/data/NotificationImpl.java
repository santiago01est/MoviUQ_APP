package com.uniquindio.moviuq.data;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.uniquindio.moviuq.provider.services.firebase.FirebaseCFDBService;

public class NotificationImpl implements NotificationService{

    @Override
    public Query getNotificationUser(String emailUser) {

        Query query;
        query= FirebaseFirestore.getInstance().collection("notification").whereEqualTo("emailUserReceiver",emailUser);

        return query;

    }
}

package com.uniquindio.moviuq.data;


import com.google.firebase.firestore.Query;
import com.uniquindio.moviuq.provider.services.firebase.FirebaseCFDBService;

public class NotificationImpl implements NotificationService{

    @Override
    public Query getNotificationUser(String emailUser) {
        return FirebaseCFDBService.getBD().collection("notification").whereEqualTo("emailUserReceiver",emailUser);

    }
}

package com.uniquindio.moviuq.data;

import android.app.Activity;

import com.google.firebase.firestore.Query;
import com.uniquindio.moviuq.domain.Notification;

public interface NotificationService {

    Query getNotificationUser(String emailUser);
    void enviarNotificacion(Notification notification, Activity activity);
}

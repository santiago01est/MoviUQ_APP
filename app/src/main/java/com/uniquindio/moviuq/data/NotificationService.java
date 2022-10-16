package com.uniquindio.moviuq.data;

import com.google.firebase.firestore.Query;

public interface NotificationService {

    Query getNotificationUser(String emailUser);
}

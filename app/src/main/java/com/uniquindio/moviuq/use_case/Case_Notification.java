package com.uniquindio.moviuq.use_case;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.google.firebase.firestore.Query;
import com.uniquindio.moviuq.data.NotificationService;
import com.uniquindio.moviuq.presentation.activity.NotificationUserActivity;

public class Case_Notification {

    private Activity activity;
    private NotificationService notificationService;

    public Case_Notification(Activity activity) {
        this.activity = activity;
    }

    public Query getNotificationUser(String emailUser){
        return notificationService.getNotificationUser(emailUser);
    }
    public void lanzarNotification(){
        Intent i = new Intent(activity, NotificationUserActivity.class);
        activity.startActivity(i);
        activity.finish();
    }
}

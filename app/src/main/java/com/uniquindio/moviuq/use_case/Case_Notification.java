package com.uniquindio.moviuq.use_case;

import static com.android.volley.VolleyLog.TAG;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.firebase.firestore.Query;
import com.uniquindio.moviuq.data.NotificationImpl;
import com.uniquindio.moviuq.data.NotificationService;
import com.uniquindio.moviuq.presentation.activity.NotificationUserActivity;

public class Case_Notification {

    private Activity activity;
    private NotificationService notificationService=new NotificationImpl();

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

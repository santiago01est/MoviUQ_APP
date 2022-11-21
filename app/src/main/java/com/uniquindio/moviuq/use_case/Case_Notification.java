package com.uniquindio.moviuq.use_case;

import static com.android.volley.VolleyLog.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.firebase.firestore.Query;
import com.uniquindio.moviuq.data.NotificationImpl;
import com.uniquindio.moviuq.data.NotificationService;
import com.uniquindio.moviuq.domain.Notification;
import com.uniquindio.moviuq.domain.TypeNotification;
import com.uniquindio.moviuq.presentation.activity.NotificationUserActivity;
import com.uniquindio.moviuq.provider.data_local.DataLocal;
import com.uniquindio.moviuq.provider.services.date.DateCalculator;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
       // activity.finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void enviarNotificacionContratacion(String token, String nameUserMe, String IduserTravel,String nameUserTravel) {


        /** Enviar notificacion al otro usuario**/
        RequestQueue myrequest= Volley.newRequestQueue(activity);
        JSONObject json = new JSONObject();

        try {

            String url_foto="";

            json.put("to",token);
            JSONObject notificacion=new JSONObject();
            notificacion.put("titulo", "Contratación");
            notificacion.put("detalle",nameUserMe+" Quiere contratar tu servicio de transporte");
            notificacion.put("foto",url_foto);

            json.put("data",notificacion);
            String URL="https://fcm.googleapis.com/fcm/send";
            JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST,URL,json,null,null){
                @Override
                public Map<String, String> getHeaders() {
                    Map<String,String>header=new HashMap<>();
                    header.put("content-type","application/json");
                    header.put("authorization","key=AAAAp1QX8SY:APA91bEaBh3YUYLtyPjwABa37KFomH2_nMNM6ny3PuJkVBfhqxdrp1bsCg4HZlB7SS3kMTu1jDA_st_k2R9F41h_XVTe9Xy0EcUIeso3gHyiL9szZkb652quMbWhQkjTw0GvtblZONw-");
                    return header;

                }
            };
            myrequest.add(request);


        }catch (JSONException e){
            e.printStackTrace();
        }

        /** Crear notificacion **/

        Date date = new Date();
        DateCalculator dateCalculator = new DateCalculator(date);
        String id="NOTI"+nameUserMe+nameUserTravel;
        String dateNoti=dateCalculator.getCompleteDay();
        String descripcion="Hola! "+nameUserTravel+" , el usuario "+nameUserMe+" está interesado en tu oferta de viaje. \nConfirma el viaje!";
        TypeNotification typeNotification= TypeNotification.ACUERDO_VIAJE;
        Notification notification= new Notification(id,dateNoti,descripcion,DataLocal.getUser().getPhoto(),typeNotification,IduserTravel,DataLocal.getToken(),nameUserMe,DataLocal.getUser().getMail());

        notificationService.enviarNotificacion(notification,activity);

    }

}

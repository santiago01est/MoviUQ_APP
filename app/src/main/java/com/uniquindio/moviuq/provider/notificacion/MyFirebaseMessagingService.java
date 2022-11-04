package com.uniquindio.moviuq.provider.notificacion;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.presentation.activity.MainActivity;

import java.util.Random;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.e("token","mi token es:"+s);

    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String from =remoteMessage.getFrom();

        if (remoteMessage.getData().size()>0){
            String titulo=remoteMessage.getData().get("titulo");

            String detalle=remoteMessage.getData().get("detalle");
            String foto=remoteMessage.getData().get("foto");

            mayorqueoreo(titulo,detalle,foto);

        }
    }

    private void mayorqueoreo(String titulo, String detalle, String foto) {
        String id="mensaje";
        NotificationManager nm=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,id);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel nc=new NotificationChannel(id,"nuevo", NotificationManager.IMPORTANCE_HIGH);
            nc.setShowBadge(true);
            assert nm!=null;
            nm.createNotificationChannel(nc);
        }

        // Bitmap imf_foto= Picasso.get().load(foto).get();
        builder.setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setContentTitle(titulo)
                .setSmallIcon(R.drawable.people_in_car)

                .setContentText(detalle)
               // .setContentIntent(clicknoti())
                .setAutoCancel(true)
                .setContentInfo("nuevo");


        // Create an Intent for the activity you want to start



        Random random=new Random();
        int idNotity =random.nextInt(8000);

        assert nm !=null;
        nm.notify(idNotity,builder.build());

    }
    public PendingIntent clicknoti(){
        Intent nf=new Intent(getApplicationContext(), MainActivity.class);
      //  nf.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return PendingIntent.getActivity(this,0,nf,0);
    }
}

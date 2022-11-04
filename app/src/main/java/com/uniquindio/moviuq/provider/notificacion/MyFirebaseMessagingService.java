package com.uniquindio.moviuq.provider.notificacion;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.uniquindio.moviuq.R;

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


        if (remoteMessage.getData().size()>0){
            String titulo=remoteMessage.getData().get("titulo");

            String detalle=remoteMessage.getData().get("detalle");


            mayorqueoreo(titulo,detalle);

        }
    }

    private void mayorqueoreo(String titulo, String detalle) {
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

}

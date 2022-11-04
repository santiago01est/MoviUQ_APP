package com.uniquindio.moviuq.presentation.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.domain.Notification;
import com.uniquindio.moviuq.provider.services.firebase.FirebaseCFDBService;
import com.uniquindio.moviuq.use_case.Adapters.AdapterFireNotification;
import com.uniquindio.moviuq.use_case.Case_Notification;
import com.uniquindio.moviuq.use_case.Case_User;

public class NotificationUserActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String emailUser;
    private Case_User case_user;
    private Case_Notification case_notification;
    private AdapterFireNotification adapterFireNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_user);

        /** USE CASE **/
        case_user= new Case_User(this);
        case_notification= new Case_Notification(this);

        emailUser= case_user.getEmailUser();

        /** REFERENCE ELEMETS**/
        recyclerView= findViewById(R.id.recycler_notifications);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.getItemAnimator().setChangeDuration(0);

        /** Consulta para fijar Adaptador**/
        //Query query=case_notification.getNotificationUser(emailUser);
        Query query= FirebaseFirestore.getInstance().collection("notification").whereEqualTo("emailUserReceiver",emailUser);
        FirestoreRecyclerOptions<Notification> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Notification>().setQuery(query, Notification.class).build();
        adapterFireNotification = new AdapterFireNotification(firestoreRecyclerOptions,this);
        recyclerView.setAdapter(adapterFireNotification);
      //  adapterFireNotification.notifyDataSetChanged();
    }

    /** onStart
     *
     *  Este metodo se encarga de limpiar el reciclerView donde estan contenidos los card request
     *  ademas de empezar a cargar cada request existente en la lista del recyclerView
     *
     **/
    @Override
    public void onStart(){
        super.onStart();
        recyclerView.getRecycledViewPool().clear();
        //adapterFireNotification.notifyDataSetChanged();
        adapterFireNotification.startListening();
    }

    @Override
    public void onStop(){
        super.onStop();
        adapterFireNotification.stopListening();
    }
}
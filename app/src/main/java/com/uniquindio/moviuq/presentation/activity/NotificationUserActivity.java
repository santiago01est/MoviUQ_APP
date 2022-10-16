package com.uniquindio.moviuq.presentation.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
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

        case_user= new Case_User(this);
        case_notification= new Case_Notification(this);
        emailUser= case_user.getEmailUser();
        recyclerView= findViewById(R.id.recycler_notifications);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.getItemAnimator().setChangeDuration(0);

        //Query query=case_notification.getNotificationUser(emailUser);
        Query query= FirebaseCFDBService.getBD().collection("notification").whereEqualTo("emailUserReceiver",emailUser);
        FirestoreRecyclerOptions<Notification> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Notification>().setQuery(query, Notification.class).build();
        adapterFireNotification = new AdapterFireNotification(firestoreRecyclerOptions,this);
        recyclerView.setAdapter(adapterFireNotification);
        adapterFireNotification.notifyDataSetChanged();
    }
    @Override
    public void onStart(){
        super.onStart();
        recyclerView.getRecycledViewPool().clear();
        adapterFireNotification.notifyDataSetChanged();
        adapterFireNotification.startListening();
    }

    @Override
    public void onStop(){
        super.onStop();
        adapterFireNotification.stopListening();
    }
}
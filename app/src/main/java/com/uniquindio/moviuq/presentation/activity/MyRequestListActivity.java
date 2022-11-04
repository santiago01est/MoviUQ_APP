package com.uniquindio.moviuq.presentation.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.domain.Request;
import com.uniquindio.moviuq.provider.services.firebase.FirebaseCFDBService;
import com.uniquindio.moviuq.use_case.Adapters.AdapterFireMyRequestList;
import com.uniquindio.moviuq.use_case.Case_Request;
import com.uniquindio.moviuq.use_case.Case_User;

public class MyRequestListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Case_Request case_request;
    private ImageButton back;
    private Case_User case_user;
    private AdapterFireMyRequestList adapterFireMyRequestList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_request_list);

        case_request = new Case_Request(this);
        case_user = new Case_User(this);
        recyclerView = findViewById(R.id.recycler_requests);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.getItemAnimator().setChangeDuration(0);

        /** Consulta para fijar Adaptador**/
        Query query = FirebaseFirestore.getInstance().collection("request").whereEqualTo("idUser", case_user.getEmailUser());
        FirestoreRecyclerOptions<Request> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Request>().setQuery(query, Request.class).build();
        adapterFireMyRequestList = new AdapterFireMyRequestList(firestoreRecyclerOptions, this);
        recyclerView.setAdapter(adapterFireMyRequestList);
       // adapterFireMyRequestList.notifyDataSetChanged();
        back = findViewById(R.id.imageButton_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        recyclerView.getRecycledViewPool().clear();
       // adapterFireMyRequestList.notifyDataSetChanged();
        adapterFireMyRequestList.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterFireMyRequestList.stopListening();
    }
}
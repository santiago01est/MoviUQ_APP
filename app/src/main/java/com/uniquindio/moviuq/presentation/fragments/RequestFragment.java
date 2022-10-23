package com.uniquindio.moviuq.presentation.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.domain.Notification;
import com.uniquindio.moviuq.domain.Request;
import com.uniquindio.moviuq.provider.services.firebase.FirebaseCFDBService;
import com.uniquindio.moviuq.use_case.Adapters.AdapterFireNotification;
import com.uniquindio.moviuq.use_case.Adapters.AdapterFireRequest;
import com.uniquindio.moviuq.use_case.Case_Request;


public class RequestFragment extends Fragment{

    private View view;
    private RecyclerView recyclerView;
    private Case_Request case_request;
    private AdapterFireRequest adapterFireRequest;

    public RequestFragment() {
        // Required empty public constructor
    }

    public static RequestFragment newInstance() {
        RequestFragment fragment = new RequestFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        case_request= new Case_Request(getActivity());
        view = inflater.inflate(R.layout.fragment_request, container, false);
        recyclerView = view.findViewById(R.id.recycler_requests);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.getItemAnimator().setChangeDuration(0);

        Query query= FirebaseCFDBService.getBD().collection("request");
        FirestoreRecyclerOptions<Request> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Request>().setQuery(query, Request.class).build();
        adapterFireRequest = new AdapterFireRequest(firestoreRecyclerOptions,getContext());
        recyclerView.setAdapter(adapterFireRequest);
        adapterFireRequest.notifyDataSetChanged();
        return view;

    }

    @Override
    public void onStart(){
        super.onStart();
        recyclerView.getRecycledViewPool().clear();
        adapterFireRequest.notifyDataSetChanged();
        adapterFireRequest.startListening();
    }

    @Override
    public void onStop(){
        super.onStop();
        adapterFireRequest.stopListening();
    }
}
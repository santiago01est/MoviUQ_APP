package com.uniquindio.moviuq.presentation.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Query;
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.domain.Request;
import com.uniquindio.moviuq.provider.services.firebase.FirebaseCFDBService;
import com.uniquindio.moviuq.use_case.Adapters.AdapterFireMyRequestList;
import com.uniquindio.moviuq.use_case.Adapters.AdapterFireRequest;
import com.uniquindio.moviuq.use_case.Case_Request;
import com.uniquindio.moviuq.use_case.Case_User;


public class MyRequestListFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;

    private Case_Request case_request;
    private Case_User case_user;
    private AdapterFireMyRequestList adapterFireMyRequestList;

    public MyRequestListFragment() {
        // Required empty public constructor
    }


    public static MyRequestListFragment newInstance(String param1, String param2) {
        MyRequestListFragment fragment = new MyRequestListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_my_request_list, container, false);
        case_request= new Case_Request(getActivity());
        case_user= new Case_User(getActivity());
        recyclerView = view.findViewById(R.id.recycler_requests);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.getItemAnimator().setChangeDuration(0);

        /** Consulta para fijar Adaptador**/
        Query query= FirebaseCFDBService.getBD().collection("request").whereEqualTo("idUser", case_user.getEmailUser());
        FirestoreRecyclerOptions<Request> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Request>().setQuery(query, Request.class).build();
        adapterFireMyRequestList = new AdapterFireMyRequestList(firestoreRecyclerOptions,getContext());
        recyclerView.setAdapter(adapterFireMyRequestList);
        adapterFireMyRequestList.notifyDataSetChanged();




        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        recyclerView.getRecycledViewPool().clear();
        //adapterFireMyRequestList.notifyDataSetChanged();
        adapterFireMyRequestList.startListening();
    }

    @Override
    public void onStop(){
        super.onStop();
        adapterFireMyRequestList.stopListening();
    }
}
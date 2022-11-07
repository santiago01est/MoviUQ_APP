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
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.domain.Notification;
import com.uniquindio.moviuq.domain.Request;
import com.uniquindio.moviuq.provider.services.firebase.FirebaseCFDBService;
import com.uniquindio.moviuq.use_case.Adapters.AdapterFireNotification;
import com.uniquindio.moviuq.use_case.Adapters.AdapterFireRequest;
import com.uniquindio.moviuq.use_case.Case_Request;


public class RequestFragment extends Fragment{

    /** Elementos UI**/
    private View view;
    private RecyclerView recyclerView;
    private CollapsingToolbarLayout collap;
    private FloatingActionButton create;

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
        /** USE CASE **/
        case_request= new Case_Request(getActivity());

        /** REFERENCE ELEMETS**/
        view = inflater.inflate(R.layout.fragment_request, container, false);
        collap=view.findViewById(R.id.collapsing_request);
        create=view.findViewById(R.id.floating_add_request);
        recyclerView = view.findViewById(R.id.recycler_requests);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.getItemAnimator().setChangeDuration(0);

        /** Consulta para fijar Adaptador**/
        Query query= FirebaseFirestore.getInstance().collection("request");
        FirestoreRecyclerOptions<Request> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Request>().setQuery(query, Request.class).build();
        adapterFireRequest = new AdapterFireRequest(firestoreRecyclerOptions,getContext());
        recyclerView.setAdapter(adapterFireRequest);
        adapterFireRequest.notifyDataSetChanged();

        /** Mecanismo collapsing para fijar nombre en la toolbar**/

        AppBarLayout appBarLayout = view.findViewById(R.id.appbar_request);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collap.setTitle("Solicitudes");
                    collap.setCollapsedTitleTextAppearance(R.style.ExpandedAppBar);
                    isShow = true;
                } else if (isShow) {
                    collap.setTitle(" ");

                    isShow = false;
                }
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                case_request.lanzarCreateRequest();
            }
        });
        return view;

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
        adapterFireRequest.notifyDataSetChanged();
        adapterFireRequest.startListening();
    }

    @Override
    public void onStop(){
        super.onStop();
        adapterFireRequest.stopListening();
    }
}
package com.uniquindio.moviuq.presentation.fragments.tab;

import android.os.Bundle;

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
import com.google.firebase.firestore.Query;
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.domain.Offer;
import com.uniquindio.moviuq.provider.services.firebase.FirebaseCFDBService;
import com.uniquindio.moviuq.use_case.Adapters.AdapterFireOffer;
import com.uniquindio.moviuq.use_case.Case_Offer;
import com.uniquindio.moviuq.use_case.Case_User;

public class TabMyTravelsListFragment extends Fragment {

    private CollapsingToolbarLayout collap;
    private AppBarLayout appBarLayout;
    private RecyclerView recyclerView;
    private AdapterFireOffer adapterFireOffer;
    private FloatingActionButton addOffer;
    private Case_Offer case_offer;
    private Case_User case_user;

    public TabMyTravelsListFragment() {
        // Required empty public constructor
    }

    public static TabMyTravelsListFragment newInstance() {
        TabMyTravelsListFragment fragment = new TabMyTravelsListFragment();
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
        View root=inflater.inflate(R.layout.fragment_offer_travel, container, false);
        case_offer=new Case_Offer(getActivity());
        /** References UI**/
        collap=root.findViewById(R.id.collapsingOffer);
        recyclerView = root.findViewById(R.id.recycler_offer_travel);
        addOffer= root.findViewById(R.id.floating_add_offer_travel);

        /** Configuración del recycler**/
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.getItemAnimator().setChangeDuration(0);

        /** Consulta para fijar Adaptador**/
        Query query= FirebaseCFDBService.getBD().collection("offers");
        FirestoreRecyclerOptions<Offer> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Offer>().setQuery(query, Offer.class).build();
        adapterFireOffer = new AdapterFireOffer(firestoreRecyclerOptions,getContext());
        recyclerView.setAdapter(adapterFireOffer);
        adapterFireOffer.notifyDataSetChanged();
        return root;
    }

    @Override
    public void onStart(){
        super.onStart();
        recyclerView.getRecycledViewPool().clear();
        adapterFireOffer.notifyDataSetChanged();
        adapterFireOffer.startListening();
    }

    @Override
    public void onStop(){
        super.onStop();
        adapterFireOffer.stopListening();
    }
}
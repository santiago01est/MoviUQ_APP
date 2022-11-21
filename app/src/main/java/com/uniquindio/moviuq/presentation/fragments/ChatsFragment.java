package com.uniquindio.moviuq.presentation.fragments;

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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.domain.Chat;
import com.uniquindio.moviuq.domain.Offer;
import com.uniquindio.moviuq.provider.data_local.DataLocal;
import com.uniquindio.moviuq.use_case.Adapters.AdapterFireChats;
import com.uniquindio.moviuq.use_case.Adapters.AdapterFireOffer;
import com.uniquindio.moviuq.use_case.Case_Offer;

import java.util.Objects;

public class ChatsFragment extends Fragment {


    private CollapsingToolbarLayout collap;
    private RecyclerView recyclerView;
    private AdapterFireChats adapterFireChats;

    public ChatsFragment() {
        // Required empty public constructor
    }


    public static ChatsFragment newInstance() {
        ChatsFragment fragment = new ChatsFragment();
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
        View root = inflater.inflate(R.layout.fragment_chats, container, false);
        collap = root.findViewById(R.id.collapsingChats);
        recyclerView = root.findViewById(R.id.recycler_chats);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Objects.requireNonNull(recyclerView.getItemAnimator()).setChangeDuration(0);

        //Fijar recycler
        Query query = FirebaseFirestore.getInstance().collection("chats").whereEqualTo("idMe", DataLocal.getUser().getMail()).whereLessThanOrEqualTo("idYou", DataLocal.getUser().getMail());

        FirestoreRecyclerOptions<Chat> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Chat>().setQuery(query, Chat.class).build();
        adapterFireChats = new AdapterFireChats(firestoreRecyclerOptions, getContext());
        recyclerView.setAdapter(adapterFireChats);
        adapterFireChats.notifyDataSetChanged();


        /* Mecanismo collapsing para fijar nombre en la toolbar**/

        AppBarLayout appBarLayout=root.findViewById(R.id.appbarChats);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collap.setTitle("Chats");
                    collap.setCollapsedTitleTextAppearance(R.style.ExpandedAppBar);
                    isShow = true;
                } else if (isShow) {
                    collap.setTitle(" ");

                    isShow = false;
                }
            }
        });

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        recyclerView.getRecycledViewPool().clear();
        //noinspection NotifyDataSetChanged
        adapterFireChats.notifyDataSetChanged();
        adapterFireChats.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterFireChats.stopListening();
    }
}
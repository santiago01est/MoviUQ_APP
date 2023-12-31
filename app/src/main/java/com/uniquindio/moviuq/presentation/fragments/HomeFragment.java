package com.uniquindio.moviuq.presentation.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.domain.Offer;
import com.uniquindio.moviuq.domain.PlaceMain;
import com.uniquindio.moviuq.domain.User;
import com.uniquindio.moviuq.presentation.activity.HomeActivity;
import com.uniquindio.moviuq.provider.data_local.DataLocal;
import com.uniquindio.moviuq.use_case.Adapters.AdapterFireOffer;
import com.uniquindio.moviuq.use_case.Adapters.AdapterFirePlaces;
import com.uniquindio.moviuq.use_case.Adapters.Adapter_Search;
import com.uniquindio.moviuq.use_case.Case_Notification;
import com.uniquindio.moviuq.use_case.Case_Offer;
import com.uniquindio.moviuq.use_case.Case_User;

import java.util.ArrayList;
import java.util.Map;


public class HomeFragment extends Fragment {

    private ImageView imgv_photo_user,imgview_search;
    private TextView txv_nameUser,buscar_destino;

    private RecyclerView recyclerViewPlaces;
    private LinearLayoutManager linearLayoutManager;


    /**
     * Casos de uso
     **/
    private Case_Notification case_notification;
    private Case_User case_user;
    private Case_Offer case_offer;
    private ArrayList<Offer> myOffer = new ArrayList<>();
    AdapterFirePlaces adapterFirePlaces;
    static Context context;

    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance(Context context1) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        context=context1;
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
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        /** References **/
        imgv_photo_user = root.findViewById(R.id.imageView_photoUser);
        imgview_search = root.findViewById(R.id.imgview_search);
        txv_nameUser = root.findViewById(R.id.txv_name_user);
        buscar_destino = root.findViewById(R.id.buscar_destino);
        recyclerViewPlaces = root.findViewById(R.id.recycler__place);

        linearLayoutManager= new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPlaces.setLayoutManager(linearLayoutManager);
        Query query= FirebaseFirestore.getInstance().collection("places_main");
        FirestoreRecyclerOptions<PlaceMain> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<PlaceMain>().setQuery(query, PlaceMain.class).build();
        adapterFirePlaces = new AdapterFirePlaces(firestoreRecyclerOptions,getContext());
        adapterFirePlaces.notifyDataSetChanged();
        recyclerViewPlaces.setAdapter(adapterFirePlaces);


        case_notification = new Case_Notification(getActivity());
        case_user = new Case_User(getActivity());
        case_offer=new Case_Offer(getActivity());
        /**
         * Elementos UI
         **/
        ImageButton notification = root.findViewById(R.id.imgbttn_notification);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                case_notification.lanzarNotification();
            }
        });

        buscar_destino.setOnClickListener(v -> case_offer.lanzarSearchTravel());


        /**  Load UI*/
        loadData();


        return root;
    }



    private void loadData() {

        FirebaseUser usersesion = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore.getInstance().collection("user").document(usersesion.getEmail()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if (documentSnapshot.exists()) {

                    User user = documentSnapshot.toObject(User.class);
                    DataLocal.setUser(user);
                    txv_nameUser.setText(user.getName());
                    /** Mediante glide se busca la photo de perfil
                     * que esta subida en Cloud Store**/

                    Glide.with(context)
                            .load(user.getPhoto())
                            .into(imgv_photo_user);

                    case_user.updateToken();

                }
            }
        });

    }

    @Override
    public void onStart(){
        super.onStart();
        recyclerViewPlaces.getRecycledViewPool().clear();
        adapterFirePlaces.notifyDataSetChanged();
        adapterFirePlaces.startListening();
    }

    @Override
    public void onStop(){
        super.onStop();
        adapterFirePlaces.stopListening();
    }


}
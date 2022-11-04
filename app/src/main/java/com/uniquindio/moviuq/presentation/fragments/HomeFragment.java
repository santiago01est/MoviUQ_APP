package com.uniquindio.moviuq.presentation.fragments;

import android.os.Bundle;

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
import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.domain.Offer;
import com.uniquindio.moviuq.domain.User;
import com.uniquindio.moviuq.provider.data_local.DataLocal;
import com.uniquindio.moviuq.provider.services.firebase.FirebaseAuthService;
import com.uniquindio.moviuq.provider.services.firebase.FirebaseCFDBService;
import com.uniquindio.moviuq.use_case.Adapters.AdapterFireOffer;
import com.uniquindio.moviuq.use_case.Case_Notification;
import com.uniquindio.moviuq.use_case.Case_User;



public class HomeFragment extends Fragment {

    private ImageView imgv_photo_user;
    private TextView txv_nameUser;
    private RecyclerView search_offer;
    private SearchView searchView;

    /**
     * Casos de uso
     **/
    private Case_Notification case_notification;
    private Case_User case_user;
  //  private ArrayList<Offer> myOffer=new ArrayList<>();


    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
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
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        /** References **/
        imgv_photo_user = root.findViewById(R.id.imageView_photoUser);
        txv_nameUser = root.findViewById(R.id.txv_name_user);
        searchView= root.findViewById(R.id.search_travel);
        search_offer=root.findViewById(R.id.recycler_search_travel);
        search_offer.setLayoutManager(new LinearLayoutManager(getContext()));
        search_offer.getItemAnimator().setChangeDuration(0);


        case_notification = new Case_Notification(getActivity());
        case_user=new Case_User(getActivity());
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



        /**  Load UI*/
        loadData();

        /** Logica para la busquedad**/
        search_view();

        return root;
    }

    private void search_view() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                textSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                textSearch(s);
                return false;
            }
        });
    }

    private void textSearch(String s) {
        Query query= FirebaseFirestore.getInstance().collection("offers");
        FirestoreRecyclerOptions<Offer> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Offer>().setQuery(query, Offer.class).build();
        AdapterFireOffer adapterFireOffer = new AdapterFireOffer(firestoreRecyclerOptions, getContext());
        search_offer.setAdapter(adapterFireOffer);
       // adapterFireOffer.notifyDataSetChanged();

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
                    Glide.with(getActivity() )
                            .load(DataLocal.getUser().getPhoto())
                            .into(imgv_photo_user);

                    case_user.updateToken();

                }
            }
        });

    }




}
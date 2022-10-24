package com.uniquindio.moviuq.presentation.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.domain.User;
import com.uniquindio.moviuq.provider.data_local.DataLocal;
import com.uniquindio.moviuq.provider.services.firebase.FirebaseAuthService;
import com.uniquindio.moviuq.provider.services.firebase.FirebaseCFDBService;
import com.uniquindio.moviuq.use_case.Case_Notification;


public class HomeFragment extends Fragment {

    /**
     * Elementos UI
     **/
    private ImageButton notification;
    private ImageView imgv_photo_user;
    private TextView txv_nameUser;

    /**
     * Casos de uso
     **/
    private Case_Notification case_notification;

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

        /** Referencias **/
        imgv_photo_user = root.findViewById(R.id.imageView_photoUser);
        txv_nameUser = root.findViewById(R.id.txv_name_user);


        case_notification = new Case_Notification(getActivity());
        notification = root.findViewById(R.id.imgbttn_notification);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                case_notification.lanzarNotification();
            }
        });



        /**  Load UI*/
        loadData();

        return root;
    }

    private void loadData() {

        FirebaseUser usersesion = FirebaseAuthService.getAuth().getCurrentUser();
        FirebaseCFDBService.getBD().collection("user").document(usersesion.getEmail()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if (documentSnapshot.exists()) {

                    User user = documentSnapshot.toObject(User.class);
                    //updateToken(user);
                    DataLocal.setUser(user);
                    txv_nameUser.setText(user.getName());
                    /** Mediante glide se busca la photo de perfil
                     * que esta subida en Cloud Store**/
                    Glide.with(getActivity() )
                            .load(DataLocal.getUser().getPhoto())
                            .into(imgv_photo_user);

                }
            }
        });




    }


}
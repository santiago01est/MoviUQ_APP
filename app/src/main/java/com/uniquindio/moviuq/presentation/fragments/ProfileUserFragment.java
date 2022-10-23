package com.uniquindio.moviuq.presentation.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.domain.User;
import com.uniquindio.moviuq.provider.services.firebase.FirebaseCFDBService;
import com.uniquindio.moviuq.use_case.Case_Profile;
import com.uniquindio.moviuq.use_case.Case_User;


public class ProfileUserFragment extends Fragment {

    private View view;
    private TextView txv_nameProfileUser;
    private LinearLayout ly_signOff, ly_editProfile;
    private Case_Profile case_profile;
    private Case_User case_user;
    private User user;
    public ProfileUserFragment() {
        // Required empty public constructor
    }

    public static ProfileUserFragment newInstance() {
        ProfileUserFragment fragment = new ProfileUserFragment();
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
        view = inflater.inflate(R.layout.fragment_profile_user, container, false);

        case_profile= new Case_Profile(getActivity());
        case_user= new Case_User(getActivity());
        txv_nameProfileUser=view.findViewById(R.id.txv_nameProfileUser);

        DocumentReference docRef = FirebaseCFDBService.getBD().collection("user").document(case_user.getEmailUser());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                user = documentSnapshot.toObject(User.class);
                txv_nameProfileUser.setText(user.getName());
            }
        });
        ly_signOff=view.findViewById(R.id.ly_signOff);
        ly_signOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                case_profile.logOutFromProfile();
            }
        });

        ly_editProfile= view.findViewById(R.id.ly_editProfile);
        ly_editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                case_profile.lanzarEditProfileUser();
            }
        });

        return view;
    }
}
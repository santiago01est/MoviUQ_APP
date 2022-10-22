package com.uniquindio.moviuq.presentation.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.firestore.Query;
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.provider.services.firebase.FirebaseCFDBService;
import com.uniquindio.moviuq.use_case.Case_Profile;
import com.uniquindio.moviuq.use_case.Case_User;


public class    ProfileUserFragment extends Fragment {

    private View view;
    private TextView txv_nameProfileUser;
    private Case_Profile case_profile;
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
        txv_nameProfileUser=view.findViewById(R.id.txv_nameProfileUser);
        case_profile.loadData(txv_nameProfileUser);
        return view;
    }
}
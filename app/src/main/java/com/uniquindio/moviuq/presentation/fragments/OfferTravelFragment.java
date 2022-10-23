package com.uniquindio.moviuq.presentation.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uniquindio.moviuq.R;


public class OfferTravelFragment extends Fragment {



    public OfferTravelFragment() {
        // Required empty public constructor
    }


    public static OfferTravelFragment newInstance() {
        OfferTravelFragment fragment = new OfferTravelFragment();
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

        View root=inflater.inflate(R.layout.fragment_offer_travel, container, false);

        return root;
    }
}
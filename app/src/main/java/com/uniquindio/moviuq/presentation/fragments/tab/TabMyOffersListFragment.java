package com.uniquindio.moviuq.presentation.fragments.tab;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uniquindio.moviuq.R;

public class TabMyOffersListFragment extends Fragment {


    public TabMyOffersListFragment() {
        // Required empty public constructor
    }

    public static TabMyOffersListFragment newInstance() {
        TabMyOffersListFragment fragment = new TabMyOffersListFragment();
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
        return inflater.inflate(R.layout.fragment_tab_my_offers_list, container, false);
    }
}
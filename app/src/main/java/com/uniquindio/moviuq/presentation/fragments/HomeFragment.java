package com.uniquindio.moviuq.presentation.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.use_case.Case_Notification;


public class HomeFragment extends Fragment {

    private ImageButton notification;
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
        View root=inflater.inflate(R.layout.fragment_home, container, false);

        case_notification=new Case_Notification(getActivity());
        notification=root.findViewById(R.id.imgbttn_notification);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                case_notification.lanzarNotification();
            }
        });

        /** Mecanismo del estado del appBar**/
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) root.findViewById(R.id.collapsingHome);
        AppBarLayout appBarLayout = (AppBarLayout) root.findViewById(R.id.appbarHome);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle("Buenos dias, Santiago");
                    collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.ExpandedAppBar);
                    // toolbar.setBackgroundColor((Color.parseColor("#5F0F40")));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");

                    isShow = false;
                }
            }
        });

        return root;
    }


}
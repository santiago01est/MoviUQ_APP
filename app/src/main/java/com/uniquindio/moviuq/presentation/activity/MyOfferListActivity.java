package com.uniquindio.moviuq.presentation.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.presentation.fragments.tab.TabMyOffersListFragment;
import com.uniquindio.moviuq.presentation.fragments.tab.TabMyTravelsListFragment;
import com.uniquindio.moviuq.use_case.Adapters.Adapter_Fragment;
import com.uniquindio.moviuq.use_case.Case_Offer;
import com.uniquindio.moviuq.use_case.Case_User;

public class MyOfferListActivity extends AppCompatActivity {

    private CollapsingToolbarLayout collap;
    private AppBarLayout appBarLayout;
    private Case_Offer case_offer;
    private Case_User case_user;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_offer_list);

        case_offer=new Case_Offer(this);
        case_user=new Case_User(this);
        /** References UI**/
        collap=findViewById(R.id.collapsingMyOfferList);
        viewPager=findViewById(R.id.viewPage_MyOfferList_MyTravelList);
        tabLayout=findViewById(R.id.tab_info);

        Adapter_Fragment adapter_fragment = new Adapter_Fragment(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter_fragment.addFragment(new TabMyTravelsListFragment().newInstance(), "Mis viajes");
        adapter_fragment.addFragment(new TabMyOffersListFragment().newInstance(), "Mis ofertas");

        viewPager.setAdapter(adapter_fragment);

        /** Mecanismo collapsing para fijar nombre en la toolbar**/

        AppBarLayout appBarLayout = findViewById(R.id.appbarMyOfferList);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collap.setTitle("Viajes");
                    collap.setCollapsedTitleTextAppearance(R.style.ExpandedAppBar);
                    isShow = true;
                } else if (isShow) {
                    collap.setTitle(" ");

                    isShow = false;
                }
            }
        });
    }

}
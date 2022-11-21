package com.uniquindio.moviuq.presentation.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.domain.Offer;
import com.uniquindio.moviuq.use_case.Adapters.AdapterFireOffer;

public class SearchTravelActivity extends AppCompatActivity {

    private RecyclerView search_offer;
    private TextView estado_busqueda;
    private SearchView searchView;
    private AdapterFireOffer adapterFireOffer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_travel);

        searchView = findViewById(R.id.search_travel);
        search_offer = findViewById(R.id.recycler_search_travel);
        estado_busqueda = findViewById(R.id.estado_busqueda);

        search_offer.setLayoutManager(new LinearLayoutManager(this));
        Query query = FirebaseFirestore.getInstance().collection("offers");
        FirestoreRecyclerOptions<Offer> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Offer>().setQuery(query, Offer.class).build();
        adapterFireOffer = new AdapterFireOffer(firestoreRecyclerOptions, this);
        search_offer.setAdapter(adapterFireOffer);

        searchView.setQueryHint("Buscar...");
        searchView.setIconified(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                search_offer.setVisibility(View.VISIBLE);
                if (!s.isEmpty()){
                    estado_busqueda.setVisibility(View.GONE);
                }else{
                    estado_busqueda.setVisibility(View.VISIBLE);
                    search_offer.setVisibility(View.GONE);
                }
                estado_busqueda.setVisibility(View.GONE);
                search_offer.setLayoutManager(new LinearLayoutManager(SearchTravelActivity.this));
                Query query = FirebaseFirestore.getInstance().collection("offers");
                FirestoreRecyclerOptions<Offer> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Offer>().setQuery(query.orderBy("title").startAt(s).endAt("~"+s+"~"), Offer.class).build();
                adapterFireOffer = new AdapterFireOffer(firestoreRecyclerOptions, SearchTravelActivity.this);
                adapterFireOffer.startListening();
                search_offer.setAdapter(adapterFireOffer);
                return true;
            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();
        search_offer.getRecycledViewPool().clear();
        //noinspection NotifyDataSetChanged
        adapterFireOffer.notifyDataSetChanged();
        adapterFireOffer.startListening();
    }

    @Override
    public void onStop(){
        super.onStop();
        adapterFireOffer.stopListening();
    }
}
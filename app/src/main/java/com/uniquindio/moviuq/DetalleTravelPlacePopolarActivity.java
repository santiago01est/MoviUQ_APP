package com.uniquindio.moviuq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.uniquindio.moviuq.domain.Offer;
import com.uniquindio.moviuq.domain.PlaceMain;
import com.uniquindio.moviuq.presentation.activity.SearchTravelActivity;
import com.uniquindio.moviuq.use_case.Adapters.AdapterFireOffer;

public class DetalleTravelPlacePopolarActivity extends AppCompatActivity {

    private RecyclerView search_offer;
    private AdapterFireOffer adapterFireOffer;
    private PlaceMain placeMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_travel_place_popolar);
        Bundle objeto=getIntent().getExtras();
        placeMain=new PlaceMain();
        if(objeto!=null){
            placeMain=(PlaceMain) objeto.getSerializable("place");

        }

        search_offer = findViewById(R.id.recycler_popular_travel);


        search_offer.setLayoutManager(new LinearLayoutManager(this));
        Query query = FirebaseFirestore.getInstance().collection("offers");
        FirestoreRecyclerOptions<Offer> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Offer>().setQuery(query.orderBy("title").startAt(placeMain.getNombre()).endAt(placeMain.getNombre()+"~"), Offer.class).build();
        adapterFireOffer = new AdapterFireOffer(firestoreRecyclerOptions, this);
        adapterFireOffer.startListening();
        search_offer.setAdapter(adapterFireOffer);
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
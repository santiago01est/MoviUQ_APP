package com.uniquindio.moviuq.use_case.Adapters;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.uniquindio.moviuq.DetalleTravelPlacePopolarActivity;
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.domain.Offer;
import com.uniquindio.moviuq.domain.PlaceMain;
import com.uniquindio.moviuq.domain.VehicleType;
import com.uniquindio.moviuq.presentation.activity.DetailOfferTravelActivity;

public class AdapterFirePlaces extends FirestoreRecyclerAdapter<PlaceMain, AdapterFirePlaces.ViewHolder> {

    private Context context;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.
     */
    public AdapterFirePlaces(@NonNull FirestoreRecyclerOptions<PlaceMain> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull AdapterFirePlaces.ViewHolder holder, int position, @NonNull PlaceMain model) {


        holder.nombre.setText(model.getNombre());

        //fijar imagen del lugar

            Glide.with(context )
                    .load(model.getFoto())
                    .into(holder.photo);



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(holder.itemView.getContext(), DetalleTravelPlacePopolarActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("place", model);
                i.putExtras(bundle);
                holder.itemView.getContext().startActivity(i);
            }
        });


    }


    public static class ViewHolder extends RecyclerView.ViewHolder {


        public TextView nombre;
        public ImageView photo;



        public ViewHolder(View itemView) {
            super(itemView);


            nombre = itemView.findViewById(R.id.txv_title_place);
            photo = itemView.findViewById(R.id.imgv_place);



        }


    }

    @Override
    public AdapterFirePlaces.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_places_main, viewGroup, false);
        return new AdapterFirePlaces.ViewHolder(v);
    }
}

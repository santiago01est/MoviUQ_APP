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

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.uniquindio.moviuq.presentation.activity.DetailOfferTravelActivity;
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.domain.Offer;
import com.uniquindio.moviuq.domain.VehicleType;

public class AdapterFireOffer extends FirestoreRecyclerAdapter<Offer, AdapterFireOffer.ViewHolder> {

    private Context context;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.
     */
    public AdapterFireOffer(@NonNull FirestoreRecyclerOptions<Offer> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull AdapterFireOffer.ViewHolder holder, int position, @NonNull Offer model) {

        int num_aleatorio = (int) (Math.random() * 10);
        holder.title.setText(model.getTitle());
        holder.date.setText(model.getDateTravel());
        holder.hour.setText(model.getHourTravel());
        holder.seats.setText(String.valueOf(model.getSeats()));

        if (model.getVehicleType() == VehicleType.CARRO) {
            holder.vehicle.setImageResource(R.drawable.ic_car);
        }

        //Se fija la imagen que acompañe a la oferta
        int id;
        if (num_aleatorio == 0 || num_aleatorio == 10) {

            holder.imgv_place.setImageResource(R.drawable.img_place01);

        } else if (num_aleatorio == 1 || num_aleatorio == 9) {

            holder.imgv_place.setImageResource(R.drawable.img_place02);

        } else if (num_aleatorio == 2 || num_aleatorio == 8) {

            holder.imgv_place.setImageResource(R.drawable.img_place03);

        } else if (num_aleatorio == 3 || num_aleatorio == 7) {

            holder.imgv_place.setImageResource(R.drawable.img_place04);

        } else if (num_aleatorio == 4 || num_aleatorio == 5 || num_aleatorio == 6) {

            holder.imgv_place.setImageResource(R.drawable.img_place05);

        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(holder.itemView.getContext(), DetailOfferTravelActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("offer", model);
                i.putExtras(bundle);
                holder.itemView.getContext().startActivity(i);
            }
        });


    }


    public static class ViewHolder extends RecyclerView.ViewHolder {


        public TextView title;
        public TextView date;
        public TextView hour;
        public TextView seats;
        public ImageView vehicle;
        public ImageView imgv_place;


        public ViewHolder(View itemView) {
            super(itemView);


            title = itemView.findViewById(R.id.txv_title_offer);
            date = itemView.findViewById(R.id.txv_offer_date);
            hour = itemView.findViewById(R.id.txv_offer_hour);
            seats = itemView.findViewById(R.id.txv_offer_seats);
            vehicle = itemView.findViewById(R.id.img_vehicle);
            imgv_place = itemView.findViewById(R.id.imgv_place);


        }


    }

    @Override
    public AdapterFireOffer.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_offer_travel, viewGroup, false);
        return new AdapterFireOffer.ViewHolder(v);
    }
}

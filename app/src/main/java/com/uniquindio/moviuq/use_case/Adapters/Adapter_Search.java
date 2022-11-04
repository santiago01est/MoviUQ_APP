package com.uniquindio.moviuq.use_case.Adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.domain.Offer;
import com.uniquindio.moviuq.domain.VehicleType;
import com.uniquindio.moviuq.presentation.activity.DetailOfferTravelActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Adapter_Search extends RecyclerView.Adapter<Adapter_Search.ViewHolder>{

    private Context context;
    private ArrayList<Offer> listaOffer;
    protected View.OnClickListener onClickListener;



    /**
     *
     * @param listaOffer
     */
    public Adapter_Search(Context context, ArrayList<Offer> listaOffer){
        this.context=context;
        this.listaOffer=listaOffer;

    }

    public void setOnClickListener(View.OnClickListener onClickListener){
        this.onClickListener=onClickListener;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView date;
        public TextView hour;
        public TextView seats;
        public ImageView vehicle;


        public ViewHolder(View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.txv_title_offer);
            date=itemView.findViewById(R.id.txv_offer_date);
            hour=itemView.findViewById(R.id.txv_offer_hour);
            seats=itemView.findViewById(R.id.txv_offer_seats);
            vehicle=itemView.findViewById(R.id.img_vehicle);


        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_offer_travel,parent,false);
        v.setOnClickListener(onClickListener);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int posicion) {

        Offer model=listaOffer.get(posicion);



        holder.title.setText(model.getTitle());
        holder.date.setText(model.getDateTravel());
        holder.hour.setText(model.getHourTravel());
        holder.seats.setText(""+model.getSeats());

        if(model.getVehicleType()== VehicleType.CARRO){
            holder.vehicle.setImageResource(R.drawable.ic_car);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(holder.itemView.getContext(), DetailOfferTravelActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("offer", model);
                i.putExtras(bundle);
                holder.itemView.getContext().startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listaOffer.size();
    }
}

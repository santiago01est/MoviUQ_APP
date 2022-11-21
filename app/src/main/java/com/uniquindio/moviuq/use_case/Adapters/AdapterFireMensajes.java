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
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.domain.Chat;
import com.uniquindio.moviuq.domain.Mensaje;
import com.uniquindio.moviuq.presentation.activity.DetailOfferTravelActivity;

public class AdapterFireMensajes extends FirestoreRecyclerAdapter<Mensaje, AdapterFireMensajes.ViewHolder> {

    private Context context;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.
     */
    public AdapterFireMensajes(@NonNull FirestoreRecyclerOptions<Mensaje> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull AdapterFireMensajes.ViewHolder holder, int position, @NonNull Mensaje model) {


        holder.nombre.setText(model.getUsername());
        holder.mensaje.setText(model.getMensaje());
        holder.fecha.setText(model.getFecha());

        //fijar imagen del lugar

            Glide.with(context )
                    .load(model.getFoto())
                    .into(holder.photo);




    }


    public static class ViewHolder extends RecyclerView.ViewHolder {


        public TextView nombre;
        public TextView mensaje;
        public TextView fecha;
        public ImageView photo;



        public ViewHolder(View itemView) {
            super(itemView);


            nombre = itemView.findViewById(R.id.txv_username);
            photo = itemView.findViewById(R.id.imageView_user_chat_profile);
            mensaje = itemView.findViewById(R.id.txv_mensaje);
            fecha = itemView.findViewById(R.id.txv_date);



        }


    }

    @Override
    public AdapterFireMensajes.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_mensajes, viewGroup, false);
        return new AdapterFireMensajes.ViewHolder(v);
    }
}

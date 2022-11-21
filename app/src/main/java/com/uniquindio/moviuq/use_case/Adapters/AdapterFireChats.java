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
import com.uniquindio.moviuq.domain.PlaceMain;
import com.uniquindio.moviuq.presentation.activity.ChatActivity;
import com.uniquindio.moviuq.presentation.activity.DetailOfferTravelActivity;
import com.uniquindio.moviuq.provider.data_local.DataLocal;

public class AdapterFireChats extends FirestoreRecyclerAdapter<Chat, AdapterFireChats.ViewHolder> {

    private Context context;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.
     */
    public AdapterFireChats(@NonNull FirestoreRecyclerOptions<Chat> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull AdapterFireChats.ViewHolder holder, int position, @NonNull Chat model) {


        if(model.getUsernameMe().equals(DataLocal.getUser().getName())){
            holder.nombre.setText(model.getUsernameYou());
            Glide.with(context )
                    .load(model.getPerfilYou())
                    .into(holder.photo);
        }else{
            holder.nombre.setText(model.getUsernameMe());
            Glide.with(context )
                    .load(model.getPerfilMe())
                    .into(holder.photo);
        }

        holder.mensaje.setText(model.getUltimoMensaje());
        holder.fecha.setText(model.getFecha());

        //fijar imagen del lugar





        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(holder.itemView.getContext(), ChatActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("chat", model);
                i.putExtras(bundle);
                holder.itemView.getContext().startActivity(i);

            }
        });


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
    public AdapterFireChats.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_chats, viewGroup, false);
        return new AdapterFireChats.ViewHolder(v);
    }
}

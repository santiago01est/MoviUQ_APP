package com.uniquindio.moviuq.use_case.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.domain.Offer;
import com.uniquindio.moviuq.domain.Request;
import com.uniquindio.moviuq.domain.VehicleType;

public class AdapterFireOffer extends FirestoreRecyclerAdapter<Offer, AdapterFireOffer.ViewHolder> {

    private Context context;
    protected View.OnClickListener onClickListener;
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.
     */
    public AdapterFireOffer(@NonNull FirestoreRecyclerOptions<Offer> options, Context context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull AdapterFireOffer.ViewHolder holder, int position, @NonNull Offer model) {


        holder.title.setText(model.getTitle());
        holder.date.setText(model.getDateTravel());
        holder.hour.setText(model.getHourTravel());
        holder.seats.setText(""+model.getSeats());

        if(model.getVehicleType()== VehicleType.CARRO){
            holder.vehicle.setImageResource(R.drawable.ic_car);
        }


        //Glide.with(context)
        //      .load(model.getPerfil())
        //    .into(holder.avatar);



        /** Clic button three_Points */
        /*holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(model.getIslike()==true){

                    /* actualiza no like en review/
                    //model.setIslike(false);

                    /* MODIFICAR ID DOCUMENTO POR ID REVIEW/
                    DocumentReference commentsUpdate = FirebaseCFDBService.getBD().collection("comments_gallery").document(model.getId());
                    commentsUpdate.update("islike", false);
                    commentsUpdate.update("numLike", model.getNumLike()-1);
                    holder.like.setImageResource(R.drawable.ic_likecero);

                }else
                if (model.getIslike()==false){


                    //model.setIslike(true);
                    DocumentReference commentsUpdate = FirebaseCFDBService.getBD().collection("comments_gallery").document(model.getId());
                    commentsUpdate.update("islike", true);
                    commentsUpdate.update("numLike", model.getNumLike()+1);

                    holder.like.setImageResource(R.drawable.ic_likeone);



                }
            }
        });

*/




    }


    public void setOnClickListener(View.OnClickListener onClickListener){
        this.onClickListener=onClickListener;
    }

    public static  class ViewHolder extends RecyclerView.ViewHolder {


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

    @Override
    public AdapterFireOffer.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_offer_travel,viewGroup,false);
        //  v.setOnClickListener(onClickListener);
        return new AdapterFireOffer.ViewHolder(v);
    }
}

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
import com.uniquindio.moviuq.domain.Request;

public class AdapterFireMyRequestList extends FirestoreRecyclerAdapter<Request, AdapterFireMyRequestList.ViewHolder> {

    private Context context;
    protected View.OnClickListener onClickListener;
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.
     */
    public AdapterFireMyRequestList(@NonNull FirestoreRecyclerOptions<Request> options, Context context) {
        super(options);
        this.context=context;
    }

    /**
     * Se carga la infomacion consultada desde la base de datos a los campos enlazados del card
     */

    @Override
    protected void onBindViewHolder(@NonNull AdapterFireMyRequestList.ViewHolder holder, int position, @NonNull Request model) {


        holder.name.setText(model.getDescription());
        holder.startArriveP.setText(model.getTitle());
        holder.date.setText(model.getDateTravel());
        holder.hour.setText(model.getHourTravel());
        holder.seats.setText(""+model.getSeats());

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

    /**
     * Crea las variables del diseño del card para luego enlazarlos mediante el id respectivo en el xml
     */
    public static  class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView photoUser;
        public TextView name;
        public TextView startArriveP;
        public TextView date;
        public TextView hour;
        public TextView seats;
        public ImageButton three_point;
        public TextView status;



        public ViewHolder(View itemView) {
            super(itemView);

            photoUser=itemView.findViewById(R.id.imgv_mrl_user);
            name=itemView.findViewById(R.id.txv_mrl_name);
            startArriveP=itemView.findViewById(R.id.txv_mrl_DeA);
            date=itemView.findViewById(R.id.txv_mrl_date);
            hour=itemView.findViewById(R.id.txv_mrl_hour);
            seats=itemView.findViewById(R.id.txv_mrl_seats);
            three_point=itemView.findViewById(R.id.imgbttn_mrl_three_point);
            status=itemView.findViewById(R.id.txv_mrl_status);


        }


    }

    /**
     * Enlaza el adaptador con el card de my request list (list_my_request_list)
     */
    @Override
    public AdapterFireMyRequestList.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_my_request_list,viewGroup,false);
        //  v.setOnClickListener(onClickListener);
        return new AdapterFireMyRequestList.ViewHolder(v);
    }
}

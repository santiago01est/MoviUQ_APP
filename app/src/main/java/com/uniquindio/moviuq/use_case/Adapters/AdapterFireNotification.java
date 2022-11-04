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
import com.uniquindio.moviuq.domain.Notification;

public class AdapterFireNotification extends FirestoreRecyclerAdapter<Notification, AdapterFireNotification.ViewHolder> {

    private Context context;
    protected View.OnClickListener onClickListener;
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.
     */
    public AdapterFireNotification(@NonNull FirestoreRecyclerOptions<Notification> options, Context context) {
        super(options);
        this.context=context;
    }

    /**
     * Se carga la infomacion consultada desde la base de datos a los campos enlazados del card
     */

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Notification model) {

        holder.description.setText(model.getDescription());
        holder.date.setText(model.getDate());

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
        public TextView description;
        public TextView date;
        public ImageButton three_point;



        public ViewHolder(View itemView) {
            super(itemView);

            photoUser=itemView.findViewById(R.id.imageView_user_profile);
            description=itemView.findViewById(R.id.txv_description);
            date=itemView.findViewById(R.id.txv_date);
            three_point=itemView.findViewById(R.id.imgbttn_three_point);


        }


    }

    /**
     * Enlaza el adaptador con el card de notification (list_notification)
     */
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_notification,viewGroup,false);
        //  v.setOnClickListener(onClickListener);
        return new ViewHolder(v);
    }
}

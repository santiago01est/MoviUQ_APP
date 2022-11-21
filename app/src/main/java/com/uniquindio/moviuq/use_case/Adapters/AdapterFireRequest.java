package com.uniquindio.moviuq.use_case.Adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.uniquindio.moviuq.R;

import com.uniquindio.moviuq.domain.Chat;
import com.uniquindio.moviuq.domain.Request;
import com.uniquindio.moviuq.domain.User;
import com.uniquindio.moviuq.provider.data_local.DataLocal;
import com.uniquindio.moviuq.provider.services.date.DateCalculator;
import com.uniquindio.moviuq.use_case.Case_Chat;

import java.util.Date;

public class AdapterFireRequest extends FirestoreRecyclerAdapter<Request, AdapterFireRequest.ViewHolder> {

    private Context context;
    protected View.OnClickListener onClickListener;
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.
     */
    public AdapterFireRequest(@NonNull FirestoreRecyclerOptions<Request> options, Context context) {
        super(options);
        this.context=context;
    }

    /**
     * Se carga la infomacion consultada desde la base de datos a los campos enlazados del card
     */

    @Override
    protected void onBindViewHolder(@NonNull AdapterFireRequest.ViewHolder holder, int position, @NonNull Request model) {


        holder.name.setText(model.getNameUser());
        holder.startArriveP.setText(model.getTitle());
        holder.date.setText(model.getDateTravel());
        holder.hour.setText(model.getHourTravel());
        holder.seats.setText(String.valueOf(model.getSeats()));




        Glide.with(context )
                .load(model.getPhotoUser())
                .into(holder.photoUser);


        holder.three_point.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                User user=DataLocal.getUser();
                Date date = new Date();
                Case_Chat case_chat=new Case_Chat((Activity) context);
                DateCalculator dateCalculator = new DateCalculator(date);
                String dateFormat= dateCalculator.getCompleteDay();

                Chat chat=new Chat(model.getIdUser()+ user.getMail(), model.getNameUser(),user.getName(),user.getMail(), model.getIdUser(),model.getPhotoUser(),user.getPhoto(),dateFormat,"");
                case_chat.iniciarChat(user.getMail(),model.getIdUser(),chat);
            }
        });



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



        public ViewHolder(View itemView) {
            super(itemView);

            photoUser=itemView.findViewById(R.id.imageView_user_request_profile);
            name=itemView.findViewById(R.id.request_name);
            startArriveP=itemView.findViewById(R.id.txv_request_startArriveP);
            date=itemView.findViewById(R.id.txv_request_date);
            hour=itemView.findViewById(R.id.txv_request_hour);
            seats=itemView.findViewById(R.id.txv_request_seats);
            three_point=itemView.findViewById(R.id.imgbttn_request_three_point);


        }


    }

    /**
     * Enlaza el adaptador con el card del request (list_request)
     */
    @Override
    public AdapterFireRequest.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_request,viewGroup,false);
        //  v.setOnClickListener(onClickListener);
        return new AdapterFireRequest.ViewHolder(v);
    }
}

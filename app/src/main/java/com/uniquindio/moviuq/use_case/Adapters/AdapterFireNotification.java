package com.uniquindio.moviuq.use_case.Adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.domain.Chat;
import com.uniquindio.moviuq.domain.Notification;
import com.uniquindio.moviuq.domain.User;
import com.uniquindio.moviuq.provider.data_local.DataLocal;
import com.uniquindio.moviuq.provider.services.date.DateCalculator;
import com.uniquindio.moviuq.use_case.Case_Chat;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

        Glide.with(context).
                load(model.getPhotouserTransmitter()).into(holder.photoUser);



        /** Clic button three_Points */
        holder.three_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dialog=new Dialog(context,R.style.PauseDialog);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(
                        Color.TRANSPARENT
                ));

                /** Ventana dialogo mylist **/
                dialog.setContentView(R.layout.dialog_notification);
                dialog.show();

                RadioButton rd_si=dialog.findViewById(R.id.rd_si);
                RadioButton rd_hablar=dialog.findViewById(R.id.rd_hablar);

                rd_si.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //dialog.hide();
                        Toast.makeText(context, "Viaje Confirmado!", Toast.LENGTH_SHORT).show();
                        /** Enviar notificacion al otro usuario**/
                        RequestQueue myrequest= Volley.newRequestQueue(context);
                        JSONObject json = new JSONObject();

                        try {

                            String url_foto="";

                            json.put("to",model.getTokenRespuesta());
                            JSONObject notificacion=new JSONObject();
                            notificacion.put("titulo", "Confirmación");
                            notificacion.put("detalle", DataLocal.getUser().getName() +" Ha confirmado el servicio de transporte");
                            notificacion.put("foto",url_foto);

                            json.put("data",notificacion);
                            String URL="https://fcm.googleapis.com/fcm/send";
                            JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST,URL,json,null,null){
                                @Override
                                public Map<String, String> getHeaders() {
                                    Map<String,String>header=new HashMap<>();
                                    header.put("content-type","application/json");
                                    header.put("authorization","key=AAAAp1QX8SY:APA91bEaBh3YUYLtyPjwABa37KFomH2_nMNM6ny3PuJkVBfhqxdrp1bsCg4HZlB7SS3kMTu1jDA_st_k2R9F41h_XVTe9Xy0EcUIeso3gHyiL9szZkb652quMbWhQkjTw0GvtblZONw-");
                                    return header;

                                }
                            };
                            myrequest.add(request);


                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }

                });

                rd_hablar.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(View v) {
                       // dialog.hide();
                        User user=DataLocal.getUser();
                        Date date = new Date();
                        Case_Chat case_chat=new Case_Chat((Activity) context);
                        DateCalculator dateCalculator = new DateCalculator(date);
                        String dateFormat= dateCalculator.getCompleteDay();

                        Chat chat=new Chat(model.getEmailuseremisor()+ user.getMail(), model.getNameuserreceiver(),user.getName(),user.getMail(), model.getEmailuseremisor(),model.getPhotouserTransmitter(),user.getPhoto(),dateFormat,"");
                        case_chat.iniciarChat(user.getMail(),model.getEmailuseremisor(),chat);
                    }
                });



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

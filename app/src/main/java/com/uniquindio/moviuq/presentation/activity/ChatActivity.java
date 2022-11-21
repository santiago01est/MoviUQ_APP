package com.uniquindio.moviuq.presentation.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.domain.Chat;
import com.uniquindio.moviuq.domain.Mensaje;
import com.uniquindio.moviuq.provider.data_local.DataLocal;
import com.uniquindio.moviuq.provider.services.date.DateCalculator;
import com.uniquindio.moviuq.use_case.Adapters.AdapterFireMensajes;
import com.uniquindio.moviuq.use_case.Case_Chat;

import java.util.Date;

public class ChatActivity extends AppCompatActivity {

    private String chat;
    private RecyclerView recyclerView;
    private AdapterFireMensajes adapterFireMensajes;
    private Case_Chat case_chat;
    private Button enviar;
    private EditText mensajetext;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        getData();
        case_chat=new Case_Chat(this);


        //Fijar Recycler chats
        recyclerView = findViewById(R.id.recycler_frag_chats);
        enviar = findViewById(R.id.bttn_enviar_mensaje);
        back = findViewById(R.id.img_bttn_back);
        mensajetext = findViewById(R.id.edit_text_mensaje);
        mensajetext.requestFocus();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.getItemAnimator().setChangeDuration(0);
        Query query= FirebaseFirestore.getInstance().collection("mensajes").whereEqualTo("idChat",chat);
        FirestoreRecyclerOptions<Mensaje> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Mensaje>().setQuery(query, Mensaje.class).build();
        adapterFireMensajes = new AdapterFireMensajes(firestoreRecyclerOptions,this);
        recyclerView.setAdapter(adapterFireMensajes);
        adapterFireMensajes.notifyDataSetChanged();

        enviar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                if(!mensajetext.getText().toString().isEmpty()){
                    Date date= new Date();
                    DateCalculator dateCalculator=new DateCalculator(date);
                    Mensaje mensaje=new Mensaje(dateCalculator.getCompleteHourId(), chat, DataLocal.getUser().getName(), DataLocal.getUser().getPhoto(),dateCalculator.getCompleteDay(),mensajetext.getText().toString());
                    FirebaseFirestore.getInstance().collection("mensajes").document(mensaje.getId()).set(mensaje);
                    mensajetext.setText("");
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mensajetext.getWindowToken(), 0);
                }else{
                    Toast.makeText(ChatActivity.this, "Escribe un mensaje", Toast.LENGTH_SHORT).show();
                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }

    private void getData() {

        Bundle objeto=getIntent().getExtras();

        if(objeto!=null){
            chat=(String) objeto.getSerializable("chat");
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        recyclerView.getRecycledViewPool().clear();
        //noinspection NotifyDataSetChanged
        adapterFireMensajes.notifyDataSetChanged();
        adapterFireMensajes.startListening();
    }

    @Override
    public void onStop(){
        super.onStop();
        adapterFireMensajes.stopListening();
    }
}
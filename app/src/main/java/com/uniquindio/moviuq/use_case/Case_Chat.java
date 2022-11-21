package com.uniquindio.moviuq.use_case;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.uniquindio.moviuq.data.ChatImpl;
import com.uniquindio.moviuq.data.ChatService;
import com.uniquindio.moviuq.data.UserImpl;
import com.uniquindio.moviuq.data.UserService;
import com.uniquindio.moviuq.domain.Chat;
import com.uniquindio.moviuq.presentation.activity.ChatActivity;
import com.uniquindio.moviuq.presentation.activity.DetailOfferTravelActivity;
import com.uniquindio.moviuq.provider.data_local.DataLocal;

public class Case_Chat {

    private Activity activity;
    private ChatService chatService = new ChatImpl();

    public Case_Chat(Activity activity) {
        this.activity = activity;
    }

    public void iniciarChat(String idMe,String idYou, Chat chat){
        chatService.iniciarChat(idMe,idYou,activity,chat);

    }


    public void lanzarChatActivity(String chat) {

        Intent i = new Intent(activity, ChatActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("chat", chat);
        i.putExtras(bundle);
        activity.startActivity(i);

    }


}

package com.uniquindio.moviuq.data;

import android.app.Activity;

import com.uniquindio.moviuq.domain.Chat;

public interface ChatService {

    void iniciarChat(String idMe, String idYou, Activity activity, Chat chat);
}

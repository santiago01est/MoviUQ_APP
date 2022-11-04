package com.uniquindio.moviuq.data;

import android.app.Activity;

import com.uniquindio.moviuq.domain.Request;

public interface RequestService {
    void createRequest(Request request,String email, Activity activity);
}

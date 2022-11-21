package com.uniquindio.moviuq.use_case;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;


import com.uniquindio.moviuq.data.RequestImpl;
import com.uniquindio.moviuq.data.RequestService;
import com.uniquindio.moviuq.domain.EstadoPost;
import com.uniquindio.moviuq.domain.MyPlace;
import com.uniquindio.moviuq.domain.Request;
import com.uniquindio.moviuq.domain.User;
import com.uniquindio.moviuq.presentation.activity.CreateRequestActivity;
import com.uniquindio.moviuq.provider.data_local.DataLocal;
import com.uniquindio.moviuq.provider.services.date.DateCalculator;

import java.util.Date;


public class Case_Request {

    private Activity activity;
    private RequestService requestService=new RequestImpl();

    public Case_Request(Activity activity) {
        this.activity = activity;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createRequest(String title, String dateTravel, String hour, int seats, MyPlace placeTo, MyPlace placeFrom) {

        Request request;
        User user=DataLocal.getUser();
        String emailUser= user.getMail();
        Date date = new Date();
        DateCalculator dateCalculator = new DateCalculator(date);
        String idViaje="OR"+emailUser+dateCalculator.getCompleteHourId();
        request=new Request(idViaje,user.getMail(),user.getName(),user.getPhoto(),dateCalculator.getCompleteDay(),title,"",dateTravel,hour,null,null,seats, EstadoPost.DISPONIBLE,dateCalculator.getIdFechaOrder());
        requestService.createRequest(request,emailUser,activity);
    }

    public void lanzarHome(){
        activity.finish();
    }

    public void lanzarCreateRequest(){
        Intent i = new Intent(activity, CreateRequestActivity.class);
        activity.startActivity(i);
    }
}

package com.uniquindio.moviuq.use_case;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.widget.DatePicker;

import androidx.annotation.IntRange;
import androidx.annotation.RequiresApi;

import com.google.android.gms.maps.model.Marker;
import com.google.android.libraries.places.api.model.Place;
import com.uniquindio.moviuq.data.OfferImpl;
import com.uniquindio.moviuq.data.OfferService;
import com.uniquindio.moviuq.domain.Condition;
import com.uniquindio.moviuq.domain.EstadoPost;
import com.uniquindio.moviuq.domain.MyPlace;
import com.uniquindio.moviuq.domain.Offer;
import com.uniquindio.moviuq.domain.Rute;
import com.uniquindio.moviuq.domain.User;
import com.uniquindio.moviuq.domain.VehicleType;
import com.uniquindio.moviuq.presentation.activity.CreateOfferActivity;
import com.uniquindio.moviuq.presentation.activity.LoginActivity;
import com.uniquindio.moviuq.presentation.activity.SearchTravelActivity;
import com.uniquindio.moviuq.provider.data_local.DataLocal;
import com.uniquindio.moviuq.provider.services.date.DateCalculator;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Case_Offer {

    private Activity activity;
    private OfferService offerService=new OfferImpl();
    private Case_User case_user;


    public Case_Offer(Activity activity) {
        this.activity = activity;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createOffer(String title, String desc, String dateTravel, String hourTravel, VehicleType vehicleType, @IntRange(from=0,to=6) int seats, List<Condition> myCondition, Marker mMarkerFrom, Marker mMarkerTo, MyPlace placeTo, MyPlace placeFrom) {
        case_user=new Case_User(activity);
        String emailUser=case_user.getEmailUser();
        Offer offer;
        User user= DataLocal.getUser();
        Rute rute;
        Date date = new Date();
        DateCalculator dateCalculator = new DateCalculator(date);
        String idViaje="OV"+emailUser+dateCalculator.getCompleteHourId();
        rute=new Rute("R"+emailUser+dateCalculator.getCompleteHourId(),idViaje,placeFrom,placeTo);
        offer=new Offer(idViaje,emailUser,user.getName(),user.getPhoto(),dateCalculator.getCompleteDay(),title,desc,dateTravel,hourTravel,rute,vehicleType,seats, EstadoPost.DISPONIBLE,myCondition,user.getToken());
        offerService.createOffer(offer, emailUser, activity);
    }


    public void lanzarHome(){
        activity.finish();
    }

    public void lanzarCreateOffer(){
        Intent i = new Intent(activity, CreateOfferActivity.class);
        activity.startActivity(i);
    }

    public void lanzarSearchTravel(){
        Intent i = new Intent(activity, SearchTravelActivity.class);
        activity.startActivity(i);
    }
}

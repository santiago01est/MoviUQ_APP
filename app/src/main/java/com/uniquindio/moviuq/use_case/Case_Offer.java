package com.uniquindio.moviuq.use_case;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Build;
import android.widget.DatePicker;

import androidx.annotation.RequiresApi;

import com.google.android.gms.maps.model.Marker;
import com.google.android.libraries.places.api.model.Place;
import com.uniquindio.moviuq.data.OfferImpl;
import com.uniquindio.moviuq.data.OfferService;
import com.uniquindio.moviuq.domain.Condition;
import com.uniquindio.moviuq.domain.Offer;
import com.uniquindio.moviuq.domain.Rute;
import com.uniquindio.moviuq.domain.VehicleType;
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
    public void createOffer(String title, String desc, String dateTravel, String hourTravel, VehicleType vehicleType, int seats, List<Condition> myCondition, Marker mMarkerFrom, Marker mMarkerTo, Place placeTo, Place placeFrom) {
        case_user=new Case_User(activity);
        String emailUser=case_user.getEmailUser();
        Offer offer;
        Rute rute;
        Date date = new Date();
        DateCalculator dateCalculator = new DateCalculator(date);
        String idViaje="OV"+emailUser+dateCalculator.getCompleteHourId();
        rute=new Rute("R"+emailUser+dateCalculator.getCompleteHourId(),idViaje,mMarkerFrom,mMarkerTo,placeFrom,placeTo);
        offer=new Offer(idViaje,emailUser,dateCalculator.getCompleteDay(),title,desc,dateTravel,hourTravel,rute,vehicleType,seats,myCondition);
        offerService.createOffer(offer, emailUser, activity);
    }


    public void lanzarHome(){
        activity.finish();
    }
}

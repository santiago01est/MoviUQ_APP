package com.uniquindio.moviuq.use_case;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Build;
import android.widget.DatePicker;

import androidx.annotation.RequiresApi;

import com.uniquindio.moviuq.domain.Condition;
import com.uniquindio.moviuq.domain.VehicleType;

import java.util.Calendar;
import java.util.List;

public class Case_Offer {

    private Activity activity;


    public Case_Offer(Activity activity) {
        this.activity = activity;
    }



    public void createOffer(String title, String toString, CharSequence text, CharSequence text1, VehicleType vehicleType, String toString1, List<Condition> myCondition) {
    }
}

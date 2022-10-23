package com.uniquindio.moviuq.data;

import android.app.Activity;

import com.uniquindio.moviuq.domain.Offer;

public interface OfferService {
    void createOffer(Offer offer, String email, Activity activity);
}

package com.uniquindio.moviuq.provider.services.firebase;

import com.google.firebase.auth.FirebaseAuth;


public class FirebaseAuthService {

    public  FirebaseAuth MAUTH;

    public  FirebaseAuth getAuth(){
        if(MAUTH==null){
            MAUTH = FirebaseAuth.getInstance();
        }
        return MAUTH;
    }
}

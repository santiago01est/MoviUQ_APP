package com.uniquindio.moviuq.provider.services.firebase;

import com.google.firebase.auth.FirebaseAuth;


public class FirebaseAuthService {

    public static FirebaseAuth mAuth;

    public static FirebaseAuth getAuth(){
        if(mAuth==null){
            mAuth = FirebaseAuth.getInstance();
        }
        return mAuth;
    }
}

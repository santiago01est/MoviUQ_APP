package com.uniquindio.moviuq.provider.services.firebase;


import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseCFDBService {

    private static FirebaseFirestore db;

    public static FirebaseFirestore getBD(){
        if(db==null){
            db = FirebaseFirestore.getInstance();
        }
        return db;
    }
}

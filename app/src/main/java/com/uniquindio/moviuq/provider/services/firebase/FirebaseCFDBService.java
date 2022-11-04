package com.uniquindio.moviuq.provider.services.firebase;


import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseCFDBService {


    private FirebaseFirestore DB;

    public  FirebaseFirestore getBD(){
        if(DB==null){
            DB = FirebaseFirestore.getInstance();
        }
        return DB;
    }
}

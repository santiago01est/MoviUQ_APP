package com.uniquindio.moviuq.data;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.uniquindio.moviuq.domain.Chat;
import com.uniquindio.moviuq.use_case.Case_Chat;
import com.uniquindio.moviuq.use_case.Case_Sign;

import java.util.List;

public class ChatImpl implements ChatService{

    @Override
    public void iniciarChat(String idMe, String idYou, Activity activity, Chat chat) {


            Case_Chat case_chat = new Case_Chat(activity);


            FirebaseFirestore.getInstance().collection("chats").whereEqualTo("idMe", idMe).whereEqualTo("idYou",idYou).
                    get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                QuerySnapshot query = task.getResult();
                                if (query.size() == 0) {



                                    //
                                    FirebaseFirestore.getInstance().collection("chats").whereEqualTo("idYou", idMe).whereEqualTo("idMe",idYou).
                                            get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                    if (task.isSuccessful()) {
                                                        QuerySnapshot query = task.getResult();
                                                        if (query.size() == 0) {

                                                            FirebaseFirestore.getInstance().collection("chats").document(chat.getId()).set(chat).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void unused) {

                                                                    case_chat.lanzarChatActivity(chat.getId());

                                                                }
                                                            }).addOnFailureListener(new OnFailureListener() {
                                                                @Override
                                                                public void onFailure(@NonNull Exception e) {

                                                                    Toast.makeText(activity, "Hubo un error inesperado, vuelva a intentarlo", Toast.LENGTH_SHORT).show();

                                                                }
                                                            });

                                                        } else {
                                                            List<DocumentSnapshot> x = query.getDocuments();
                                                            DocumentSnapshot chaat = x.get(0);
                                                            String z= (String) chaat.get("id");

                                                            case_chat.lanzarChatActivity(z);

                                                        }
                                                    }
                                                }
                                            });

                                    //


                                } else {
                                    List<DocumentSnapshot> x = query.getDocuments();
                                    DocumentSnapshot chaat = x.get(0);
                                    String z= (String) chaat.get("id");

                                    case_chat.lanzarChatActivity(z);

                                }
                            }
                        }
                    });


    }
}

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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.uniquindio.moviuq.domain.Chat;
import com.uniquindio.moviuq.use_case.Case_Chat;
import com.uniquindio.moviuq.use_case.Case_Sign;

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
                                    FirebaseFirestore.getInstance().collection("chats").document(chat.getId()).set(chat).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {

                                            case_chat.lanzarChatActivity(chat);

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {

                                            Toast.makeText(activity, "Hubo un error inesperado, vuelva a intentarlo", Toast.LENGTH_SHORT).show();

                                        }
                                    });

                                } else {
                                    case_chat.lanzarChatActivity(chat);

                                }
                            }
                        }
                    });


    }
}

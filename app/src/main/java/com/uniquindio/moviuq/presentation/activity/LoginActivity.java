package com.uniquindio.moviuq.presentation.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.provider.services.firebase.FirebaseCFDBService;
import com.uniquindio.moviuq.use_case.Case_Log;

public class LoginActivity extends AppCompatActivity {

    private LottieAnimationView img;
    private Case_Log case_log;
    TextInputEditText log_email, log_pass;
    private ProgressBar progressBar;
    private static final int REQ_ONE_TAP = 235;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /** ANIMATION **/
        img=findViewById(R.id.lottie_login);
        img.playAnimation();

        /** USE CASE **/
        case_log=new Case_Log(this);

        /** REFERENCE ELEMETS**/
        log_email =findViewById(R.id.login_correo);
        log_pass =findViewById(R.id.login_password);
        progressBar = findViewById(R.id.progressBar_login);
    }

    public void loginUser(View view){

        if (!log_email.getText().toString().isEmpty() && !log_pass.getText().toString().isEmpty()) {
            case_log.login_user(log_email.getText().toString().trim(), log_pass.getText().toString());
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    public void googleIniciarSesionUsuario(View view) {
        FirebaseAuth.getInstance().signOut();
        GoogleSignInOptions googleSignInOptions= new GoogleSignInOptions.
                Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.IdClient)).requestEmail().build();

        GoogleSignInClient googleSignInClient= GoogleSignIn.getClient(this,googleSignInOptions);
        googleSignInClient.signOut();
        startActivityForResult(googleSignInClient.getSignInIntent(),REQ_ONE_TAP);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //   Toast.makeText(this, "" + requestCode, Toast.LENGTH_SHORT).show();
        if (requestCode == REQ_ONE_TAP) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
              // Toast.makeText(this, "235if", Toast.LENGTH_SHORT).show();

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if(account != null){
                    // Toast.makeText(this, "" + requestCode, Toast.LENGTH_SHORT).show();
                    AuthCredential authCredential= GoogleAuthProvider.getCredential(account.getIdToken(),null);
                    FirebaseAuth.getInstance().signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                GoogleSignInAccount account= GoogleSignIn.getLastSignedInAccount(getApplicationContext());

                                //hacer consulta

                                FirebaseCFDBService.getBD().collection("user").
                                        whereEqualTo("email",account.getEmail()).get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    boolean existe=false;

                                                    for (QueryDocumentSnapshot query:task.getResult()) {
                                                        existe=true;
                                                         Log.d("correo de usuario",query.getData()+"");

                                                    }
                                                    if(existe){
                                                        case_log.lanzarWelcome();
                                                        //homeActivity();
                                                        registroExito(1);
                                                        Log.d("Goo","entro al existe");

                                                    }else{
                                                        Toast.makeText(LoginActivity.this, "Se debe registrar", Toast.LENGTH_SHORT).show();
                                                        //startActivity(new Intent(LoginActivity.this,CreatePerfilActivity.class));
                                                        //registrar datos del usuario
                                                    }
                                                }

                                            }
                                        });

                            }else{
                                registroExito(2);
                            }
                        }
                    });
                }

            } catch (ApiException e) {
                e.printStackTrace();
            }

        }
    }


    private void registroExito(int i) {
        if(i==1){
             Toast.makeText(this, "ingreso Exitoso!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Ups", Toast.LENGTH_SHORT).show();
        }

    }
}
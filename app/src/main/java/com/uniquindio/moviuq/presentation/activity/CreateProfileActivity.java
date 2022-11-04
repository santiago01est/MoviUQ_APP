package com.uniquindio.moviuq.presentation.activity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.domain.User;
import com.uniquindio.moviuq.provider.services.firebase.FirebaseCFDBService;
import com.uniquindio.moviuq.use_case.Case_Profile;
import com.uniquindio.moviuq.use_case.Case_User;

import java.io.ByteArrayOutputStream;

public class CreateProfileActivity extends AppCompatActivity {

    /** Casa de uso para la activity**/
    private Case_Profile case_createProfile;
    private Case_User case_user;
    /** Elementos UI**/
    private TextInputEditText crProf_name, crProf_lastName, crProf_years, crProf_phoneNumber, crProf_city;
    private  ProgressBar cargando;
    private  ImageView foto_perfil;
    private Button create;
    /** Objetos */
    private String emailUser;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    private ImageButton add_photo;
    private Uri imageUri;
    private Uri downloadUri;
    /** Codigo de respuesta**/
    private static final int PICK_IMAGE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        /** iniciacion de variables y casos de uso*/
        init();


        /** REFERENCE ELEMETS**/
        references();

        add_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /** Metodo para abrir galeria de fotos y seleccionar foto de usuario**/
                openGallery();
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createProfile();
            }
        });

    }



    public void init(){
        case_createProfile=new Case_Profile(this);
        case_user=new Case_User(this);
        emailUser=case_user.getEmailUser();
    }

    public void references(){
        crProf_name =findViewById(R.id.profile_name);
        crProf_lastName =findViewById(R.id.profile_lastname);
        crProf_years = findViewById(R.id.profile_years);
        crProf_phoneNumber = findViewById(R.id.profile_phone);
        crProf_city = findViewById(R.id.profile_city);
        add_photo=findViewById(R.id.select_photo);
        cargando=findViewById(R.id.progressBar_create_user);
        foto_perfil=findViewById(R.id.image_user_profile);
        create=findViewById(R.id.btn_perfil_continuar);
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();

            foto_perfil.setImageURI(imageUri);

        }
    }

    /** Metodo createProfile
     *
     *  Se encarga de obtener la informacion ingresada en los TextInputEditText anteriormente referenciados,
     *  se verifica que en ningun campo hayan informacion vacia (No ingresada ""), en caso de haber informacion vacia se
     *  mostrara mediante un Toast un mensaje expresando una condicion necesaria, ademas guarda la informacion en variables
     *  para ser direccionadas a la capa de casos de usos de createProfile.
     **/
    public void createProfile(){
        if(crProf_name.getText().toString().isEmpty() || crProf_lastName.getText().toString().isEmpty()
            || crProf_years.getText().toString().isEmpty() || crProf_phoneNumber.getText().toString().isEmpty()
            || crProf_city.getText().toString().isEmpty()){

            Toast.makeText(this, "Ningun campo puede estar vacio, llena los campos porfavor", Toast.LENGTH_SHORT).show();

        }else{
            cargando.setVisibility(View.VISIBLE);
            String name= crProf_name.getText().toString();
            String last_name= crProf_lastName.getText().toString();
            int years = Integer.parseInt(crProf_years.getText().toString());
            long phoneNumber= Long.parseLong(crProf_years.getText().toString());
            String city= crProf_city.getText().toString();


            upData(emailUser,name,last_name,years,phoneNumber,city);

        }

    }

    /**
     * Metodo tomado de la documentación oficial de google Firebase
     * donde se procesa la imagen y se obtiene su respectivo url y se sube a la base de datos
     * asi como se guarda al usuario
     * @param emailUser
     * @param name
     * @param last_name
     * @param years
     * @param phoneNumber
     * @param city
     */
    private void upData(String emailUser, String name, String last_name, int years, long phoneNumber, String city) {
        StorageReference storageRef = storage.getReference();
        StorageReference photoRef = storageRef.child("perfil/"+emailUser+"photo.jpg");
        StorageReference profileImagesRef = storageRef.child("perfil/");

        foto_perfil.setDrawingCacheEnabled(true);
        foto_perfil.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) foto_perfil.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = photoRef.putBytes(data);
        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                // Continue with the task to get the download URL
                return photoRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    downloadUri = task.getResult();
                    case_createProfile.create_profile(downloadUri+"", name, last_name, years, phoneNumber, city);
                    cargando.setVisibility(View.GONE);

                } else {

                }
            }
        });

    }



}
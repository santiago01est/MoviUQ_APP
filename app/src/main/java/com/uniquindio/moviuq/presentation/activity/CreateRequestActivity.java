package com.uniquindio.moviuq;

import static com.android.volley.VolleyLog.TAG;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.uniquindio.moviuq.data.VerificationImpl;
import com.uniquindio.moviuq.data.VerificationService;
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.domain.MyPlace;
import com.uniquindio.moviuq.use_case.Case_Request;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CreateRequestActivity extends AppCompatActivity {

    /**
     * Constantes de respuesta para la onActivityResult
     **/
    private static int FROM_REQUEST_CODE = 1;
    private static int TO_REQUEST_CODE = 2;

    /** Elementos UI**/
    private TextView from_travel;
    private TextView to_travel;
    private EditText seats;
    private TextView txv_date;
    private TextView txv_hour;
    private Button request;
    private MaterialToolbar toolbar;


    /**
     * Caso de Uso y objetos
     **/
    private Case_Request case_request;
    private Place place;
    private GoogleMap mMap;
    private MyPlace placeFrom;
    private MyPlace placeTo;
    private LatLng mToLatLng=null;
    private LatLng mFromLatLng=null;
    private VerificationService verificationService = new VerificationImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_request);

        /** Inicializar datos**/
        case_request=new Case_Request(this);
        placeTo=new MyPlace();
        placeFrom=new MyPlace();

        referencesElements();

        /** Logica para obtener la información de los Lugares*/
        setupPlaces();

        /** referencia de elementos UI para la fecha y sus respectivos onclick listeners
         * configuración de la fecha del viaje */
        setupDate();

        /** evento clic del boton crear request**/
        createRequest();

        /** boton de navegacion Back de la toolbar**/
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });

    }

    /** Conexion con los elementos ui*/
    private void referencesElements() {

        seats= findViewById(R.id.edtText_seat);
        toolbar=findViewById(R.id.topAppBar);
        from_travel = findViewById(R.id.txv_from_travel);
        to_travel = findViewById(R.id.txv_to_travel);
        txv_date=findViewById(R.id.txv_request_date);
        txv_hour=findViewById(R.id.txv_request_hour);

    }

    /** Button Post request **/
    private void createRequest() {
        request=findViewById(R.id.btn_post_request);

        request.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String from_travelString;
                if(from_travel.getText().toString().equals("De")){
                    from_travelString= "";
                }else{
                    from_travelString=from_travel.getText().toString();
                }
                String to_travelString;
                if(to_travel.getText().toString().equals("A")){
                    to_travelString= "";
                }else{
                    to_travelString=to_travel.getText().toString();
                }
                String seatsString= seats.getText().toString();
                String txv_dateString= txv_date.getText().toString();
                List<String> campos = new ArrayList<>(Arrays.asList(from_travelString,to_travelString,seatsString,txv_dateString));
                if(verificationService.camposVacios(campos, -2)){
                    Toast.makeText(CreateRequestActivity.this, "Ingresa los campos obligatorios (*)", Toast.LENGTH_SHORT).show();

                }else{
                    /** se crea titulo con el nombre de los 2 lugares seleccionados*/
                    String title=placeFrom.getName()+" - " +placeTo.getName();

                    case_request.createRequest(title,txv_dateString, txv_hour.getText().toString(), Integer.parseInt(seatsString) ,placeTo,placeFrom);
                }

            }
        });
    }

    private boolean verificarCampos() {
        boolean centinela=true;

        if( from_travel.getText()=="De" ||
                to_travel.getText()=="A"  ||
                seats.getText().toString().isEmpty() || txv_date.getText().toString().isEmpty()){
            centinela=false;

        }

        return  centinela;
    }

    /**
     * PLACES
     **/

    private void setupPlaces() {

        /** Initialize the SDK */
        Places.initialize(getApplicationContext(), getString(R.string.mapsApi));

        /**Reference elements*/


        from_travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPlace(FROM_REQUEST_CODE);
            }
        });

        to_travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPlace(TO_REQUEST_CODE);
            }
        });
    }

    /**
     * Metodo onclick que infla un fragment haciendo uso de la Api Google Maps para ingresar los datos del
     * lugar para crear el viaje
     *
     * @param request_code
     */
    private void selectPlace(int request_code) {
        // Set the fields to specify which types of place data to
        // return after the user has made a selection.
        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG);
        // Start the autocomplete intent.
        Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                .build(this);

        startActivityForResult(intent, request_code);
    }


    /**
     * Metodo que obtiene los datos del fragment de la
     * Api de google maps
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == FROM_REQUEST_CODE) {
            processAutoCompleteResult(from_travel, requestCode, resultCode, data, mFromLatLng);
            return;
        } else if (requestCode == TO_REQUEST_CODE) {
            processAutoCompleteResult(to_travel, requestCode, resultCode, data, mToLatLng);
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void processAutoCompleteResult(TextView txv_travel, int requestCode, int resultCode, @Nullable Intent data, LatLng latLng) {

        if (resultCode == RESULT_OK) {
            place = Autocomplete.getPlaceFromIntent(data);

            Log.i(TAG, "MyPlace: " + place.getName() + ", " + place.getId());

            /** Metodo que refleja los cambios en la UI */
            updatePlaceUi(place, txv_travel);
            latLng = place.getLatLng();
            if (requestCode == FROM_REQUEST_CODE) {
                //setMarketFrom(latLng);

                placeFrom.setName(place.getName());
                placeFrom.setAddress(place.getAddress());
                placeFrom.setLatitude(latLng.latitude);
                placeFrom.setLongitude(latLng.longitude);
            } else if (requestCode == TO_REQUEST_CODE) {
                //setMarketTo(latLng);
                placeTo.setAddress(place.getAddress());
                placeTo.setName(place.getName());
                placeTo.setLatitude(latLng.latitude);
                placeTo.setLongitude(latLng.longitude);
            } else {

            }


        } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
            // TODO: Handle the error.
            Status status = Autocomplete.getStatusFromIntent(data);
            Log.i(TAG, status.getStatusMessage());
        }

    }

    /**
     * metodo que es invocado en onActivityResult
     * para traer el lugar seleccionado por el usuario
     * y se fija el texto del textview correspondiente con el
     * nombre del lugar
     *
     * @param place
     */
    private void updatePlaceUi(Place place, TextView txv_travel) {
        txv_travel.setText("" + place.getName());
    }

    /** DATE **/

    private void setupDate() {



        txv_date.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                getDate();
            }
        });

        txv_hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getHour();
            }
        });
    }




    /**
     * Metodo que obtiene la fecha desde el calendario fragment
     * Documentación de google -DatePickerDialog-
     */
    private void getDate(){

        /** Instancia fecha actual en la vista Calendar**/
        Calendar cal = Calendar.getInstance();
        int year= cal.get(Calendar.YEAR);
        int month= cal.get(Calendar.MONTH);
        int day= cal.get(Calendar.DAY_OF_MONTH);

        /** Escuchador para obtener fecha ingresada por el usuario**/
        DatePickerDialog dpd= new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String date = day+"/"+month+"/"+year;
                /** se fija la fecha en el recurso textview**/
                txv_date.setText(date);
            }
        }, year, month, day);
        dpd.show();

    }

    /**
     * Metodo que obtiene la hora desde timePickerDialog
     * Documentación de google -timePickerDialog-
     */
    private void getHour() {

        Calendar cal=Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minutes= cal.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog= new TimePickerDialog(this, R.style.PickerTheme,new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                String amPm="AM";
                if(hourOfDay>=12){
                    amPm="PM";
                }
                String hour =hourOfDay+":"+minute+" "+amPm;
                txv_hour.setText(hour);

            }
        },hour,minutes,false);
        timePickerDialog.show();
    }
}
package com.uniquindio.moviuq.presentation.activity;

import static com.android.volley.VolleyLog.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.provider.services.maps.myMapFragment;
import com.uniquindio.moviuq.use_case.Case_Offer;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CreateOfferActivity extends AppCompatActivity implements OnMapReadyCallback {

    /**
     * Constantes de respuesta para la onActivityResult
     **/
    private static int FROM_REQUEST_CODE = 1;
    private static int TO_REQUEST_CODE = 2;

    /**
     * Elementos UI
     **/
    private TextView from_travel;
    private TextView to_travel;
    private ScrollView mScrollView;
    private TextView txv_date;
    private TextView txv_hour;
    private Button post;

    /**
     * Objeto Place de la libreria Google Maps
     **/
    private Place from_place_travel;

    /**
     * Caso de Uso y objetos
     **/
    private Case_Offer case_offer;
    private Place place;
    private GoogleMap mMap;
    private Place placeFrom;
    private Place placeTo;
    private LatLng mToLatLng;
    private LatLng mFromLatLng;
    private Marker mMarkerTo = null;
    private Marker mMarkerFrom = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_offer);

        case_offer = new Case_Offer(this);

        /** Configuración de Google Maps*/
        setupMap();

        /** Logica para obtener la información de los Lugares*/
        setupPlaces();

        /** referencia de elementos UI para la fecha y sus respectivos onclick listeners
         * configuración de la fecha del viaje */
        setupDate();

        /** Referencia del elemento Ui boton para crear la oferta de viaje**/
        createOffer();



    }

    /** Button Post Offer **/
    private void createOffer() {
        post=findViewById(R.id.btn_post_offer);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                case_offer.createOffer();
            }
        });
    }

    /** DATE **/

    private void setupDate() {

        txv_date=findViewById(R.id.txv_offer_date);
        txv_hour=findViewById(R.id.txv_offer_hour);

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



    /**
     * PLACES
     **/

    private void setupPlaces() {

        /** Initialize the SDK */
        Places.initialize(getApplicationContext(), getString(R.string.mapsApi));

        /**Reference elements*/
        from_travel = findViewById(R.id.txv_from_travel);
        to_travel = findViewById(R.id.txv_to_travel);

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

            Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());

            /** Metodo que refleja los cambios en la UI */
            updatePlaceUi(place, txv_travel);
            latLng = place.getLatLng();
            if (requestCode == FROM_REQUEST_CODE) {
                setMarketFrom(latLng);
                placeFrom=place;
            } else if (requestCode == TO_REQUEST_CODE) {
                setMarketTo(latLng);
                placeTo=place;
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


    /**
     * MAP
     **/

    private void setupMap() {
        SupportMapFragment mapFragment = ((myMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_map));
        mapFragment.getMapAsync(this);


        mScrollView = (ScrollView) findViewById(R.id.scroll_offer);


        ((myMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_map)).setListener(new myMapFragment.OnTouchListener() {
            @Override
            public void onTouch() {
                mScrollView.requestDisallowInterceptTouchEvent(true);
            }
        });
    }


    /**
     * Manipula el mapa una vez que está disponible.
     * Este callback se dispara cuando el mapa está listo para ser usado.
     * Aquí es donde podemos añadir marcadores o líneas, añadir oyentes o mover la cámara.
     * En este caso añadimos un marcador de inicio del viaje y otro para el destino
     * <p>
     * Si los servicios de Google Play no están instalados en el dispositivo, se pedirá al usuario que lo instale
     * dentro del SupportMapFragment. Este método sólo se activará una vez que el usuario haya
     * instalado los servicios de Google Play y haya vuelto a la aplicación.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMinZoomPreference(10);
        mMap.setMaxZoomPreference(15);

        LatLng latLng = new LatLng(4.55402805, -75.6609262169371);
       // addMarker(latLng,"Universidad");

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));



    }

    /**
     * Metodo que añade un marcador al mapa segun el lugar que el usuario
     * ingresó, para fijar un marcador se debe enviar
     * latitud y longitud del lugar (LatLng)
     * @param latLng
     * @param nombre
     * @return
     */
    private Marker addMarker(LatLng latLng, String nombre) {
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title(nombre);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        return mMap.addMarker(markerOptions);

    }

    /**
     * Metodo que recoge los datos para fijar el marcador
     * del lugar de partida del viaje
     * @param latLng
     */
    private void setMarketFrom(LatLng latLng) {
        if (mMarkerFrom != null) {
            mMarkerFrom.remove();
        }

        mMarkerFrom = addMarker(latLng, "Desde");

    }

    /**
     * Metodo que recoge los datos para fijar el marcador
     * del lugar de destino del viaje
     * @param latLng
     */
    private void setMarketTo(LatLng latLng) {
        if (mMarkerTo != null) {
            mMarkerTo.remove();
        }

        mMarkerTo = addMarker(latLng, "Hasta");

    }
}
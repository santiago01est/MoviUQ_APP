package com.uniquindio.moviuq.presentation.activity;

import static com.android.volley.VolleyLog.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.use_case.Case_Offer;

import org.w3c.dom.Text;

import java.util.Arrays;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_offer);

        case_offer = new Case_Offer(this);

        /** Configuración de Google Maps*/
        setupMap();

        /** Logica para obtener la información de los Lugares*/
        setupPlaces();




    }

    /**  PLACES **/

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
    public void selectPlace(int request_code) {
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
            processAutoCompleteResult(from_travel, requestCode, resultCode, data);
            return;
        } else if (requestCode == TO_REQUEST_CODE) {
            processAutoCompleteResult(to_travel, requestCode, resultCode, data);
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void processAutoCompleteResult(TextView txv_travel, int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode == RESULT_OK) {
            place = Autocomplete.getPlaceFromIntent(data);

            Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());

            /** Metodo que refleja los cambios en la UI */
            updatePlaceUi(place, txv_travel);

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
    public void updatePlaceUi(Place place, TextView txv_travel) {
        txv_travel.setText("" + place.getName());
    }


    /**  MAP **/

    public void  setupMap()
    {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipula el mapa una vez que está disponible.
     * Este callback se dispara cuando el mapa está listo para ser usado.
     * Aquí es donde podemos añadir marcadores o líneas, añadir oyentes o mover la cámara.
     * En este caso añadimos un marcador de inicio del viaje y otro para el destino
     *
     * Si los servicios de Google Play no están instalados en el dispositivo, se pedirá al usuario que lo instale
     * dentro del SupportMapFragment. Este método sólo se activará una vez que el usuario haya
     * instalado los servicios de Google Play y haya vuelto a la aplicación.

     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng latLng = new LatLng(-34, 151);
        addMarker(latLng,"Universidad");

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    public void addMarker(LatLng latLng, String nombre){
        MarkerOptions markerOptions= new MarkerOptions().position(latLng).title(nombre);
        mMap.addMarker(markerOptions);

    }
}
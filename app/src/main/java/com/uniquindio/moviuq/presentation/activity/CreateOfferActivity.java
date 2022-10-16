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

public class CreateOfferActivity extends AppCompatActivity {

    /**
     * Constantes de respuesta para la onActivityResult
     **/
    private static int FROM_REQUEST_CODE = 1;
    private static int TO_REQUEST_CODE = 2;

    /**
     * Elementos UI
     **/
    TextView from_travel;
    TextView to_travel;

    /**
     * Objeto Place de la libreria Google Maps
     **/
    Place from_place_travel;

    /**
     * Caso de Uso
     **/
    Case_Offer case_offer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_offer);

        case_offer = new Case_Offer(this);

        /** Initialize the SDK */
        Places.initialize(getApplicationContext(), getString(R.string.mapsApi));

        /**Reference elements*/
        from_travel = findViewById(R.id.txv_from_travel);
        to_travel = findViewById(R.id.txv_to_travel);


    }

    /**
     * Metodo onclick que infla un fragment haciendo uso de la Api Google Maps para ingresar los datos del
     * lugar de partida del viaje
     *
     * @param view
     */
    public void lugarPartida(View view) {
        // Set the fields to specify which types of place data to
        // return after the user has made a selection.
        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS);
        // Start the autocomplete intent.
        Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                .build(this);

        startActivityForResult(intent, FROM_REQUEST_CODE);
    }

    /**
     * Metodo onclick que infla un fragment haciendo uso de la Api Google Maps para ingresar los datos del
     * lugar de destino del viaje
     *
     * @param view
     */
    public void lugarLlegada(View view) {
        // Set the fields to specify which types of place data to
        // return after the user has made a selection.
        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS);
        // Start the autocomplete intent.
        Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                .build(this);

        startActivityForResult(intent, TO_REQUEST_CODE);

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
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());

                updatePlaceUi(place);
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i(TAG, status.getStatusMessage());
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * metodo que es invocado en onActivityResult
     * para traer el lugar seleccionado por el usuario
     * y se fija el texto del textview correspondiente con el
     * nombre del lugar
     * @param place
     */
    public void updatePlaceUi(Place place){
        from_travel.setText("" + place.getAddress());
    }


}
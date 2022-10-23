package com.uniquindio.moviuq;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ScrollView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.uniquindio.moviuq.provider.services.maps.myMapFragment;

public class DetailOfferTravelActivity extends AppCompatActivity implements OnMapReadyCallback {

    /** Elements UI**/
    private ScrollView mScrollView;

    private GoogleMap mMap;
    private Marker mMarkerTo = null;
    private Marker mMarkerFrom = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_offer_travel);

        referencesElementsUI();

        setupMap();
    }

    public void referencesElementsUI(){
        mScrollView = findViewById(R.id.scroll_detail_offer);
    }



    /**
     * MAP
     **/

    private void setupMap() {
        SupportMapFragment mapFragment = ((myMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_map_detail_offer));
        mapFragment.getMapAsync(this);


        ((myMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_map_detail_offer)).setListener(new myMapFragment.OnTouchListener() {
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
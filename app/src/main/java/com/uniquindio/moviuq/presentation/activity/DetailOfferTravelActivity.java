package com.uniquindio.moviuq.presentation.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.appbar.MaterialToolbar;
import com.uniquindio.moviuq.R;
import com.uniquindio.moviuq.domain.Condition;
import com.uniquindio.moviuq.domain.MyPlace;
import com.uniquindio.moviuq.domain.Offer;
import com.uniquindio.moviuq.domain.User;
import com.uniquindio.moviuq.domain.VehicleType;
import com.uniquindio.moviuq.provider.data_local.DataLocal;
import com.uniquindio.moviuq.provider.services.maps.myMapFragment;
import com.uniquindio.moviuq.use_case.Case_Notification;
import com.uniquindio.moviuq.use_case.Case_User;

import java.util.List;

public class DetailOfferTravelActivity extends AppCompatActivity implements OnMapReadyCallback {

    /** Elements UI**/
    private NestedScrollView mScrollView;
    private TextView txv_title;
    private TextView txv_nameUser;
    private TextView txv_desc;
    private TextView txv_vehicle;
    private TextView txv_seats;
    private TextView txv_date;
    private TextView txv_hour;
    private CheckBox cbx_fumar,cbx_hablar,cbx_comida,cbx_musica,cbx_mascota;
    private LinearLayout contenedor_noconditions;
    private LinearLayout contenedor_main_conditions;
    private ImageView photoUser;
    private Toolbar toolbar;
    private ImageView imgv_photoUser;
    private Button bttn_contratar;


    /** Objets**/
    private Offer offer;
    private String emailUser;
    private User user;
    private GoogleMap mMap;
    private Marker mMarkerTo = null;
    private Marker mMarkerFrom = null;

    /** Case use**/
    Case_User case_user;
    Case_Notification case_notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_offer_travel);

        /** Inicializar objetos**/
        init();
        referencesElementsUI();

        /** metodo que recibe la informacion del adapter */
        getData();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        bttn_contratar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                case_notification.enviarNotificacionContratacion(offer.getToken(),user.getName()+" "+user.getLast_name(),offer.getNameUser());
            }
        });

    }

    private void init(){
        case_user= new Case_User(this);
        case_notification=new Case_Notification(this);
        emailUser=case_user.getEmailUser();
        user=DataLocal.getUser();

    }

    private void getData() {
        /** Recibir objeto */
        Bundle objeto=getIntent().getExtras();
        offer=new Offer();
        if(objeto!=null){
            offer=(Offer) objeto.getSerializable("offer");
            setupData(offer);
        }

    }

    private void setupData(Offer offer) {

        txv_title.setText(offer.getTitle());
        txv_nameUser.setText(offer.getNameUser());
        txv_desc.setText(offer.getDescription());
        VehicleType vehicleType=offer.getVehicleType();
        txv_vehicle.setText("Vehículo: "+ vehicleType.tostring(vehicleType));
        txv_seats.setText("Cupos: "+offer.getSeats());
        txv_date.setText("Fecha: "+offer.getDateTravel());
        txv_hour.setText("Hora: "+offer.getHourTravel());
        Glide.with(this )
                .load(offer.getPhotoUser())
                .into(imgv_photoUser);

        setupConditions(offer.getMyConditions());
        setupMap();


    }

    private void setupConditions(List<Condition> myConditions) {


        for (Condition c: myConditions) {
            if(c==Condition.NINGUNA){
                contenedor_noconditions.setVisibility(View.VISIBLE);
                contenedor_main_conditions.setVisibility(View.GONE);

            }else{
                if(c==Condition.FUMAR){
                    cbx_fumar.setChecked(true);
                }
                if(c==Condition.HABLAR){
                    cbx_hablar.setChecked(true);
                }
                if(c==Condition.COMIDA){
                    cbx_comida.setChecked(true);
                }
                if(c==Condition.MASCOTA){
                    cbx_mascota.setChecked(true);
                }
                if(c==Condition.MUSICA){
                    cbx_musica.setChecked(true);
                }
            }

        }
    }

    public void referencesElementsUI(){
        mScrollView = findViewById(R.id.scroll_detail_offer);
        txv_title=findViewById(R.id.txv_offer_subtitle_title);
        txv_nameUser=findViewById(R.id.txv_offer_subtitle_nameuser);
        txv_desc=findViewById(R.id.txv_offer_content_desc);
        txv_vehicle=findViewById(R.id.txv_detail_offer_vehicle);
        txv_seats=findViewById(R.id.txv_detail_offer_seats);
        txv_date=findViewById(R.id.txv_detail_offer_calendar);
        txv_hour=findViewById(R.id.txv_detail_offer_hour);
        cbx_hablar=findViewById(R.id.cbx_detail_offer_hablar);
        cbx_fumar=findViewById(R.id.cbx_detail_offer_fumar);
        cbx_comida=findViewById(R.id.cbx_detail_offer_comida);
        cbx_mascota=findViewById(R.id.cbx_detail_offer_mascota);
        cbx_musica=findViewById(R.id.cbx_detail_offer_musica);
        contenedor_noconditions=findViewById(R.id.contenedor_no_conditions);
        contenedor_main_conditions=findViewById(R.id.contenedor_main_column);
        photoUser=findViewById(R.id.imageView_photo_user);
        toolbar=findViewById(R.id.toolbar_detail_offer);
        imgv_photoUser=findViewById(R.id.imageView_photo_user);
        bttn_contratar=findViewById(R.id.bttn_contratar);
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

        /** Se incia el mapa con un enfoque hacia la universidad del Quindío**/
        LatLng latLng = new LatLng(4.55402805, -75.6609262169371);

        /** obtener coordenadas de los lugares para crear marcadores*/
        getLatLng();

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));



    }

    private void getLatLng() {

        MyPlace placeFrom= offer.getRute().getPlaceFrom();
        MyPlace placeTo= offer.getRute().getPlaceTo();
        final  LatLng LatLngPlaceFrom = new LatLng(placeFrom.getLatitude(),placeFrom.getLongitude());
        final  LatLng LatLngPlaceTo = new LatLng(placeTo.getLatitude(),placeTo.getLongitude());

        /** Añadir marcadores de los lugares del viaje**/
        addMarker(LatLngPlaceFrom,"Inicio");
        addMarker(LatLngPlaceTo,"Destino");
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
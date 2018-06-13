package com.example.gutty.googlemapsexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnCameraMoveListener {

    Map map;
    GoogleMap googleMap;

    private Marker markerPaciente;

    LatLng cameraPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        map = Map.newInstance();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_map,map).commit();

        map.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;


        //listener para movimiento de la camara del mapa

        googleMap.setOnCameraMoveListener(this);
        cameraPosition = googleMap.getCameraPosition().target;
        addMarker(cameraPosition);

    }


    //agregar marcador
    public void addMarker(LatLng latLng) {
        if (markerPaciente == null) {
            markerPaciente = googleMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title("direccion")
                    .snippet("independencia 19, san simon")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_pac)));
        } else {
            markerPaciente.setPosition(latLng);
        }
    }



    //Callback que se llama cuando se mueve la camara y actualiza la posicion del marker
    @Override
    public void onCameraMove() {
        LatLng cameraPosition = googleMap.getCameraPosition().target;
        if(cameraPosition!=null){
            markerPaciente.setPosition(cameraPosition);
        }


    }
}

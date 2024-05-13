package com.finallabtres.animalogistics.UI.animal.crear;

import static android.app.Activity.RESULT_OK;

import static androidx.core.content.ContextCompat.getSystemService;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.ByteArrayOutputStream;

public class AgregarAnimalViewModel extends AndroidViewModel {


    private Context context;

    private MutableLiveData<Bitmap> foto;

    private MutableLiveData<MapaActual> MAMutable;

    private Location posicion;


    public AgregarAnimalViewModel(@NonNull Application application) {
        super(application);
        this.context=application.getApplicationContext();
    }
    public LiveData<Bitmap> getFoto() {
        if (foto == null) {
            foto = new MutableLiveData<>();
        }
        return foto;
    }

    public LiveData<MapaActual> getMapa(){
        if(MAMutable == null){
            MAMutable = new MutableLiveData<>();
        }
        return MAMutable;
    }


    public void ObtenerMapa(Location posicion) {
        this.posicion = posicion;
        MapaActual MA = new MapaActual();
        MAMutable.setValue(MA);
    }


    public class MapaActual implements OnMapReadyCallback {

        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {

            googleMap.clear();

            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

            googleMap.addMarker(new MarkerOptions().position(new LatLng(posicion.getLatitude(), posicion.getLongitude())).title("Posicion Actual"));


            googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng point) {

                    googleMap.clear();

                    MarkerOptions marker = new MarkerOptions().position(new LatLng(point.latitude, point.longitude)).title("Ubicación del Avistamiento");
                    googleMap.addMarker(marker);

                }
            });

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(posicion.getLatitude(), posicion.getLongitude()))
                    .zoom(19)
                    .bearing(45)
                    .tilt(70)
                    .build();

            CameraUpdate Cupdate = CameraUpdateFactory.newCameraPosition(cameraPosition);

            googleMap.animateCamera(Cupdate);




        }


    }


    public void registrarAnimal(String nombre, String tipo, float edadValue, int tamanoCheckedRadioButtonId, boolean collarChecked, String genero, String detalles, ImageView foto/*, LatLng Posicion*/) {
    }

    public void respuetaDeCamara(ActivityResult result) {

        if (result.getResultCode() == RESULT_OK) {
            //Recupero los datos provenientes de la camara.
            Bundle extras = result.getData().getExtras();
            //Casteo a bitmap lo obtenido de la camara.
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            //Rutina para optimizar la foto,
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            foto.setValue(imageBitmap);
        }

    }
}
package com.finallabtres.animalogistics.UI.animal.crear;

import static android.app.Activity.RESULT_OK;

import static androidx.core.content.ContextCompat.getSystemService;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.finallabtres.animalogistics.API.API;
import com.finallabtres.animalogistics.MODELO.Animal;
import com.finallabtres.animalogistics.MainActivity;
import com.finallabtres.animalogistics.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregarAnimalViewModel extends AndroidViewModel {


    private Context context;

    private MutableLiveData<Bitmap> foto;

    private MutableLiveData<MapaActual> MAMutable;

    private Location posicion;


    // Crear instancia de DecimalFormatSymbols y establecer la coma como separador decimal

    DecimalFormatSymbols simbolos = new DecimalFormatSymbols(Locale.getDefault());
    DecimalFormat formato;


    public AgregarAnimalViewModel(@NonNull Application application) {
        super(application);
        this.context=application.getApplicationContext();

        simbolos.setDecimalSeparator(',');
        formato = new DecimalFormat("#.######", simbolos);
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

                    posicion.setLatitude(point.latitude);
                    posicion.setLongitude(point.longitude);

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


    public void registrarAnimal(View view,  String nombre, String tipo, float edad, String tamanoCheckedRadioButton, boolean collarChecked, String genero, String comentarios, ImageView foto) {

        API.ApiAnimalogistics API_A = API.getApi();

        // recuperamos el token
        String token = API.LeerToken(context);


        // Preparamos el animal
        RequestBody Nombre = RequestBody.create(MediaType.parse("application/json"), nombre);
        RequestBody Edad  = RequestBody.create(MediaType.parse("application/json"), String.valueOf(edad));
        RequestBody Tipo = RequestBody.create(MediaType.parse("application/json"), tipo);
        RequestBody Tamano = RequestBody.create(MediaType.parse("application/json"), tamanoCheckedRadioButton);
        RequestBody Collar = RequestBody.create(MediaType.parse("application/json"), String.valueOf(collarChecked));
        RequestBody Genero = RequestBody.create(MediaType.parse("application/json"), genero);
        RequestBody Comentarios = RequestBody.create(MediaType.parse("application/json"), comentarios);
        RequestBody GPSX = RequestBody.create(MediaType.parse("application/json"), formato.format(posicion.getLatitude()));
        RequestBody GPSY = RequestBody.create(MediaType.parse("application/json"), formato.format(posicion.getLongitude()));

        MultipartBody.Part FotoFile = null; // Inicializa como null

        if (foto.getDrawable() instanceof BitmapDrawable) {

            // Preparamos la imagen del usuario
            Bitmap bitmap = ((BitmapDrawable) foto.getDrawable()).getBitmap();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), byteArrayOutputStream.toByteArray());
            // Creo una parte multipart con el RequestBody.
            FotoFile = MultipartBody.Part.createFormData("FotoFile", "image.jpg", requestFile);

        }



        Call<Animal> call = API_A.animalAgregar(token,Nombre, Edad, Tipo, Tamano, Collar, Genero, Comentarios, GPSX, GPSY, FotoFile);

        call.enqueue(new Callback<Animal>() {
            @Override
            public void onResponse(Call<Animal> call, Response<Animal> response) {
                if (response.isSuccessful()) {

                    Navigation.findNavController(view).navigate(R.id.item_registrar_animal);

                } else {

                    try {
                        String errorResponse = response.errorBody().string();
                        String errorMessage = "";

                        try {
                            JSONObject jsonObject = new JSONObject(errorResponse);
                            if (jsonObject.has("errors")) {

                                Toast.makeText(context, "Problema al registrar : ", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            // Si no se pudo analizar como JSON, el mensaje de error es la respuesta tal cual
                            errorMessage = errorResponse;
                        }

                        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }public void onFailure(Call<Animal> call, Throwable t) {
                Log.d("salida falla ",t.getMessage());
            }

        });

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
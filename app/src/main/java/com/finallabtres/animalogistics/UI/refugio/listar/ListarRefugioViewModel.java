package com.finallabtres.animalogistics.UI.refugio.listar;

import static android.provider.Settings.System.getString;

import static java.lang.Integer.*;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Region;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.finallabtres.animalogistics.API.API;
import com.finallabtres.animalogistics.MODELO.Noticia;
import com.finallabtres.animalogistics.MODELO.Refugio;
import com.finallabtres.animalogistics.R;
import com.finallabtres.animalogistics.UI.auth.login.LoginActivity;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.sql.Ref;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListarRefugioViewModel extends AndroidViewModel {

    private Context context;

    private MutableLiveData<MapaActual> MAMutable;
    private MutableLiveData<Marker> markerM;

    private MutableLiveData<String> errorM;

    private MutableLiveData<List<Refugio>> listaRefugioM;

    private MutableLiveData<Refugio> RefugioM;



    private GoogleMap googleMap;
    private Marker myMarker;

    public ListarRefugioViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();


    }

    public LiveData<List<Refugio>> getListaRefugioM() {
        if (listaRefugioM == null) {

            listaRefugioM = new MutableLiveData<>();
        }
        return listaRefugioM;

    }

    public LiveData<String> getErrorM() {
        if (errorM == null) {

            errorM = new MutableLiveData<>();
        }
        return errorM;

    }

    public LiveData<MapaActual> getMapaDeRefugios() {
        if (MAMutable == null) {
            MAMutable = new MutableLiveData<>();
        }
        return MAMutable;
    }

    public LiveData<Marker> getMarker() {
        if (markerM == null) {
            markerM = new MutableLiveData<>();
        }
        return markerM;
    }

    public void ObtenerMapa() {
        MapaActual MA = new MapaActual();
        MAMutable.setValue(MA);
    }

    public LiveData<Refugio> getPerfilRefugioM() {
        if (RefugioM == null) {
            RefugioM = new MutableLiveData<>();
        }
        return RefugioM;

    }
    public void cargarPerfilRefugio(Marker marker) {

        int posicion = parseInt(Objects.requireNonNull(marker.getTag()).toString());

        Refugio refugio = Objects.requireNonNull(listaRefugioM.getValue()).get(posicion);

        RefugioM.postValue(refugio);



    }

    public void setMarker(Marker value) {
        markerM.postValue(value);
    }

    public void CargarRefugios() {

        String token = API.LeerToken(context);

        API.ApiAnimalogistics API_A = API.getApi();


        Call<List<Refugio>> call = API_A.refugioLista(token);


        call.enqueue(new Callback<List<Refugio>>() {
            @Override
            public void onResponse(Call<List<Refugio>> call, Response<List<Refugio>> response) {

                if (response.isSuccessful()) {

                    listaRefugioM.postValue(response.body());
                }

                 else {

                    errorM.postValue("Hubo problemas al cargar los refugios");
                    Log.d("ERRORMORTAL", response.message());
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                errorM.postValue("Se produjo el siguiente fallo: " + t.getMessage());
                Log.d("ERRORMORTAL", t.getMessage());
            }
        });
    }



    public class MapaActual implements OnMapReadyCallback {


        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {

            googleMap.clear();

            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

            // seteo todos los marker


                        for (int i = 0; i < listaRefugioM.getValue().size(); i++) {

                            Refugio refugio = listaRefugioM.getValue().get(i);

                            LatLng  dire=new LatLng(refugio.getGpsx(),refugio.getGpsy());

                            myMarker = googleMap.addMarker(new MarkerOptions()
                                            .position(dire)
                                            .title(refugio.getNombre())
                                            .snippet(refugio.getDescripcion()));
                            myMarker.setTag(i);


                            CircleOptions circleOptions = new CircleOptions()
                                    .center(dire)
                                    .radius(refugio.getRango()) // Radio en metros
                                    .strokeColor(R.color.Perimetro) // Color del borde del círculo
                                    .fillColor(R.color.Area); // Color de relleno del círculo

                            googleMap.addCircle(circleOptions);

                            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                @Override
                                public boolean onMarkerClick(@NonNull Marker marker) {


                                        markerM.postValue(marker);


                                    return false;
                                }
                            });

                            CameraPosition cameraPosition = new CameraPosition.Builder()
                                    .target(dire)
                                    .zoom(19)
                                    .bearing(45)
                                    .tilt(70)
                                    .build();
                            CameraUpdate Cupdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
                            googleMap.animateCamera(Cupdate);

                        }




        }

    }



}

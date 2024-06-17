package com.finallabtres.animalogistics.UI.animal.gestion.agregarRefugio;

import static java.lang.Integer.parseInt;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.finallabtres.animalogistics.API.API;
import com.finallabtres.animalogistics.MODELO.Animal;
import com.finallabtres.animalogistics.MODELO.Refugio;
import com.finallabtres.animalogistics.R;
import com.finallabtres.animalogistics.UI.refugio.listar.ListarRefugioViewModel;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregarAnimalRefugioViewModel extends AndroidViewModel {
    private Context context;

    private MutableLiveData<MapaActual> MAMutable;
    private MutableLiveData<Marker> markerM;

    private MutableLiveData<String> errorM;

    private List<Animal> listaAnimalM = new ArrayList<>();

    private MutableLiveData<Animal> AnimalM;

    private MutableLiveData<Refugio> RefugioM;

 /*   String IdRefugio;*/

    private Marker myMarker;

    public AgregarAnimalRefugioViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();


    }

/*    public LiveData<List<Animal>> getListaAnimalM() {
        if (listaAnimalM == null) {

            listaAnimalM = new MutableLiveData<>();
        }
        return listaAnimalM;

    }*/

    public LiveData<String> getErrorM() {
        if (errorM == null) {

            errorM = new MutableLiveData<>();
        }
        return errorM;

    }

    public LiveData<MapaActual> getMapaDeAnimales() {
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

    public LiveData<Animal> getPerfilAnimalM() {
        if (AnimalM == null) {
            AnimalM = new MutableLiveData<>();
        }
        return AnimalM;

    }

    public LiveData<Refugio> getRefugioM() {
        if (RefugioM == null) {
            RefugioM = new MutableLiveData<>();
        }
        return RefugioM;

    }
    public void cargarPerfilAnimal(Marker marker) {

        int posicion = parseInt(Objects.requireNonNull(marker.getTag()).toString());

        Animal animal = Objects.requireNonNull(listaAnimalM.get(posicion));

        AnimalM.postValue(animal);



    }

    public void setMarker(Marker value) {
        markerM.postValue(value);
    }


    public void CargarDatos(Bundle bundle) {

        String IdRefugio = bundle.getString("refugioId");

        CargarAnimales();

        CargarRefugio(IdRefugio);


    }

    public void CargarRefugio(String IdRefugio) {


        String token = API.LeerToken(context);

        API.ApiAnimalogistics API_A = API.getApi();


        Call<Refugio> call = API_A.refugioPorId(token, parseInt(IdRefugio));


        call.enqueue(new Callback<Refugio>() {
            @Override
            public void onResponse(Call<Refugio> call, Response<Refugio> response) {

                if (response.isSuccessful()) {

                    RefugioM.postValue(response.body());
                }

                else {

                    errorM.postValue("Hubo problemas al cargar el refugio");
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

    public void CargarAnimales() {




        String token = API.LeerToken(context);

        API.ApiAnimalogistics API_A = API.getApi();


        Call<List<Animal>> call = API_A.animalListarSinRefugio(token);


        call.enqueue(new Callback<List<Animal>>() {
            @Override
            public void onResponse(Call<List<Animal>> call, Response<List<Animal>> response) {

                if (response.isSuccessful() && response.body() != null) {

                    listaAnimalM.addAll(response.body());
                }

                else {

                    errorM.postValue("Hubo problemas al cargar los ANIMALES");
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

    public void agregarAnimalRefugio(View view, Animal animal) {

        String token = API.LeerToken(context);

        API.ApiAnimalogistics API_A = API.getApi();


        Call<Animal> call = API_A.animalAgregarARefugio(token,animal.getId(), RefugioM.getValue().getId());


        call.enqueue(new Callback<Animal>() {
            @Override
            public void onResponse(Call<Animal> call, Response<Animal> response) {

                if (response.isSuccessful() && response.body() != null) {

                    Snackbar.make(view, "Animal Registrado Correctamente", Snackbar.LENGTH_LONG).show();
                    Navigation.findNavController(view).popBackStack(R.id.agregarAnimalRefugioFragment, true);
                }

                else {

                    errorM.postValue("Hubo problemas al registrar el animal en el refugio");
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


            for (int i = 0; i < listaAnimalM.size(); i++) {

                Animal animal = listaAnimalM.get(i);

                LatLng dire=new LatLng(animal.getGpsx(),animal.getGpsy());

                myMarker = googleMap.addMarker(new MarkerOptions()
                        .position(dire)
                        .title(animal.getNombre())
                        .snippet(animal.getComentarios()));
                myMarker.setTag(i);


/*
                googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(@NonNull Marker marker) {


                        markerM.postValue(marker);


                        return false;
                    }
                });*/



            }



            LatLng  dire=new LatLng(RefugioM.getValue().getGpsx(), RefugioM.getValue().getGpsy());


            Marker myMarkerRefugio = googleMap.addMarker(new MarkerOptions()
                    .position(dire)
                    .title(RefugioM.getValue().getNombre())
                    .snippet(RefugioM.getValue().getDescripcion())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            myMarkerRefugio.setTag(-1);


            myMarkerRefugio.showInfoWindow();

            CircleOptions circleOptions = new CircleOptions()
                    .center(dire)
                    .radius(RefugioM.getValue().getRango()) // Radio en metros
                    .strokeColor(R.color.Perimetro) // Color del borde del círculo
                    .fillColor(R.color.Area); // Color de relleno del círculo

            googleMap.addCircle(circleOptions);

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(dire)
                    .zoom(19)
                    .bearing(45)
                    .tilt(70)
                    .build();
            CameraUpdate Cupdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
            googleMap.animateCamera(Cupdate);

            /*googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    marker.showInfoWindow();
                    return true; // Retornar true para evitar la acción de clic predeterminada.
                }
            });*/
            googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    myMarkerRefugio.showInfoWindow();
                }
            });
            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    myMarkerRefugio.showInfoWindow();
                    if(marker.getTag().equals(-1)) {
                      // marker.showInfoWindow();
                        return false;
                    }else{
                        markerM.postValue(marker);
                        return true; // Retornar true para evitar la acción de clic predeterminada.
                    }

                   //


                }
            });

            googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                @Override
                public View getInfoWindow(Marker marker) {
                    // Return null to use the default frame for the InfoWindow.
                    return null;
                }
                @Override
                public View getInfoContents(Marker marker) {
                    // Inflate the custom InfoWindow layout.
                    View infoWindow = LayoutInflater.from(context).inflate(R.layout.inforefugio, null);

                    TextView title = infoWindow.findViewById(R.id.title);
                    TextView snippet = infoWindow.findViewById(R.id.snippet);

                    title.setText(marker.getTitle());
                    snippet.setText(marker.getSnippet());

                    return infoWindow;
                }
            });
        }



    }



}

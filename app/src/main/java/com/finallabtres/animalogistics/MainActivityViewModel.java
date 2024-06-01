package com.finallabtres.animalogistics;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.finallabtres.animalogistics.API.API;
import com.finallabtres.animalogistics.MODELO.Noticia;
import com.finallabtres.animalogistics.MODELO.Refugio;
import com.finallabtres.animalogistics.MODELO.Usuario;

import org.json.JSONObject;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends AndroidViewModel {

    private Context context;

    private MutableLiveData<List<Refugio>> listaRefugioM;

    private MutableLiveData<List<Refugio>> listaRefugioVoluntarioM;

    private MutableLiveData<Usuario> UsuarioM;

    private MutableLiveData<String> errorM;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context = application;
    }


    public LiveData<List<Refugio>> getListaRefugiosM(){
        if(listaRefugioM==null){

            listaRefugioM=new MutableLiveData<>();
        }
        return listaRefugioM;

    }
    public LiveData<List<Refugio>> getListaRefugiosVoluntarioM(){
        if(listaRefugioVoluntarioM==null){

            listaRefugioVoluntarioM=new MutableLiveData<>();
        }
        return listaRefugioVoluntarioM;

    }

    public LiveData<Usuario> getUsuarioM(){
        if(UsuarioM==null){

            UsuarioM=new MutableLiveData<>();
        }
        return UsuarioM;

    }
    public LiveData<String> getErrorM(){
        if(errorM==null){

            errorM=new MutableLiveData<>();
        }
        return errorM;

    }

    public void cargarDatosNavSide() {

        cargarDataRefugios();

        cargarDataRefugiosVoluntario();

    }



    public void cargarDataRefugios() {
        String token = API.LeerToken(context);

        API.ApiAnimalogistics API_A = API.getApi();


        Call<List<Refugio>> call = API_A.refugioObtenerPorDueno(token);


        call.enqueue(new Callback<List<Refugio>>() {
            @Override
            public void onResponse(Call<List<Refugio>> call, Response<List<Refugio>> response) {

                if(response.isSuccessful()){

                    listaRefugioM.postValue(response.body());

                }else{

                    if (response.code() == 404) {

                            errorM.postValue("Hubo problemas al cargar los refugios");
                        }

                }


            }

            @Override
            public void onFailure(Call call, Throwable t) {
                errorM.postValue("Se produjo el siguiente fallo: " + t.getMessage());
                Log.d("ERRORMORTAL", t.getMessage());
            }
        });
    }

    public void cargarDataRefugiosVoluntario() {
        String token = API.LeerToken(context);

        API.ApiAnimalogistics API_A = API.getApi();


        Call<List<Refugio>> call = API_A.refugioObtenerPorVoluntario(token);


        call.enqueue(new Callback<List<Refugio>>() {
            @Override
            public void onResponse(Call<List<Refugio>> call, Response<List<Refugio>> response) {

                if(response.isSuccessful()){

                    listaRefugioVoluntarioM.postValue(response.body());

                }else{

                    if (response.code() == 404) {
                        try {

                            JSONObject errorObj = new JSONObject(response.errorBody().string());
                            String errorMessage = errorObj.getString("mensaje");


                            errorM.postValue(errorMessage);
                        } catch (Exception e) {

                            errorM.postValue("Hubo problemas al cargar los refugios");
                        }
                    } else {
                        errorM.postValue("Hubo problemas al cargar los refugios");
                    }
                }


            }

            @Override
            public void onFailure(Call call, Throwable t) {
                errorM.postValue("Se produjo el siguiente fallo: " + t.getMessage());
                Log.d("ERRORMORTAL", t.getMessage());
            }
        });
    }

    public void cargarDatosUsuario() {

        String token = API.LeerToken(context);

        API.ApiAnimalogistics API_A = API.getApi();


        Call<Usuario> call = API_A.usuarioObtenerPerfil(token);


        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                if(response.isSuccessful()){

                    UsuarioM.postValue(response.body());

                }else{

                    errorM.postValue("Hubo problemas al cargar el perfil");
                }


            }

            @Override
            public void onFailure(Call call, Throwable t) {
                errorM.postValue("Se produjo el siguiente fallo: " + t.getMessage());
                Log.d("ERRORMORTAL", t.getMessage());
            }
        });


    }
}

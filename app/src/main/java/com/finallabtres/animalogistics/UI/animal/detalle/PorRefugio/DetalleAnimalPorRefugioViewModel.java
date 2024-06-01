package com.finallabtres.animalogistics.UI.animal.detalle.PorRefugio;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.finallabtres.animalogistics.API.API;
import com.finallabtres.animalogistics.MODELO.Animal;
import com.finallabtres.animalogistics.MODELO.Tarea;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleAnimalPorRefugioViewModel extends AndroidViewModel {

    private Context context;

    private MutableLiveData<String> errorM;

    private MutableLiveData<List<Animal>> listaAnimalesDisponiblesParaAdoptarM;

    public DetalleAnimalPorRefugioViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }


    public LiveData<String> getErrorM(){
        if(errorM==null){

            errorM=new MutableLiveData<>();
        }
        return errorM;

    }

    public LiveData<List<Animal>>getlistaAnimalesDisponiblesParaAdoptarM(){
        if(listaAnimalesDisponiblesParaAdoptarM==null){

            listaAnimalesDisponiblesParaAdoptarM=new MutableLiveData<>();
        }
        return listaAnimalesDisponiblesParaAdoptarM;

    }

    public void cargarAnimalesParaAdoptar(Bundle bundle) {
        String refugioId = bundle.getString("IdRefugio");

        String token = API.LeerToken(context);

        API.ApiAnimalogistics API_A = API.getApi();



        Call<List<Animal>> call = API_A.listarAnimalesDisponiblesParaAdoptarPorRefugio(token, Integer.parseInt(refugioId));

        call.enqueue(new Callback<List<Animal>>() {
            @Override
            public void onResponse(Call<List<Animal>> call, Response<List<Animal>> response) {

                if(response.isSuccessful()){

                    listaAnimalesDisponiblesParaAdoptarM.postValue(response.body());

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
}
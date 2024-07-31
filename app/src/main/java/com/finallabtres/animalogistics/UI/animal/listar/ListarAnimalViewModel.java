package com.finallabtres.animalogistics.UI.animal.listar;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.finallabtres.animalogistics.API.API;
import com.finallabtres.animalogistics.MODELO.Animal;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListarAnimalViewModel extends AndroidViewModel {
    private Context context;

    private MutableLiveData<String> errorM;

    private MutableLiveData<List<Animal>> listaAnimalM;


    public ListarAnimalViewModel(@NonNull Application application) {
        super(application);
        this.context=application.getApplicationContext();
    }




    public LiveData<List<Animal>> getListaAnimalesM(){
        if(listaAnimalM==null){

            listaAnimalM=new MutableLiveData<>();
        }
        return listaAnimalM;

    }

    public LiveData<String> getErrorM(){
        if(errorM==null){

            errorM=new MutableLiveData<>();
        }
        return errorM;

    }


    public void cargarDatos() {

        String token = API.LeerToken(context);

        API.ApiAnimalogistics API_A = API.getApi();


        Call<List<Animal>> call = API_A.animalListarPorUsuario(token);


        call.enqueue(new Callback<List<Animal>>() {
            @Override
            public void onResponse(Call<List<Animal>> call, Response<List<Animal>> response) {

                if(response.isSuccessful()){

                    listaAnimalM.postValue(response.body());


                }else{

                    errorM.postValue("Hubo problemas al cargar las noticias");
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

package com.finallabtres.animalogistics.UI.noticia.listar;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.finallabtres.animalogistics.API.API;
import com.finallabtres.animalogistics.MODELO.Noticia;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListarNoticiaViewModel extends AndroidViewModel {


    private Context context;

    private MutableLiveData<List<Noticia>> listaNoticiaM;
    private MutableLiveData<String> errorM;

    public ListarNoticiaViewModel(@NonNull Application application) {
        super(application);
        this.context=application.getApplicationContext();
    }

    public LiveData<List<Noticia>> getListaNoticiasM(){
        if(listaNoticiaM==null){

            listaNoticiaM=new MutableLiveData<>();
        }
        return listaNoticiaM;

    }

    public LiveData<String> getErrorM(){
        if(errorM==null){

            errorM=new MutableLiveData<>();
        }
        return errorM;

    }


    public void cargarNoticias() {

        String token = API.LeerToken(context);

        API.ApiAnimalogistics API_A = API.getApi();


        Call<List<Noticia>> call = API_A.noticiaLista(token);


            call.enqueue(new Callback<List<Noticia>>() {
                @Override
                public void onResponse(Call<List<Noticia>> call, Response<List<Noticia>> response) {

                    if(response.isSuccessful()){

                        listaNoticiaM.postValue(response.body());
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






























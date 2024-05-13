package com.finallabtres.animalogistics.UI.noticia.listar.porRefugio;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.finallabtres.animalogistics.API.API;
import com.finallabtres.animalogistics.MODELO.Noticia;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListarNoticiaPorRefugioViewModel extends AndroidViewModel {


    private Context context;
    private MutableLiveData<List<Noticia>> listaNoticiaM;
    private MutableLiveData<String> errorM;
    private MutableLiveData<Set<String>> listaCategoriasM;

    public ListarNoticiaPorRefugioViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }
    public LiveData<List<Noticia>> getListaNoticiasM(){
        if(listaNoticiaM==null){

            listaNoticiaM=new MutableLiveData<>();
        }
        return listaNoticiaM;

    }
    public LiveData<Set<String>> getListaCategoriasM(){
        if(listaCategoriasM==null){

            listaCategoriasM=new MutableLiveData<>();
        }
        return listaCategoriasM;

    }

    public LiveData<String> getErrorM(){
        if(errorM==null){

            errorM=new MutableLiveData<>();
        }
        return errorM;

    }


    public void cargarDatos(Bundle bundle) {

        String refugioId = bundle.getString("IdRefugio");

        String token = API.LeerToken(context);

        API.ApiAnimalogistics API_A = API.getApi();


        Call<List<Noticia>> call = API_A.noticiaListarPorRefugio(token,refugioId);


        call.enqueue(new Callback<List<Noticia>>() {
            @Override
            public void onResponse(Call<List<Noticia>> call, Response<List<Noticia>> response) {

                if(response.isSuccessful()){

                    listaNoticiaM.postValue(response.body());

                    List<Noticia> noticias = response.body();


                    Set<String> categorias = new HashSet<>();

                    for (Noticia noticia : noticias) {
                        categorias.add(noticia.getCategoria());
                    }
                    listaCategoriasM.postValue(categorias);

                }else{

                    errorM.postValue("Hubo problemas al cargar las noticias");
                    Log.d("ERRORMORTAL", response.toString());
                }


            }

            @Override
            public void onFailure(Call call, Throwable t) {
                errorM.postValue("Se produjo el siguiente fallo: " + t.getMessage());
                Log.d("ERRORMORTAL", t.getMessage());
            }
        });





    }


    public void cargarNoticiasPorCategorias(String categoria, Bundle bundle) {

        String refugioId = bundle.getString("IdRefugio");

        String token = API.LeerToken(context);

        API.ApiAnimalogistics API_A = API.getApi();


        Call<List<Noticia>> call = API_A.noticiaListarPorRefugioPorCategoria(token, categoria, refugioId);


        call.enqueue(new Callback<List<Noticia>>() {
            @Override
            public void onResponse(Call<List<Noticia>> call, Response<List<Noticia>> response) {

                if(response.isSuccessful()){

                    listaNoticiaM.postValue(response.body());


                }else{

                    errorM.postValue("Hubo problemas al cargar las noticias");
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
}


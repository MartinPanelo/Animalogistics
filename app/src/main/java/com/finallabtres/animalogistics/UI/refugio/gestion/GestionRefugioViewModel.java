package com.finallabtres.animalogistics.UI.refugio.gestion;

import static java.lang.Integer.parseInt;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.finallabtres.animalogistics.API.API;
import com.finallabtres.animalogistics.MODELO.Animal;
import com.finallabtres.animalogistics.MODELO.Noticia;
import com.finallabtres.animalogistics.MODELO.Refugio;
import com.finallabtres.animalogistics.MODELO.Tarea;
import com.finallabtres.animalogistics.R;

import org.json.JSONObject;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GestionRefugioViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<List<Tarea>> listaTareasM;
    private MutableLiveData<List<Noticia>> listaNoticiasM;
    private MutableLiveData<List<Animal>> listaAnimalesM;

    private MutableLiveData<String> errorM;

    private MutableLiveData<Boolean> TipoDeVistaM;

    public GestionRefugioViewModel(@NonNull Application application) {
        super(application);
        this.context=application.getApplicationContext();
    }

    public LiveData<List<Tarea>> getlistaTareasM(){
        if(listaTareasM==null){

            listaTareasM=new MutableLiveData<>();
        }
        return listaTareasM;

    }
    public LiveData<List<Noticia>> getlistaNoticiasM(){
        if(listaNoticiasM==null){

            listaNoticiasM=new MutableLiveData<>();
        }
        return listaNoticiasM;

    }
    public LiveData<List<Animal>> getlistaAnimalesM(){
        if(listaAnimalesM==null){

            listaAnimalesM=new MutableLiveData<>();
        }
        return listaAnimalesM;

    }

    public LiveData<String> getErrorM(){
        if(errorM==null){

            errorM=new MutableLiveData<>();
        }
        return errorM;

    }
    public LiveData<Boolean> getTipoDeVistaM(){
        if(TipoDeVistaM==null){

            TipoDeVistaM=new MutableLiveData<>();
        }
        return TipoDeVistaM;

    }
    public void cargarDatosDeVoluntariados(Bundle bundle) {

        TipoDeVistaM.postValue(bundle.getBoolean("TipoDeVista"));


        String IdRefugio = bundle.getString("refugioId");



        String token = API.LeerToken(context);

        API.ApiAnimalogistics API_A = API.getApi();



        Call<List<Tarea>> call = API_A.listarTareasDeUnRefugio(token,IdRefugio);

        call.enqueue(new Callback<List<Tarea>>() {
            @Override
            public void onResponse(Call<List<Tarea>> call, Response<List<Tarea>> response) {

                if(response.isSuccessful()){



                    listaTareasM.postValue(response.body());

                }else{

                    if (response.code() == 404) {
                        try {

                            JSONObject errorObj = new JSONObject(response.errorBody().string());
                            String errorMessage = errorObj.getString("mensaje");


                            errorM.postValue(errorMessage);
                        } catch (Exception e) {

                            errorM.postValue("Hubo problemas al cargar las tareas");
                        }
                    } else {
                        errorM.postValue("Hubo problemas al cargar las tareas");
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
    public void cargarDatosDeNoticias(Bundle bundle) {
        String IdRefugio = bundle.getString("refugioId");

        String token = API.LeerToken(context);

        API.ApiAnimalogistics API_A = API.getApi();



        Call<List<Noticia>> call = API_A.noticiaListarPorRefugio(token,IdRefugio);

        call.enqueue(new Callback<List<Noticia>>() {
            @Override
            public void onResponse(Call<List<Noticia>> call, Response<List<Noticia>> response) {

                if(response.isSuccessful()){



                    listaNoticiasM.postValue(response.body());

                }else{

                    if (response.code() == 404) {
                        try {

                            JSONObject errorObj = new JSONObject(response.errorBody().string());
                            String errorMessage = errorObj.getString("mensaje");


                            errorM.postValue(errorMessage);
                        } catch (Exception e) {

                            errorM.postValue("Hubo problemas al cargar las noticias");
                        }
                    } else {
                        errorM.postValue("Hubo problemas al cargar las noticias");
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

    public void cargarDatosDeAnimales(Bundle bundle) {
        String IdRefugio = bundle.getString("refugioId");

        String token = API.LeerToken(context);

        API.ApiAnimalogistics API_A = API.getApi();



        Call<List<Animal>> call = API_A.animalListarPorRefugio(token,IdRefugio);

        call.enqueue(new Callback<List<Animal>>() {
            @Override
            public void onResponse(Call<List<Animal>> call, Response<List<Animal>> response) {

                if(response.isSuccessful()){



                    listaAnimalesM.postValue(response.body());

                }else{

                    if (response.code() == 404) {
                        try {

                            JSONObject errorObj = new JSONObject(response.errorBody().string());
                            String errorMessage = errorObj.getString("mensaje");


                            errorM.postValue(errorMessage);
                        } catch (Exception e) {

                            errorM.postValue("Hubo problemas al cargar las tareas");
                        }
                    } else {
                        errorM.postValue("Hubo problemas al cargar las tareas");
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

    public void editarRefugio(Bundle bundle, View view) {


        String IdRefugio = bundle.getString("refugioId");

        String token = API.LeerToken(context);

        API.ApiAnimalogistics API_A = API.getApi();


        Call<Refugio> call = API_A.refugioPorId(token, parseInt(IdRefugio));


        call.enqueue(new Callback<Refugio>() {
            @Override
            public void onResponse(Call<Refugio> call, Response<Refugio> response) {

                if (response.isSuccessful()) {

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("ItemRefugio", response.body());

                    Navigation.findNavController(view).navigate(R.id.crearRefugioFragment,bundle);

                }

                else {

                    errorM.postValue("No puede editar un refugio que no existe o que no le pertenece");
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
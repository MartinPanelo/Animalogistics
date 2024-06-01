package com.finallabtres.animalogistics.UI.refugio.voluntario.listar;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.finallabtres.animalogistics.API.API;
import com.finallabtres.animalogistics.MODELO.Noticia;
import com.finallabtres.animalogistics.MODELO.Refugio;
import com.finallabtres.animalogistics.MODELO.Tarea;
import com.finallabtres.animalogistics.MODELO.Voluntario;
import com.finallabtres.animalogistics.R;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListarVoluntariadosDisponiblesViewModel extends AndroidViewModel {

    private Context context;

    private Refugio refugio;
/*    private MutableLiveData<Refugio> refugioM;*/

    private MutableLiveData<List<Tarea>> listaTareasDisponibleM;

    private MutableLiveData<Tarea> TareaDisponibleM;
    private MutableLiveData<String> errorM;

/*    private MutableLiveData<Tarea> TareaElegidaM;*/

    public ListarVoluntariadosDisponiblesViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();


    }

    public LiveData<List<Tarea>> getlistaTareasDisponibleM(){
        if(listaTareasDisponibleM==null){

            listaTareasDisponibleM=new MutableLiveData<>();
        }
        return listaTareasDisponibleM;

    }
    public LiveData<Tarea> getTareaDisponibleM(){
        if(TareaDisponibleM==null){

            TareaDisponibleM=new MutableLiveData<>();
        }
        return TareaDisponibleM;

    }
/*    public LiveData<Tarea> getTareaElegidaM(){
        if(TareaElegidaM==null){

            TareaElegidaM=new MutableLiveData<>();
        }
        return TareaElegidaM;

    }*/

    public LiveData<String> getErrorM(){
        if(errorM==null){

            errorM=new MutableLiveData<>();
        }
        return errorM;

    }
    public void cargarTareasDisponibles(Bundle bundle) {

        String refugioId = bundle.getString("IdRefugio");

        String token = API.LeerToken(context);

        API.ApiAnimalogistics API_A = API.getApi();





        Call<List<Tarea>> call = API_A.listarTareasDisponbilesDeUnRefugio(token,refugioId);

        call.enqueue(new Callback<List<Tarea>>() {
            @Override
            public void onResponse(Call<List<Tarea>> call, Response<List<Tarea>> response) {

                if(response.isSuccessful()){

                    listaTareasDisponibleM.postValue(response.body());

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

    public void mostrarTarea(int itemAtPosition) {

        TareaDisponibleM.postValue(listaTareasDisponibleM.getValue().get(itemAtPosition));


    }

    public void inscribirse(View view, int selectedPosition) {

     //   aca llega el id de la tarea al que el usuario va a inscribirse

    /*    Tarea tarea = listaTareasDisponibleM.getValue().get(selectedPosition);
        TareaElegidaM.postValue(tarea);*/

        Tarea tarea = listaTareasDisponibleM.getValue().get(selectedPosition);


        String token = API.LeerToken(context);

        API.ApiAnimalogistics API_A = API.getApi();


        Call<Voluntario> call = API_A.anotarseComoVoluntario(token, String.valueOf(tarea.getId()));


        call.enqueue(new Callback<Voluntario>() {
            @Override
            public void onResponse(Call<Voluntario> call, Response<Voluntario> response) {

                if(response.isSuccessful()){

                    Navigation.findNavController(view).popBackStack(R.id.detalleRefugioFragment, false);

                   /* Voluntario voluntario = response.body();

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("itemrefugio", voluntario.getRefugio());
                    Navigation.findNavController(view).navigate(R.id.detalleRefugioFragment, bundle);*/


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
}


























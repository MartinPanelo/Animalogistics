package com.finallabtres.animalogistics.UI.tarea;

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
import com.finallabtres.animalogistics.MODELO.Refugio;
import com.finallabtres.animalogistics.MODELO.Tarea;
import com.finallabtres.animalogistics.R;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListarTareasDisponiblesPorRefugioViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<List<Tarea>> listaTareaDisponibleM;

    private MutableLiveData<Tarea> TareaDisponibleM;
    private MutableLiveData<String> errorM;


    public ListarTareasDisponiblesPorRefugioViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();


    }

    public LiveData<List<Tarea>> getlistaTareasDisponibleM(){
        if(listaTareaDisponibleM==null){

            listaTareaDisponibleM=new MutableLiveData<>();
        }
        return listaTareaDisponibleM;

    }
    public LiveData<Tarea> getTareaDisponibleM(){
        if(TareaDisponibleM==null){

            TareaDisponibleM=new MutableLiveData<>();
        }
        return TareaDisponibleM;

    }


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





        Call<List<Tarea>> call = API_A.listarTareasDisponiblesDeUnRefugio(token,refugioId);

        call.enqueue(new Callback<List<Tarea>>() {
            @Override
            public void onResponse(Call<List<Tarea>> call, Response<List<Tarea>> response) {

                if(response.isSuccessful()){

                    listaTareaDisponibleM.postValue(response.body());

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

    public void mostrarTarea(int itemAtPosition) {

        TareaDisponibleM.postValue(listaTareaDisponibleM.getValue().get(itemAtPosition));


    }

    public void inscribirse(View view, int selectedPosition) {

        //   aca llega el id de la tarea al que el usuario va a inscribirse

    /*    Tarea tarea = listaTareasDisponibleM.getValue().get(selectedPosition);
        TareaElegidaM.postValue(tarea);*/

        Tarea tarea = listaTareaDisponibleM.getValue().get(selectedPosition);


        String token = API.LeerToken(context);

        API.ApiAnimalogistics API_A = API.getApi();


        Call<Tarea> call = API_A.anotarseAUnaTarea(token, tarea.getId());


        call.enqueue(new Callback<Tarea>() {
            @Override
            public void onResponse(Call<Tarea> call, Response<Tarea> response) {

                if(response.isSuccessful()){

                    Navigation.findNavController(view).popBackStack(R.id.detalleRefugioFragment, true);


                    Tarea tarea = response.body();

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("itemrefugio", tarea.getRefugio());
                    Navigation.findNavController(view).navigate(R.id.detalleRefugioFragment, bundle);



                }else{

                    if (response.code() == 404) {

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
}

























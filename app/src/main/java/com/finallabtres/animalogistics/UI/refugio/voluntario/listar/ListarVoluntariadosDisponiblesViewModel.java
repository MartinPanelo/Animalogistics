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

    private MutableLiveData<List<Voluntario>> listaVoluntarioDisponibleM;

    private MutableLiveData<Voluntario> VoluntarioDisponibleM;
    private MutableLiveData<String> errorM;

/*    private MutableLiveData<Tarea> TareaElegidaM;*/

    public ListarVoluntariadosDisponiblesViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();


    }

    public LiveData<List<Voluntario>> getlistaVoluntariosDisponibleM(){
        if(listaVoluntarioDisponibleM==null){

            listaVoluntarioDisponibleM=new MutableLiveData<>();
        }
        return listaVoluntarioDisponibleM;

    }
    public LiveData<Voluntario> getVoluntarioDisponibleM(){
        if(VoluntarioDisponibleM==null){

            VoluntarioDisponibleM=new MutableLiveData<>();
        }
        return VoluntarioDisponibleM;

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





        Call<List<Voluntario>> call = API_A.listarVoluntariadosDisponbilesDeUnRefugio(token,refugioId);

        call.enqueue(new Callback<List<Voluntario>>() {
            @Override
            public void onResponse(Call<List<Voluntario>> call, Response<List<Voluntario>> response) {

                if(response.isSuccessful()){



                    listaVoluntarioDisponibleM.postValue(response.body());

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

        VoluntarioDisponibleM.postValue(listaVoluntarioDisponibleM.getValue().get(itemAtPosition));


    }

    public void inscribirse(View view, int selectedPosition) {

     //   aca llega el id de la tarea al que el usuario va a inscribirse

    /*    Tarea tarea = listaTareasDisponibleM.getValue().get(selectedPosition);
        TareaElegidaM.postValue(tarea);*/

        Voluntario voluntario = listaVoluntarioDisponibleM.getValue().get(selectedPosition);


        String token = API.LeerToken(context);

        API.ApiAnimalogistics API_A = API.getApi();


        Call<Voluntario> call = API_A.anotarseComoVoluntario(token, voluntario.getId());


        call.enqueue(new Callback<Voluntario>() {
            @Override
            public void onResponse(Call<Voluntario> call, Response<Voluntario> response) {

                if(response.isSuccessful()){

                    Navigation.findNavController(view).popBackStack(R.id.detalleRefugioFragment, true);


                    Voluntario voluntario = response.body();

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("itemrefugio", voluntario.getRefugio());
                    Navigation.findNavController(view).navigate(R.id.detalleRefugioFragment, bundle);



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


























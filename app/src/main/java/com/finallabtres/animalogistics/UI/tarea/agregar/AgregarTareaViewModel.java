package com.finallabtres.animalogistics.UI.tarea.agregar;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.finallabtres.animalogistics.API.API;
import com.finallabtres.animalogistics.MODELO.Noticia;
import com.finallabtres.animalogistics.MODELO.Tarea;
import com.finallabtres.animalogistics.MODELO.ToastUtils;
import com.finallabtres.animalogistics.MODELO.Usuario;
import com.finallabtres.animalogistics.R;
import com.finallabtres.animalogistics.UI.auth.login.LoginActivity;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregarTareaViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<String> errorM;
    private int idRefugio;

    private MutableLiveData<Boolean> operationSuccessful;




    public AgregarTareaViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<Boolean> getOperationSuccessful(){
        if(operationSuccessful==null){
            operationSuccessful=new MutableLiveData<>();
        }
        return operationSuccessful;
    }

    public LiveData<String> getErrorM(){
        if(errorM==null){

            errorM=new MutableLiveData<>();
        }
        return errorM;

    }
    public void AgregarTarea(String actividad, String descripcion) {

        RequestBody RefugioId = RequestBody.create(MediaType.parse("application/json"), String.valueOf(idRefugio));
        RequestBody Actividad = RequestBody.create(MediaType.parse("application/json"), actividad);
        RequestBody Descripcion = RequestBody.create(MediaType.parse("application/json"), descripcion);

        String token = API.LeerToken(context);

        API.ApiAnimalogistics API_A = API.getApi();

        Call<Tarea> call = API_A.crearTarea(token,RefugioId, Actividad, Descripcion);

        call.enqueue(new Callback<Tarea>() {
            @Override
            public void onResponse(Call<Tarea> call, Response<Tarea> response) {
                if(response.isSuccessful()){


                    ToastUtils.showToast(context, context.getString(R.string.Operacion_exitosa), R.color.toast_success,R.drawable.check);
                    operationSuccessful.postValue(true);




                }else{

                    errorM.postValue( context.getString(R.string.no_tiene_permisos));

                }

            }

            @Override
            public void onFailure(Call<Tarea> call, Throwable t) {
                Toast.makeText(context,t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    public void setIdRefugioTarea(Bundle bundle) {

        idRefugio = Integer.parseInt( bundle.getString("refugioId") );
    }
}





















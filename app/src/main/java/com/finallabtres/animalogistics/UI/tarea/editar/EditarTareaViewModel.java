package com.finallabtres.animalogistics.UI.tarea.editar;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.finallabtres.animalogistics.API.API;
import com.finallabtres.animalogistics.MODELO.Tarea;
import com.finallabtres.animalogistics.MODELO.ToastUtils;
import com.finallabtres.animalogistics.MODELO.Usuario;
import com.finallabtres.animalogistics.R;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditarTareaViewModel extends AndroidViewModel {

    private Context context;

    private MutableLiveData<Tarea> TareaM;

    private MutableLiveData<Boolean>  CheckBoxM;

    private MutableLiveData<String> errorM;

    private MutableLiveData<Boolean> EditFormM;

    public EditarTareaViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<Tarea> getTareaM(){
        if(TareaM==null){

            TareaM=new MutableLiveData<>();
        }
        return TareaM;

    }

    public LiveData<Boolean> getEditFormM(){
        if(EditFormM==null){

            EditFormM=new MutableLiveData<>();
        }
        return EditFormM;

    }

    public LiveData<Boolean> setCheckBoxM(){
        if(CheckBoxM==null){

            CheckBoxM=new MutableLiveData<>();
        }
        return CheckBoxM;

    }
    public LiveData<String> getErrorM(){
        if(errorM==null){

            errorM=new MutableLiveData<>();
        }
        return errorM;

    }
    public void cargarTarea(Bundle bundle) {


        // controlo la posible edicion del formulario tarea

        boolean TipoDeVista = bundle.getBoolean("TipoDeVista", false);

        EditFormM.postValue(TipoDeVista);


        Tarea tarea = bundle.getSerializable("ObjTarea", Tarea.class);
        TareaM.postValue(tarea);


    }

    public void actualizarTarea(String actividad, String descripcion, boolean liberar) {

        String token = API.LeerToken(context);
        API.ApiAnimalogistics API_A = API.getApi();

        Tarea tarea = new Tarea();

        tarea.setId(TareaM.getValue().getId());
        tarea.setActividad(actividad);
        tarea.setDescripcion(descripcion);
        if(!liberar && TareaM.getValue().getUsuario() != null){
            tarea.setUsuario(TareaM.getValue().getUsuario());
        }






        Call call = API_A.tareaEditar(token, tarea);


        call.enqueue(new Callback<Tarea>() {
            @Override
            public void onResponse(Call<Tarea> call, Response<Tarea> response) {
                if(response.isSuccessful()){


                    TareaM.postValue(response.body());

                    ToastUtils.showToast(context, context.getString(R.string.se_actualizo_la_tarea), R.color.toast_success,R.drawable.check);

                }else{

                    try {
                        String errorResponse = response.errorBody().string();
                        String errorMessage = "";

                        try {
                            JSONObject jsonObject = new JSONObject(errorResponse);
                            if (jsonObject.has("errors")) {
                                JSONObject errors = jsonObject.getJSONObject("errors");
                                if (errors.has("mensaje")) {
                                    errorMessage = errors.getJSONArray("mensaje").getString(0);
                                    errorM.postValue(errorMessage);
                                }
                            }

                        } catch (JSONException e) {
                            // Si no se pudo analizar como JSON, el mensaje de error es la respuesta tal cual
                            errorMessage = errorResponse;
                            // NO HAY ERROR En LOS DATOS PERO EL SERVIDOR TUVO UN ERROR

                            Toast.makeText(context, errorResponse, Toast.LENGTH_LONG).show();

                        }


                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }

            }

            @Override
            public void onFailure(Call<Tarea> call, Throwable t) {
                Toast.makeText(context,t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
// indiferentemente si fallo o no el checkBox se tiene que setear a false
        CheckBoxM.postValue(false);

    }
}
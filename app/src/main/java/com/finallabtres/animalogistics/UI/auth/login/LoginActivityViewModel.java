package com.finallabtres.animalogistics.UI.auth.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.finallabtres.animalogistics.API.API;
import com.finallabtres.animalogistics.MainActivity;
import com.finallabtres.animalogistics.UI.auth.registro.RegistroActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityViewModel extends AndroidViewModel{

        private Context context;

    private MutableLiveData<Boolean> CorreoM;
    private MutableLiveData<Boolean> ContrasenaM;

        public LoginActivityViewModel(@NonNull Application application) {
            super(application);
            this.context = application;
        }



    public LiveData<Boolean> getCorreoM(){
        if(CorreoM == null){
            CorreoM = new MutableLiveData<>();
        }
        return CorreoM;
    }

    public LiveData<Boolean> getContrasenaM(){
        if(ContrasenaM == null){
            ContrasenaM = new MutableLiveData<>();
        }
        return ContrasenaM;
    }



        public void registrar() {

            Intent intent = new Intent(context, RegistroActivity.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(intent);
        }

    public void ingresar(String correo, String contrasena) {

        API.ApiAnimalogistics API_A = API.getApi();

        Call<String> call = API_A.Login(correo,contrasena);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){



                    String tokenParaGuardar = "Bearer " + response.body();

                    API.GuardarToken(context, tokenParaGuardar);

                    Intent intent = new Intent(context, MainActivity.class);

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }else{

                    try {
                        String errorResponse = response.errorBody().string();
                        String errorMessage = "";

                        try {
                            JSONObject jsonObject = new JSONObject(errorResponse);
                            if (jsonObject.has("errors")) {
                                JSONObject errors = jsonObject.getJSONObject("errors");
                                if (errors.has("Correo")) {
                                    errorMessage = errors.getJSONArray("Correo").getString(0);

                                    CorreoM.postValue(false);

                                }else{
                                    CorreoM.postValue(true);

                                }

                                if (errors.has("Contrasena")) {
                                    errorMessage = errors.getJSONArray("Contrasena").getString(0);
                                    ContrasenaM.postValue(false);
                                }else{

                                    ContrasenaM.postValue(true);
                                }
                            }

                        } catch (JSONException e) {
                            // Si no se pudo analizar como JSON, el mensaje de error es la respuesta tal cual
                            errorMessage = errorResponse;
                            // NO HAY ERROR PERO LOS DATOS NO COINCIDEN CON LOS DE UN USUARIO
                            CorreoM.postValue(true);
                            ContrasenaM.postValue(true);
                            Toast.makeText(context, errorResponse, Toast.LENGTH_LONG).show();

                        }


                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(context,t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });





    }
}

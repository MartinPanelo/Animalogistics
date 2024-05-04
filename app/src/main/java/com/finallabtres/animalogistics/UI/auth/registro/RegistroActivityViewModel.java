package com.finallabtres.animalogistics.UI.auth.registro;

import static android.app.Activity.RESULT_OK;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.finallabtres.animalogistics.API.API;
import com.finallabtres.animalogistics.MODELO.Usuario;
import com.finallabtres.animalogistics.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroActivityViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Bitmap> foto;
    private MutableLiveData<Boolean> CorreoM;
    private MutableLiveData<Boolean> ContrasenaM;
    private MutableLiveData<Boolean> NombreM;
    private MutableLiveData<Boolean> ApellidoM;
    private MutableLiveData<Boolean> TelefonoM;
    private MutableLiveData<Boolean> DNIM;


    public RegistroActivityViewModel(@NonNull Application application) {
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
    public LiveData<Boolean> getNombreM(){
        if(NombreM == null){
            NombreM = new MutableLiveData<>();
        }
        return NombreM;
    }
    public LiveData<Boolean> getApellidoM(){
        if(ApellidoM == null){
            ApellidoM = new MutableLiveData<>();
        }
        return ApellidoM;
    }
    public LiveData<Boolean> getTelefonoM(){
        if(TelefonoM == null){
            TelefonoM = new MutableLiveData<>();
        }
        return TelefonoM;
    }
    public LiveData<Boolean> getDNIM(){
        if(DNIM == null){
            DNIM = new MutableLiveData<>();
        }
        return DNIM;
    }

    public LiveData<Bitmap> getFoto() {
        if (foto == null) {
            foto = new MutableLiveData<>();
        }
        return foto;
    }

    public void respuetaDeCamara(ActivityResult result) {
        // Log.d("salida",requestCode+"");



        if (result.getResultCode() == RESULT_OK) {
            //Recupero los datos provenientes de la camara.
            Bundle extras = result.getData().getExtras();
            //Casteo a bitmap lo obtenido de la camara.
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            //Rutina para optimizar la foto,
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            foto.setValue(imageBitmap);

/*

            //Rutina para convertir a un arreglo de byte los datos de la imagen
            byte[] b = baos.toByteArray();


            //Aquí podría ir la rutina para llamar al servicio que recibe los bytes.

            String NombreFoto = "IMG" + ".png";

            File archivo = new File(context.getFilesDir(), NombreFoto);


            if (archivo.exists()) {
                archivo.delete();
            }
            try {
                FileOutputStream fo = new FileOutputStream(archivo);
                BufferedOutputStream bo = new BufferedOutputStream(fo);
                // ObjectOutputStream OOS = new ObjectOutputStream(bo);
                bo.write(b);
                bo.flush();
                bo.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
*/

        }
    }


    public void RegistrarUsuario(View view, String correo, String contrasena, String nombre, String apellido, String telefono, String dni, ImageView IMG) {



        if (correo.isEmpty()) {
            CorreoM.setValue(false);
        } else if (contrasena.isEmpty()) {
            ContrasenaM.setValue(false);
        } else if (nombre.isEmpty()) {
            NombreM.setValue(false);
        } else if (apellido.isEmpty()) {
            ApellidoM.setValue(false);
        } else if (telefono.isEmpty()) {
            TelefonoM.setValue(false);
        } else if (dni.isEmpty()) {
            DNIM.setValue(false);
        } else {
          //  Usuario usuario = new Usuario(nombre, apellido, dni,  telefono,  contrasena,  correo ) ;


            // preparamos los datos del usuario

            // Preparamos la imagen del usuario
            Bitmap bitmap = ((BitmapDrawable) IMG.getDrawable()).getBitmap();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), byteArrayOutputStream.toByteArray());
            // Creo una parte multipart con el RequestBody.
            MultipartBody.Part FotoFile = MultipartBody.Part.createFormData("FotoFile", "image.jpg", requestFile);

            // Preparamos el usuario
            RequestBody Correo = RequestBody.create(MediaType.parse("application/json"), correo);
            RequestBody Contrasena = RequestBody.create(MediaType.parse("application/json"), contrasena);
            RequestBody Nombre = RequestBody.create(MediaType.parse("application/json"), nombre);
            RequestBody Apellido = RequestBody.create(MediaType.parse("application/json"), apellido);
            RequestBody Telefono = RequestBody.create(MediaType.parse("application/json"), telefono);
            RequestBody DNI = RequestBody.create(MediaType.parse("application/json"), dni);





            // Aquí iria la rutina para mandar el usuario al servicio de Registro.

            API.ApiAnimalogistics API_A = API.getApi();

            Call<Usuario> call = API_A.Registrar(Correo, Contrasena, Nombre, Apellido, Telefono, DNI, FotoFile);

            call.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if(response.isSuccessful()){



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

                                    if (errors.has("Nombre")) {
                                        errorMessage = errors.getJSONArray("Nombre").getString(0);
                                        NombreM.postValue(false);
                                    }else{
                                        NombreM.postValue(true);
                                    }
                                    if (errors.has("Apellido")) {
                                        errorMessage = errors.getJSONArray("Apellido").getString(0);
                                        ApellidoM.postValue(false);
                                    }else{
                                        ApellidoM.postValue(true);
                                    }
                                    if (errors.has("Telefono")) {
                                        errorMessage = errors.getJSONArray("Telefono").getString(0);
                                        TelefonoM.postValue(false);
                                    }else{
                                        TelefonoM.postValue(true);
                                    }
                                    if (errors.has("DNI")) {
                                        errorMessage = errors.getJSONArray("DNI").getString(0);
                                        DNIM.postValue(false);
                                    }else{
                                        DNIM.postValue(true);
                                    }
                                }

                            } catch (JSONException e) {
                                // Si no se pudo analizar como JSON, el mensaje de error es la respuesta tal cual
                                errorMessage = errorResponse;
                                // NO HAY ERROR En LOS DATOS PERO EL SERVIDOR TUVO UN ERROR
                                CorreoM.postValue(true);
                                ContrasenaM.postValue(true);
                                NombreM.postValue(true);
                                ApellidoM.postValue(true);
                                TelefonoM.postValue(true);
                                DNIM.postValue(true);

                                Toast.makeText(context, errorResponse, Toast.LENGTH_LONG).show();

                            }


                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }

                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Toast.makeText(context,t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }

    }
}

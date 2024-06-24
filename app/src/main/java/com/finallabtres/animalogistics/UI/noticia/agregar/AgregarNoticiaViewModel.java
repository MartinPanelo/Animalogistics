package com.finallabtres.animalogistics.UI.noticia.agregar;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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
import com.finallabtres.animalogistics.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregarNoticiaViewModel extends AndroidViewModel {

    private Context context;

    private MutableLiveData<String> errorM;
    private int idRefugio;
    public AgregarNoticiaViewModel(@NonNull Application application) {
        super(application);
        this.context=application.getApplicationContext();
    }

    public LiveData<String> getErrorM(){
        if(errorM==null){

            errorM=new MutableLiveData<>();
        }
        return errorM;

    }
    public void setIdRefugioNoticia(Bundle bundle) {

        idRefugio = Integer.parseInt( bundle.getString("refugioId") ); // bundle.getString("refugioId");
    }


    public void AgregarNoticia(View view, String categoria, String titulo, String contenido, ShapeableImageView banner) {


        RequestBody RefugioId = RequestBody.create(MediaType.parse("application/json"), String.valueOf(idRefugio));
        RequestBody Categoria = RequestBody.create(MediaType.parse("application/json"), categoria.substring(0,1).toUpperCase() + categoria.substring(1).toLowerCase());
        RequestBody Titulo = RequestBody.create(MediaType.parse("application/json"), titulo.substring(0,1).toUpperCase() + titulo.substring(1).toLowerCase());
        RequestBody Contenido = RequestBody.create(MediaType.parse("application/json"), contenido);

        MultipartBody.Part BannerFile = null; // Inicializa como null

        if (banner.getDrawable() instanceof BitmapDrawable) {

            // Preparamos la imagen del usuario
            Bitmap bitmap = ((BitmapDrawable) banner.getDrawable()).getBitmap();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), byteArrayOutputStream.toByteArray());
            // Creo una parte multipart con el RequestBody.
            BannerFile = MultipartBody.Part.createFormData("BannerFile", "image.jpg", requestFile);

        }

        String token = API.LeerToken(context);

        API.ApiAnimalogistics API_A = API.getApi();

        Call<Noticia> call = API_A.crearNoticia(token,RefugioId, Categoria, Titulo, Contenido, BannerFile);

        call.enqueue(new Callback<Noticia>() {
            @Override
            public void onResponse(Call<Noticia> call, Response<Noticia> response) {
                if(response.isSuccessful()){

                    Snackbar.make(view, "Noticia Creada Correctamente", Snackbar.LENGTH_LONG).show();
                    Navigation.findNavController(view).popBackStack(R.id.agregarNoticiaFragment, true);

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
                            Toast.makeText(context, errorResponse, Toast.LENGTH_LONG).show();

                        }


                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }

            }

            @Override
            public void onFailure(Call<Noticia> call, Throwable t) {
                Toast.makeText(context,t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
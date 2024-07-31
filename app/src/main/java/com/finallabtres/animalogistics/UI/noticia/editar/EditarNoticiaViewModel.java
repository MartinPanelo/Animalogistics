package com.finallabtres.animalogistics.UI.noticia.editar;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.finallabtres.animalogistics.API.API;
import com.finallabtres.animalogistics.MODELO.Noticia;
import com.finallabtres.animalogistics.MODELO.ToastUtils;
import com.finallabtres.animalogistics.R;

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

public class EditarNoticiaViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Noticia> NoticiaM;
    private MutableLiveData<String> errorM;

    public EditarNoticiaViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<Noticia> getNoticiaM(){
        if(NoticiaM==null){

            NoticiaM=new MutableLiveData<>();
        }
        return NoticiaM;

    }

    public LiveData<String> getErrorM(){
        if(errorM==null){

            errorM=new MutableLiveData<>();
        }
        return errorM;

    }
    public void cargarNoticia(Bundle bundle) {

        Noticia noticia = bundle.getSerializable("ObjNoticia", Noticia.class);
        NoticiaM.postValue(noticia);


    }

    public void actualizarNoticia(String titulo, String categoria, String contenido, ImageView IMG) {

        String token = API.LeerToken(context);
        API.ApiAnimalogistics API_A = API.getApi();




        RequestBody Id = RequestBody.create(MediaType.parse("application/json"), String.valueOf(NoticiaM.getValue().getId()));
        RequestBody Titulo = RequestBody.create(MediaType.parse("application/json"), titulo);
        RequestBody Categoria = RequestBody.create(MediaType.parse("application/json"), categoria);
        RequestBody Contenido = RequestBody.create(MediaType.parse("application/json"), contenido);

        MultipartBody.Part BannerFile = null; // Inicializa como null

        if(IMG.getDrawable() instanceof BitmapDrawable) {
            // Preparamos la imagen de la noticia
            Bitmap bitmap = ((BitmapDrawable) IMG.getDrawable()).getBitmap();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), byteArrayOutputStream.toByteArray());
            // Creo una parte multipart con el RequestBody.
            BannerFile = MultipartBody.Part.createFormData("BannerFile", "image.jpg", requestFile);
        }


        Call<Noticia> call = API_A.noticiaEditar(token, Id, Titulo, Categoria, Contenido, BannerFile);


        call.enqueue(new Callback<Noticia>() {
            @Override
            public void onResponse(Call<Noticia> call, Response<Noticia> response) {
                if(response.isSuccessful()){


                    NoticiaM.postValue(response.body());

                    ToastUtils.showToast(context, context.getString(R.string.noticia_actualizada_exitosamente), R.color.toast_success,R.drawable.check);

                /*    Toast.makeText( context, "Se actualizo la noticia", Toast.LENGTH_LONG).show();*/

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
            public void onFailure(Call<Noticia> call, Throwable t) {
                Toast.makeText(context,t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
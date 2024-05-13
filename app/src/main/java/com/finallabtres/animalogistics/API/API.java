package com.finallabtres.animalogistics.API;

import android.content.Context;
import android.content.SharedPreferences;

import com.finallabtres.animalogistics.MODELO.Noticia;
import com.finallabtres.animalogistics.MODELO.Refugio;
import com.finallabtres.animalogistics.MODELO.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public class API {

    public static final String URLBASE = "http://192.168.0.9:5014/";

    private static ApiAnimalogistics apiAnimalogistics;

    public static ApiAnimalogistics getApi(){


        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();



        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLBASE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build();

        apiAnimalogistics = retrofit.create(ApiAnimalogistics.class);

        return apiAnimalogistics;

    }

    public interface ApiAnimalogistics {

        /*---------------------LOGIN---------------------*/

        @FormUrlEncoded
        @POST("ControllerUsuario/usuarioLogin")
        Call<String> Login (@Field("correo") String correo, @Field("contrasena") String contrasena);


        /*---------------------REGISTRO---------------------*/

        @Multipart
        @POST("ControllerUsuario/usuarioRegistrar")
        Call<Usuario> Registrar (@Part("Correo") RequestBody Correo,
                                 @Part("Contrasena") RequestBody Contrasena,
                                 @Part("Nombre") RequestBody Nombre,
                                 @Part("Apellido") RequestBody Apellido,
                                 @Part("Telefono") RequestBody Telefono,
                                 @Part("DNI") RequestBody DNI,
                                 @Part MultipartBody.Part FotoFile);




        /*---------------------NOTICIA---------------------*/

        @GET("ControllerNoticia/noticiaLista")
        Call<List<Noticia>> noticiaLista(@Header("Authorization") String token);

        @GET("ControllerNoticia/noticiaListarPorCategoria")
        Call<List<Noticia>> noticiaListaPorCategoria(@Header("Authorization") String token, @Query("categoria") String categoria);

        @GET("ControllerNoticia/noticiaListarPorRefugio")
        Call<List<Noticia>> noticiaListarPorRefugio(@Header("Authorization") String token, @Query("refugioId") String refugioId);

        @GET("ControllerNoticia/noticiaListarPorRefugioPorCategoria")
        Call<List<Noticia>> noticiaListarPorRefugioPorCategoria(@Header("Authorization") String token, @Query("categoria") String categoria, @Query("refugioId") String refugioId);


        /*---------------------REFUGIO---------------------*/

        @GET("ControllerRefugio/refugioLista")
        Call<List<Refugio>> refugioLista(@Header("Authorization") String token);




    }
        /*---------------------TOKEN---------------------*/


    public static void GuardarToken(Context context, String token){

        SharedPreferences sp = context.getSharedPreferences("token.xml", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("token", token);
        editor.commit();
    }


    public static String LeerToken(Context context){

        SharedPreferences sp = context.getSharedPreferences("token.xml", Context.MODE_PRIVATE);
        return sp.getString("token", "");

    }

}

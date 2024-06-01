package com.finallabtres.animalogistics.API;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;

import com.finallabtres.animalogistics.MODELO.Animal;
import com.finallabtres.animalogistics.MODELO.Noticia;
import com.finallabtres.animalogistics.MODELO.Refugio;
import com.finallabtres.animalogistics.MODELO.Tarea;
import com.finallabtres.animalogistics.MODELO.Usuario;
import com.finallabtres.animalogistics.MODELO.Voluntario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.UUID;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

        /*---------------------USUARIO---------------------*/

        @FormUrlEncoded
        @POST("ControllerUsuario/usuarioLogin")
        Call<String> Login (@Field("correo") String correo, @Field("contrasena") String contrasena);


        @GET("ControllerUsuario/usuarioObtenerPerfil")
        Call<Usuario> usuarioObtenerPerfil(@Header("Authorization") String token);

        @Multipart
        @PUT("ControllerUsuario/usuarioActualizarPerfil")
        Call<Usuario> usuarioActualizarPerfil(@Header("Authorization") String token,
                                              @Part("Correo") RequestBody Correo,
                                              @Part("Contrasena") RequestBody Contrasena,
                                              @Part("Nombre") RequestBody Nombre,
                                              @Part("Apellido") RequestBody Apellido,
                                              @Part("Telefono") RequestBody Telefono,
                                              @Part("DNI") RequestBody DNI,
                                              @Part MultipartBody.Part FotoFile);


        @Multipart
        @PUT("ControllerUsuario/usuarioActualizarPerfil")
        Call<Usuario> usuarioActualizarPerfilSinContrasena(@Header("Authorization") String token,
                                              @Part("Correo") RequestBody Correo,
                                              @Part("Nombre") RequestBody Nombre,
                                              @Part("Apellido") RequestBody Apellido,
                                              @Part("Telefono") RequestBody Telefono,
                                              @Part("DNI") RequestBody DNI,
                                              @Part MultipartBody.Part FotoFile);




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


        @GET("ControllerRefugio/refugioObtenerPorDueno")
        Call<List<Refugio>> refugioObtenerPorDueno(@Header("Authorization") String token);

        @GET("ControllerRefugio/refugioObtenerPorVoluntario")
        Call<List<Refugio>> refugioObtenerPorVoluntario(@Header("Authorization") String token);




        @Multipart
        @POST("ControllerRefugio/refugioAgregar")
        Call<Refugio> refugioAgregar(@Header("Authorization") String token,
                                   @Part("Nombre") RequestBody Nombre,
                                   @Part("Direccion") RequestBody Direccion,
                                   @Part("Telefono") RequestBody Telefono,
                                   @Part("Descripcion") RequestBody Descripcion,
                                   @Part("GPSRango") RequestBody GPSRango,
                                   @Part("GPSX") RequestBody GPSX,
                                   @Part("GPSY") RequestBody GPSY,
                                   @Part MultipartBody.Part FotoFile);





        /*---------------------ANIMAL---------------------*/


        @Multipart
        @POST("ControllerAnimal/animalAgregar")
        Call<Animal> animalAgregar(@Header("Authorization") String token,
                                     @Part("Nombre") RequestBody Nombre,
                                     @Part("Edad") RequestBody Edad,
                                     @Part("Tipo") RequestBody Tipo,
                                     @Part("Tamano") RequestBody Tamano,
                                     @Part("Collar") RequestBody Collar,
                                     @Part("Genero") RequestBody Genero,
                                   @Part("Comentarios") RequestBody Comentarios,
                                   @Part("GPSX") RequestBody GPSX,
                                   @Part("GPSY") RequestBody GPSY,
                                   @Part MultipartBody.Part FotoFile);

        @Multipart
        @PUT("ControllerAnimal/animalEditarDeUsuario")
        Call<Animal> animalEditarDeUsuario(@Header("Authorization") String token,
                                   @Part("Id") RequestBody Id,
                                   @Part("Nombre") RequestBody Nombre,
                                   @Part("Edad") RequestBody Edad,
                                   @Part("Tipo") RequestBody Tipo,
                                   @Part("Tamano") RequestBody Tamano,
                                   @Part("Collar") RequestBody Collar,
                                   @Part("Genero") RequestBody Genero,
                                   @Part("Comentarios") RequestBody Comentarios,
                                   @Part("GPSX") RequestBody GPSX,
                                   @Part("GPSY") RequestBody GPSY,
                                   @Part MultipartBody.Part FotoFile);


        @GET("ControllerAnimal/animalListarPorUsuario")
        Call<List<Animal>> animalListarPorUsuario(@Header("Authorization") String token);


        @DELETE("ControllerAnimal/animalBorrarDeUsuario")
        Call<Animal> animalBorrarDeUsuario(@Header("Authorization") String token, @Query("animalId") int animalId);


        @GET("ControllerAnimal/listarAnimalesDisponiblesParaAdoptarPorRefugio")
        Call<List<Animal>> listarAnimalesDisponiblesParaAdoptarPorRefugio(@Header("Authorization") String token, @Query("refugioId") int refugioId);


        /*---------------------VOLUNTARIO---------------------*/

        @GET("ControllerVoluntario/listarVoluntariadosDisponbilesDeUnRefugio")
        Call<List<Voluntario>> listarVoluntariadosDisponbilesDeUnRefugio(@Header("Authorization") String token,
                                                             @Query("refugioId") String refugioId);

        @PUT("ControllerVoluntario/anotarseComoVoluntario")
        Call<Voluntario> anotarseComoVoluntario(@Header("Authorization") String token,
                                                @Query("voluntarioId") int voluntarioId);


        /*---------------------TAREA---------------------*/


    /*    @Multipart
        @PUT("ControllerTarea/anotarseComoVoluntario")
        Call<Tarea> anotarseComoVoluntario(@Header("Authorization") String token,
                                           @Query("refugioId") String refugioId);
*/

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

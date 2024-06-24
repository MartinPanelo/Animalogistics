package com.finallabtres.animalogistics.UI.dialogos;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.navigation.Navigation;

import com.finallabtres.animalogistics.API.API;
import com.finallabtres.animalogistics.MODELO.Animal;
import com.finallabtres.animalogistics.MODELO.Noticia;
import com.finallabtres.animalogistics.MODELO.Tarea;
import com.finallabtres.animalogistics.MODELO.ToastUtils;
import com.finallabtres.animalogistics.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dialogos {

    public static void DialogoBorrarAnimal(View view, Animal animal,int viewNavigation){

        new AlertDialog.Builder(view.getContext())
                .setTitle("Borrar Registro")
                .setMessage("Esta seguro que desea borrar el registro de :" + animal.getNombre())
                .setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {



                        String token = API.LeerToken(view.getContext());

                        API.ApiAnimalogistics API_A = API.getApi();

                        Call<Animal> call = API_A.animalBorrar(token, animal.getId());


                        call.enqueue(new Callback<Animal>() {
                            @Override
                            public void onResponse(@NonNull Call<Animal> call, @NonNull Response<Animal> response) {

                                if(response.isSuccessful()){


                                        // aca si salio tudo nice
                                    ToastUtils.showToast(view.getContext(), view.getContext().getString(R.string.registro_borrado_exitosamente), R.color.toast_success,R.drawable.check);

                                        Navigation.findNavController(view).popBackStack(viewNavigation, true);



                                        Bundle bundle = new Bundle();

                                        if(viewNavigation == R.id.gestionRefugioFragment){
                                            bundle.putSerializable("refugioId", String.valueOf(animal.getRefugio().getId()));
                                        }
                                        Navigation.findNavController(view).navigate(viewNavigation,bundle);







                                }else{

                                    ToastUtils.showToast(view.getContext(), view.getContext().getString(R.string.no_se_pudo_borrar_el_registro), R.color.toast_error,R.drawable.error);
                                    Toast.makeText(view.getContext(),"No se pudo borrar el registro", Toast.LENGTH_SHORT).show();
                                }


                            }

                            @Override
                            public void onFailure(Call call, Throwable t) {

                                Toast.makeText(view.getContext(), "Onfalile"+ t.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ToastUtils.showToast(view.getContext(), view.getContext().getString(R.string.operacion_cancelada), R.color.yellow,R.drawable.baseline_warning_24);
                    }
                })
                .show();
    }

    public static void DialogoBorrarTarea(View view, Tarea tarea) {


        new AlertDialog.Builder(view.getContext())
                .setTitle("Borrar Tarea")
                .setMessage("Esta seguro que desea borrar la tarea :" + tarea.getActividad())
                .setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {



                        String token = API.LeerToken(view.getContext());

                        API.ApiAnimalogistics API_A = API.getApi();

                        Call<Tarea> call = API_A.borrarTareaRefugio(token, tarea.getId());


                        call.enqueue(new Callback<Tarea>() {
                            @Override
                            public void onResponse(@NonNull Call<Tarea> call, @NonNull Response<Tarea> response) {

                                if(response.isSuccessful()){




                                  // aca si salio tudo nice
                                    ToastUtils.showToast(view.getContext(), view.getContext().getString(R.string.Tarea_borrada_exitosamente), R.color.toast_success,R.drawable.check);



                                    Navigation.findNavController(view).popBackStack(R.id.gestionRefugioFragment, true);



                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("refugioId", String.valueOf(tarea.getRefugio().getId()));
                                    Navigation.findNavController(view).navigate(R.id.gestionRefugioFragment,bundle);




                                 /*   Tarea tarea = response.body();
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("refugioId", tarea.getRefugio().getId());
                                    Navigation.findNavController(view).navigate(R.id.gestionRefugioFragment, bundle);
*/


                                }else{

                                   try {
                                        String errorResponse = response.errorBody().string();
                                      /*  String errorMessage = "";*/

                                       JSONObject jsonObject = new JSONObject(errorResponse);
                                       if (jsonObject.has("permiso")) {
                                           /*errorMessage = jsonObject.getString("permiso");*/

                                               ToastUtils.showToast(view.getContext(), view.getContext().getString(R.string.no_tiene_permisos), R.color.yellow,R.drawable.baseline_warning_24);




                                       }



                                   } catch (IOException e) {
                                       throw new RuntimeException(e);
                                   } catch (JSONException e) {
                                       throw new RuntimeException(e);
                                   }



                                }


                            }

                            @Override
                            public void onFailure(Call call, Throwable t) {

                                Toast.makeText(view.getContext(), "Onfalile"+ t.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ToastUtils.showToast(view.getContext(), view.getContext().getString(R.string.operacion_cancelada), R.color.yellow,R.drawable.baseline_warning_24);
                    }
                })
                .show();



    }

    public static void DialogoBorrarNoticia(View view, Noticia noticia) {


        new AlertDialog.Builder(view.getContext())
                .setTitle("Borrar Noticia")
                .setMessage("Esta seguro que desea borrar la noticia :" + noticia.getTitulo())
                .setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {



                        String token = API.LeerToken(view.getContext());

                        API.ApiAnimalogistics API_A = API.getApi();

                        Call<Noticia> call = API_A.noticiaEliminar(token, noticia.getId());


                        call.enqueue(new Callback<Noticia>() {
                            @Override
                            public void onResponse(@NonNull Call<Noticia> call, @NonNull Response<Noticia> response) {

                                if(response.isSuccessful()){




                                    // aca si salio tudo nice
                                    ToastUtils.showToast(view.getContext(), view.getContext().getString(R.string.noticia_borrada_exitosamente), R.color.toast_success,R.drawable.check);


                                    Navigation.findNavController(view).popBackStack(R.id.gestionRefugioFragment, true);



                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("refugioId", String.valueOf(noticia.getRefugio().getId()));
                                    Navigation.findNavController(view).navigate(R.id.gestionRefugioFragment,bundle);







                                }else{

                                    ToastUtils.showToast(view.getContext(), view.getContext().getString(R.string.no_puede_borrar_esta_noticia), R.color.toast_error,R.drawable.error);

                                }


                            }

                            @Override
                            public void onFailure(Call call, Throwable t) {

                                Toast.makeText(view.getContext(), "Onfalile"+ t.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ToastUtils.showToast(view.getContext(), view.getContext().getString(R.string.operacion_cancelada), R.color.yellow,R.drawable.baseline_warning_24);
                    }
                })
                .show();



    }

    public static void DialogoSalir(Activity activity){

        new AlertDialog.Builder(activity)
                .setTitle("Salir de la aplicacion")
                .setMessage("Esta seguro que quiere salir?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //   activity.finish();
                        SharedPreferences sp = activity.getSharedPreferences("token.xml", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();

                        editor.putString("token", "");
                        editor.commit();
                        activity.finishAndRemoveTask();
                        activity.finishAffinity();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(activity,"Continua la aplicacion", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }
}

package com.finallabtres.animalogistics.UI.dialogos;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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
import com.finallabtres.animalogistics.R;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dialogos {

    public static void DialogoBorrarAnimal(View view, Animal animal){

        new AlertDialog.Builder(view.getContext())
                .setTitle("Borrar Registro")
                .setMessage("Esta seguro que desea borrar el registro de :" + animal.getNombre())
                .setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {



                        String token = API.LeerToken(view.getContext());

                        API.ApiAnimalogistics API_A = API.getApi();

                        Call<Animal> call = API_A.animalBorrarDeUsuario(token, animal.getId());


                        call.enqueue(new Callback<Animal>() {
                            @Override
                            public void onResponse(@NonNull Call<Animal> call, @NonNull Response<Animal> response) {

                                if(response.isSuccessful()){


                                    // aca si salio tudo nice
                                    Toast.makeText(view.getContext(),"Registro borrado exitosamente", Toast.LENGTH_SHORT).show();
                                    Navigation.findNavController(view).
                                            navigate(R.id.listarAnimalFragment);

                                }else{


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
                        Toast.makeText(view.getContext(),"Operacion cancelada", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }
}

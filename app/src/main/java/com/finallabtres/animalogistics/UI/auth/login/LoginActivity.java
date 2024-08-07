package com.finallabtres.animalogistics.UI.auth.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableRow;

import com.finallabtres.animalogistics.MODELO.IdiomaUtils;
import com.finallabtres.animalogistics.R;
import com.finallabtres.animalogistics.databinding.ActivityLoginBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import android.content.pm.PackageManager;

import java.util.Locale;

public class LoginActivity extends AppCompatActivity {


    private ActivityLoginBinding binding;
    private LoginActivityViewModel vm;
    private static final int REQUEST_PERMISSION = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginActivityViewModel.class);


        /*-------------------------binding-------------------------------*/
        binding.BTNRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.registrar();
            }
        });

        binding.BTNIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.ingresar(binding.TIETUsuario.getText().toString(), binding.TIETContrasena.getText().toString());
            }
        });


        /*-------------------------Observers-------------------------------*/

        vm.getCorreoM().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean s) {
                binding.TILUsuario.setError(s ? null : getString(R.string.campo_obligatorio_Correo));
            }
        });

        vm.getContrasenaM().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean s) {
                binding.TILContrasena.setError(s ? null : getString(R.string.campo_obligatorio_Contrasena));
            }
        });


        /*-------------------------opcionesSheet-------------------------------*/

        binding.FABOpciones.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               IdiomaUtils.mostrarOpcionesIdioma(LoginActivity.this);
           }
       });
/*

                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(LoginActivity.this);
                View view1 = LayoutInflater.from(LoginActivity.this).inflate(R.layout.opcionessheet, null);
                bottomSheetDialog.setContentView(view1);
                bottomSheetDialog.show();


                TableRow MBEspanol = view1.findViewById(R.id.TREspaniol);
                TableRow MBEnglish = view1.findViewById(R.id.TREnglish);

                MBEspanol.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        saveLocale("es");
                        FijarIdioma("es");
                    }
                });


                MBEnglish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        saveLocale("en");
                        FijarIdioma("en");
                    }
                });

            }*/


                permisos();


    }


/*
    // Método para obtener el idioma guardado en SharedPreferences
    private String getSavedLocale() {
        SharedPreferences preferences = getSharedPreferences("SP", Context.MODE_PRIVATE);
        return preferences.getString("idioma", Locale.getDefault().getLanguage());
    }

    // Método para guardar el idioma en SharedPreferences
    private void saveLocale(String languageCode) {
        SharedPreferences preferences = getSharedPreferences("SP", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("idioma", languageCode);
        editor.apply();
    }


    private void FijarIdioma(String Idioma){

        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(Idioma));
    }
*/




    private void permisos() {
        // Verificar si los permisos no están otorgados
        if ((checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) ||
                (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) ||
                (checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) ||
                (checkSelfPermission(android.Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) ||
                (checkSelfPermission(android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)){

            // Si no están otorgados, mostrar un único diálogo solicitando todos los permisos necesarios
            AlertDialog.Builder dialogo = new AlertDialog.Builder(LoginActivity.this);
            dialogo.setTitle("Permisos Desactivados");
            dialogo.setMessage("Debe aceptar los permisos para el correcto funcionamiento de la App");

            dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // Solicitar permisos de cámara, ubicación y almacenamiento externo
                    requestPermissions(new String[]{
                            android.Manifest.permission.CAMERA,
                            android.Manifest.permission.ACCESS_FINE_LOCATION,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION,
                            android.Manifest.permission.READ_MEDIA_IMAGES,
                            android.Manifest.permission.CALL_PHONE
                    }, REQUEST_PERMISSION);
                }
            });

            dialogo.show();
        }
    }

}




















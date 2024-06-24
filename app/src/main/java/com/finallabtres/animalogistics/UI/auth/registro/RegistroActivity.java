package com.finallabtres.animalogistics.UI.auth.registro;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.finallabtres.animalogistics.MODELO.ToastUtils;
import com.finallabtres.animalogistics.R;
import com.finallabtres.animalogistics.UI.auth.login.LoginActivity;
import com.finallabtres.animalogistics.databinding.ActivityLoginBinding;
import com.finallabtres.animalogistics.databinding.ActivityRegistroBinding;
import com.finallabtres.animalogistics.databinding.FormularioregistroBinding;
import com.google.android.material.snackbar.Snackbar;

public class RegistroActivity extends AppCompatActivity {

    private ActivityRegistroBinding binding;
    private RegistroActivityViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroActivityViewModel.class);

        FormularioregistroBinding FormularioBinding = FormularioregistroBinding.bind(findViewById(R.id.CLFormularioRegistro));


        /*-------------------------Observers-------------------------------*/

        vm.getCorreoM().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean s) {
                FormularioBinding.TILCorreo.setError(s ? null : getString(R.string.campo_obligatorio_Correo));
            }
        });
        vm.getApellidoM().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean s) {
                FormularioBinding.TILApellido.setError(s ? null :  getString(R.string.campo_obligatorio_Apellido));
            }
        });
        vm.getContrasenaM().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean s) {
                FormularioBinding.TILContrasena.setError(s ? null : getString(R.string.campo_obligatorio_Contrasena));
            }
        });
        vm.getNombreM().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean s) {
                FormularioBinding.TILNombre.setError(s ? null :  getString(R.string.campo_obligatorio_Nombre));
            }
        });

        vm.getTelefonoM().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean s) {
                FormularioBinding.TILTelefono.setError(s ? null : getString(R.string.campo_obligatorio_Telefono));
        }
        });
        vm.getDNIM().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean s) {
                FormularioBinding.TILDNI.setError(s ? null :  getString(R.string.campo_obligatorio_DNI));
            }
        });


        vm.getNavLoginM().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean s) {

                if(s){
                    ToastUtils.showToast(binding.getRoot().getContext(), getString(R.string.registro_exitoso), R.color.toast_success,R.drawable.check);
                    Intent intent = new Intent(binding.getRoot().getContext(), LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    binding.getRoot().getContext().startActivity(intent);
                }else{
                    ToastUtils.showToast(binding.getRoot().getContext(), getString(R.string.registro_fallido_intentelo_de_nuevo), R.color.toast_error,R.drawable.error);
                }





            }
        });



       /* -----------------------------*/
        FormularioBinding.BTNRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // TODO: implementar el registro
                vm.RegistrarUsuario(
                        FormularioBinding.TILCorreo.getEditText().getText().toString(),
                        FormularioBinding.TILContrasena.getEditText().getText().toString(),
                        FormularioBinding.TILNombre.getEditText().getText().toString(),
                        FormularioBinding.TILApellido.getEditText().getText().toString(),
                        FormularioBinding.TILTelefono.getEditText().getText().toString(),
                        FormularioBinding.TILDNI.getEditText().getText().toString(),
                        FormularioBinding.IMGRegistro);
            }
        });


        // boton para abrir la camara y capturar una imagen para perfil
        FormularioBinding.IMGRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //startActivityForResult es otra forma de iniciar una activity, pero esperando desde donde la llamé un resultado
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    //startActivityForResult(takePictureIntent, 1);
                    startActivityIntent.launch(takePictureIntent);
                }

            }
        });


        // se setea la imagen de la camara en el ImageView
        vm.getFoto().observe(this, new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap bitmap) {
                FormularioBinding.IMGRegistro.setImageBitmap(bitmap);
            }
        });


    }
    // el resultado de la camara se obtiene aca
    ActivityResultLauncher<Intent> startActivityIntent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    vm.respuetaDeCamara(result);
                }
            });



}


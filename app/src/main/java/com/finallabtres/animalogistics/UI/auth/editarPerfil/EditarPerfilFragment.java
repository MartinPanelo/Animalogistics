package com.finallabtres.animalogistics.UI.auth.editarPerfil;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.finallabtres.animalogistics.API.API;
import com.finallabtres.animalogistics.MODELO.Usuario;
import com.finallabtres.animalogistics.R;
import com.finallabtres.animalogistics.UI.noticia.listar.porRefugio.ListarNoticiaPorRefugioViewModel;
import com.finallabtres.animalogistics.databinding.FragmentEditarPerfilBinding;
import com.finallabtres.animalogistics.databinding.FragmentListarNoticiaBinding;

public class EditarPerfilFragment extends Fragment {

    private EditarPerfilViewModel vm;

    private FragmentEditarPerfilBinding binding;

    public static EditarPerfilFragment newInstance() {
        return new EditarPerfilFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
     //   return inflater.inflate(R.layout.fragment_editar_perfil, container, false);

        binding = FragmentEditarPerfilBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        binding.formulario.BTNRegistrar.setText(R.string.actualizarPerfil);

        vm = new ViewModelProvider(this).get(EditarPerfilViewModel.class);

        /*-------------------------Observers-------------------------------*/

        vm.getCorreoM().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean s) {
                binding.formulario.TILCorreo.setError(s ? null : getString(R.string.campo_obligatorio_Correo));
            }
        });
        vm.getApellidoM().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean s) {
                binding.formulario.TILApellido.setError(s ? null :  getString(R.string.campo_obligatorio_Apellido));
            }
        });
        vm.getContrasenaM().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean s) {
                binding.formulario.TILContrasena.setError(s ? null : getString(R.string.campo_obligatorio_Contrasena));
            }
        });
        vm.getNombreM().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean s) {
                binding.formulario.TILNombre.setError(s ? null :  getString(R.string.campo_obligatorio_Nombre));
            }
        });

        vm.getTelefonoM().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean s) {
                binding.formulario.TILTelefono.setError(s ? null : getString(R.string.campo_obligatorio_Telefono));
            }
        });
        vm.getDNIM().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean s) {
                binding.formulario.TILDNI.setError(s ? null :  getString(R.string.campo_obligatorio_DNI));
            }
        });



        vm.getUsuarioM().observe(getViewLifecycleOwner(), new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                binding.formulario.TECorreo.setText(usuario.getCorreo());
                binding.formulario.TEContrasena.setText("");
                binding.formulario.TENombre.setText(usuario.getNombre());
                binding.formulario.TEApellido.setText(usuario.getApellido());
                binding.formulario.TETelefono.setText(usuario.getTelefono());
                binding.formulario.TEDNI.setText(usuario.getDni());


                Glide.with(root)
                        .load(API.URLBASE + usuario.getFotoUrl())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .fitCenter()
                        .override(200,200)
                        .into(binding.formulario.IMGRegistro);



            }
        });


        binding.formulario.BTNRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // TODO: implementar el registro
                vm.ActualiarUsuario(view,
                        binding.formulario.TILCorreo.getEditText().getText().toString(),
                        binding.formulario.TILContrasena.getEditText().getText().toString(),
                        binding.formulario.TILNombre.getEditText().getText().toString(),
                        binding.formulario.TILApellido.getEditText().getText().toString(),
                        binding.formulario.TILTelefono.getEditText().getText().toString(),
                        binding.formulario.TILDNI.getEditText().getText().toString(),
                        binding.formulario.IMGRegistro);
            }
        });



        vm.cargarDatos();


        // boton para abrir la camara y capturar una imagen para perfil
        binding.formulario.IMGRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //startActivityForResult es otra forma de iniciar una activity, pero esperando desde donde la llamé un resultado
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(requireActivity().getPackageManager())  != null) {
                    //startActivityForResult(takePictureIntent, 1);
                    startActivityIntent.launch(takePictureIntent);
                }

            }
        });


        // se setea la imagen de la camara en el ImageView
        vm.getFoto().observe(getViewLifecycleOwner(), new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap bitmap) {
                binding.formulario.IMGRegistro.setImageBitmap(bitmap);
            }
        });


        return root;
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
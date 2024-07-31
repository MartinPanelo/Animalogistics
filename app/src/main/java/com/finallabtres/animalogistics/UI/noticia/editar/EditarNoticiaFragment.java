package com.finallabtres.animalogistics.UI.noticia.editar;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.finallabtres.animalogistics.API.API;
import com.finallabtres.animalogistics.MODELO.Noticia;
import com.finallabtres.animalogistics.MODELO.Tarea;
import com.finallabtres.animalogistics.R;
import com.finallabtres.animalogistics.UI.tarea.editar.EditarTareaViewModel;
import com.finallabtres.animalogistics.databinding.FragmentEditarNoticiaBinding;
import com.finallabtres.animalogistics.databinding.FragmentEditarTareaBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

public class EditarNoticiaFragment extends Fragment {

    private EditarNoticiaViewModel vm;

    private FragmentEditarNoticiaBinding binding;

    private ActivityResultLauncher<PickVisualMediaRequest> pickMedia;

    public static EditarNoticiaFragment newInstance() {
        return new EditarNoticiaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentEditarNoticiaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm = new ViewModelProvider(this).get(EditarNoticiaViewModel.class);


        vm.getErrorM().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String error) {
                Snackbar.make(binding.getRoot(), error , Snackbar.LENGTH_LONG).show();
            }
        });
        vm.getNoticiaM().observe(getViewLifecycleOwner(), new Observer<Noticia>() {
            @Override
            public void onChanged(Noticia noticia) {


                binding.formularioeditarnoticia.TIETAutorNoticiaEditar.setText(noticia.getUsuario().getApellido()+" "+noticia.getUsuario().getNombre());
                binding.formularioeditarnoticia.TIETCategoriaNoticiaEditar.setText(noticia.getCategoria());
                binding.formularioeditarnoticia.TIETTituloNoticiaEditar.setText(noticia.getTitulo());
                binding.formularioeditarnoticia.TIETContenidoNOticiaEditar.setText(noticia.getContenido());
                Glide.with(root)
                        .load(API.URLBASE + noticia.getBannerUrl())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .fitCenter()
                        .override(150, 150)
                        .into(binding.formularioeditarnoticia.IMGFotoEditarNoticia);



            }
        });


        binding.formularioeditarnoticia.BTNGuardarNoticia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.actualizarNoticia(
                        binding.formularioeditarnoticia.TIETTituloNoticiaEditar.getText().toString(),
                        binding.formularioeditarnoticia.TIETCategoriaNoticiaEditar.getText().toString(),
                        binding.formularioeditarnoticia.TIETContenidoNOticiaEditar.getText().toString(),
                        binding.formularioeditarnoticia.IMGFotoEditarNoticia);


            }
        });
        binding.formularioeditarnoticia.IMGFotoEditarNoticia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch the photo picker and let the user choose only images.
                pickMedia.launch(new PickVisualMediaRequest.Builder()
                        .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                        .build());
            }
        });

        // Registers a photo picker activity launcher in single-select mode.
        pickMedia =   registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
            // Callback is invoked after the user selects a media item or closes the
            // photo picker.
            if (uri != null) {
                Log.d("PhotoPicker", "Selected URI: " + uri);
                binding.formularioeditarnoticia.IMGFotoEditarNoticia.setImageURI(uri);
            } else {
                Log.d("PhotoPicker", "No media selected");
            }
        });



        Bundle bundle = this.getArguments();

        if(bundle != null){

            vm.cargarNoticia(bundle);
        }

        return root;
    }


    @Override
    public void onResume(){
        super.onResume();
        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);
        if (bottomNavigationView != null) {
            bottomNavigationView.setVisibility(View.GONE);
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);
        if (bottomNavigationView != null) {
            bottomNavigationView.setVisibility(View.VISIBLE);
        }
    }

}
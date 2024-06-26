package com.finallabtres.animalogistics.UI.noticia.agregar;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.finallabtres.animalogistics.R;
import com.finallabtres.animalogistics.UI.noticia.editar.EditarNoticiaViewModel;
import com.finallabtres.animalogistics.databinding.FragmentAgregarNoticiaBinding;
import com.finallabtres.animalogistics.databinding.FragmentEditarNoticiaBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

public class AgregarNoticiaFragment extends Fragment {

    private AgregarNoticiaViewModel vm;

    private FragmentAgregarNoticiaBinding binding;

    private ActivityResultLauncher<PickVisualMediaRequest> pickMedia;

    public static AgregarNoticiaFragment newInstance() {
        return new AgregarNoticiaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        binding = FragmentAgregarNoticiaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm = new ViewModelProvider(this).get(AgregarNoticiaViewModel.class);


        binding.formularioagregarnoticia.TILAutorNoticiaEditar.setVisibility(View.GONE);


        vm.getErrorM().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String error) {

                Snackbar.make(root, error, Snackbar.LENGTH_SHORT).show();
            }
        });

        vm.getOperationSuccessful().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean success) {
                if(success){
                    Navigation.findNavController(root).popBackStack(R.id.agregarNoticiaFragment, true);
                }
            }
        });



        binding.formularioagregarnoticia.BTNGuardarNoticia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vm.AgregarNoticia(
                        binding.formularioagregarnoticia.TILCategoriaNoticiaEditar.getEditText().getText().toString(),
                        binding.formularioagregarnoticia.TILTituloNoticiaEditar.getEditText().getText().toString(),
                        binding.formularioagregarnoticia.TILContenidoNOticiaEditar.getEditText().getText().toString(),
                        binding.formularioagregarnoticia.IMGFotoEditarNoticia);
            }
        });


        Bundle bundle = this.getArguments();

        if(bundle != null){
            vm.setIdRefugioNoticia(bundle);
        }

        binding.formularioagregarnoticia.IMGFotoEditarNoticia.setOnClickListener(new View.OnClickListener() {
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
                               // binding.formularioagregarnoticia.IMGFotoEditarNoticia.setImageURI(uri);
                                // Cargar la imagen con Glide
                                Glide.with(this)
                                        .load(uri)
                                        .override(300, 200) // Ajusta el tamaño según sea necesario
                                        .into(binding.formularioagregarnoticia.IMGFotoEditarNoticia);

                            } else {
                                Log.d("PhotoPicker", "No media selected");
                            }
                        });


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
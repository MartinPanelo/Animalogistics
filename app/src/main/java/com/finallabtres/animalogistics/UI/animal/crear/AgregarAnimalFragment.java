package com.finallabtres.animalogistics.UI.animal.crear;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.finallabtres.animalogistics.R;
import com.finallabtres.animalogistics.UI.animal.listar.ListarAnimalFragment;
import com.finallabtres.animalogistics.UI.animal.listar.ListarAnimalViewModel;
import com.finallabtres.animalogistics.databinding.FragmentAgregarAnimalBinding;
import com.finallabtres.animalogistics.databinding.FragmentListarAnimalBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.Executor;

public class AgregarAnimalFragment extends Fragment {


    public static AgregarAnimalFragment newInstance() {
        return new AgregarAnimalFragment();
    }


    private AgregarAnimalViewModel vm;
    private FragmentAgregarAnimalBinding binding;

    private FusedLocationProviderClient fusedLocationClient;

    @SuppressLint("MissingPermission")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //   return inflater.inflate(R.layout.fragment_listar_animal, container, false);


        binding = FragmentAgregarAnimalBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        vm = new ViewModelProvider(this).get(AgregarAnimalViewModel.class);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity());


        binding.LYFormularioRegistrarAnimal.BTNRegistrarAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.registrarAnimal(
                        binding.LYFormularioRegistrarAnimal.TIETNombreAnimal.getText().toString(),
                        binding.LYFormularioRegistrarAnimal.TIETTipoAnimal.getText().toString(),
                        binding.LYFormularioRegistrarAnimal.SLREdad.getValue(),
                        binding.LYFormularioRegistrarAnimal.RBTNTamano.getCheckedRadioButtonId(),
                        binding.LYFormularioRegistrarAnimal.SWTCollar.isChecked(),
                        binding.LYFormularioRegistrarAnimal.CBGenero.getText().toString(),
                        binding.LYFormularioRegistrarAnimal.TIETDetalles.getText().toString(),
                        binding.LYFormularioRegistrarAnimal.IMGFoto
                        /* binding.LYFormularioRegistrarAnimal.POSICION*/);
            }
        });


        // boton para abrir la camara y capturar una imagen del animalito
        binding.LYFormularioRegistrarAnimal.CDFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivityForResult es otra forma de iniciar una activity, pero esperando desde donde la llamé un resultado
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
                    startActivityIntent.launch(takePictureIntent);
                }

            }
        });
        vm.getFoto().observe(getViewLifecycleOwner(), new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap bitmap) {
                binding.LYFormularioRegistrarAnimal.IMGFoto.setImageBitmap(bitmap);
            }
        });

        binding.LYFormularioRegistrarAnimal.CDPosicion.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {


                    fusedLocationClient.getLastLocation()
                            .addOnSuccessListener( requireActivity(), new OnSuccessListener<Location>() {
                                @Override
                                public void onSuccess(Location location) {
                                    if (location != null) {
                                        vm.ObtenerMapa(location);
                                        Snackbar.make(requireView(), "Latitud: " + location.getLatitude() + "\nLongitud: " + location.getLongitude(), Snackbar.LENGTH_LONG).show();
                                    }
                                }
                            });

            }
        });


        vm.getMapa().observe(requireActivity(), new Observer<AgregarAnimalViewModel.MapaActual>() {
            @Override
            public void onChanged(AgregarAnimalViewModel.MapaActual mapaActual) {
                SupportMapFragment SMF = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapaRegistroAnimal);

                SMF.getMapAsync(mapaActual);
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

















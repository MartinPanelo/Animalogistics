package com.finallabtres.animalogistics.UI.animal.detalle.porUsuario;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.finallabtres.animalogistics.API.API;
import com.finallabtres.animalogistics.MODELO.Animal;
import com.finallabtres.animalogistics.R;

import com.finallabtres.animalogistics.databinding.FragmentDetalleAnimalPorUsuarioBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

public class DetalleAnimalPorUsuarioFragment extends Fragment {

    private DetalleAnimalPorUsuarioViewModel vm;

    private FragmentDetalleAnimalPorUsuarioBinding binding;

    private FusedLocationProviderClient fusedLocationClient;
    private Animal animal = new Animal();

    public static DetalleAnimalPorUsuarioFragment newInstance() {
        return new DetalleAnimalPorUsuarioFragment();
    }

    @SuppressLint({"ClickableViewAccessibility", "MissingPermission"})
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentDetalleAnimalPorUsuarioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm = new ViewModelProvider(this).get(DetalleAnimalPorUsuarioViewModel.class);




        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity());


        binding.LYFormularioRegistrarAnimal.BTNRegistrarAnimal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (isAdded()) {

                    int radioButtonId = binding.LYFormularioRegistrarAnimal.RBTNTamano.getCheckedRadioButtonId();
                    String tamanoSeleccionado = "";

                    if (radioButtonId != -1) {
                        // Encuentra el RadioButton seleccionado por su ID
                        RadioButton radioButton = binding.LYFormularioRegistrarAnimal.RBTNTamano.findViewById(radioButtonId);

                        // Obtén el texto del RadioButton seleccionado
                        tamanoSeleccionado = radioButton.getText().toString();
                    }
                    vm.editarAnimal(animal.getId(),
                            binding.LYFormularioRegistrarAnimal.TIETNombreAnimal.getText().toString(),
                            binding.LYFormularioRegistrarAnimal.TIETTipoAnimal.getText().toString(),
                            binding.LYFormularioRegistrarAnimal.SLREdad.getValue(),
                            tamanoSeleccionado,
                            binding.LYFormularioRegistrarAnimal.SWTCollar.isChecked(),
                            binding.LYFormularioRegistrarAnimal.CBGenero.getText().toString(),
                            binding.LYFormularioRegistrarAnimal.CBEstado.getText().toString(),
                            binding.LYFormularioRegistrarAnimal.TIETDetalles.getText().toString(),
                            binding.LYFormularioRegistrarAnimal.IMGFoto);

                }
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

        binding.LYFormularioRegistrarAnimal.BTNPosicionActual.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {


                fusedLocationClient.getLastLocation()
                        .addOnSuccessListener(requireActivity(), new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                if (location != null) {
                                    vm.ObtenerMapa(location);
                                }
                            }
                        });

            }
        });

        vm.getMapa().observe(requireActivity(), new Observer<DetalleAnimalPorUsuarioViewModel.MapaActual>() {
            @Override
            public void onChanged(DetalleAnimalPorUsuarioViewModel.MapaActual mapaActual) {
                if (isAdded()) {

                    SupportMapFragment SMF = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapaRegistroAnimal);

                    SMF.getMapAsync(mapaActual);
                }
            }
        });

        binding.LYFormularioRegistrarAnimal.transparentImage.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        binding.SVRegistrarAnimal.requestDisallowInterceptTouchEvent(true);
                        // Disable touch on transparent view
                        return false;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        binding.SVRegistrarAnimal.requestDisallowInterceptTouchEvent(false);
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        binding.SVRegistrarAnimal.requestDisallowInterceptTouchEvent(true);
                        return false;

                    default:
                        return true;
                }
            }
        });


        fusedLocationClient.getLastLocation().addOnSuccessListener(requireActivity(), new OnSuccessListener<Location>() {

            @Override
            public void onSuccess(Location location) {
                if (location != null) {

                    if (animal.getId() != 0) {

                        location.setLatitude(animal.getGpsx());
                        location.setLongitude(animal.getGpsy());
                    }
                    vm.ObtenerMapa(location);
                }
            }
        });

        Bundle bundle = getArguments();

        if(bundle != null){

            //puedo llegar desde: mis registros, detalle animal desde mi refugio
            if(bundle.getBoolean("MisRegistros")){
                binding.LYFormularioRegistrarAnimal.menuEstado.setVisibility(View.GONE);
            }

            animal = (Animal) bundle.getSerializable("itemAnimal");

            binding.LYFormularioRegistrarAnimal.TIETNombreAnimal.setText(animal.getNombre());
            binding.LYFormularioRegistrarAnimal.TIETTipoAnimal.setText(animal.getTipo());
            binding.LYFormularioRegistrarAnimal.SLREdad.setValue(Float.parseFloat(animal.getEdad()));
            binding.LYFormularioRegistrarAnimal.SWTCollar.setChecked(animal.getCollar());
            binding.LYFormularioRegistrarAnimal.CBGenero.setText(animal.getGenero(),false);
            binding.LYFormularioRegistrarAnimal.TIETDetalles.setText(animal.getComentarios());
            binding.LYFormularioRegistrarAnimal.CBEstado.setText(animal.getEstado(),false);
            if(animal.getFotoUrl() != null){

                Glide.with(requireContext())
                        .load(API.URLBASE + animal.getFotoUrl())
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .fitCenter()
                        .override(300,250)
                        .into(binding.LYFormularioRegistrarAnimal.IMGFoto);



              /*  Glide.with(requireContext()).load(API.URLBASE + animal.getFotoUrl()).into(binding.LYFormularioRegistrarAnimal.IMGFoto);*/
            }
            // Iterar sobre los RadioButtons en el RadioGroup para encontrar el que coincida con el tamaño seleccionado
            for (int i = 0; i < binding.LYFormularioRegistrarAnimal.RBTNTamano.getChildCount(); i++) {
                View child = binding.LYFormularioRegistrarAnimal.RBTNTamano.getChildAt(i);
                if (child instanceof RadioButton) {
                    RadioButton radioButton = (RadioButton) child;
                    if (radioButton.getText().toString().equals(animal.getTamano())) {
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }


        }

        return root;
    };

    // el resultado de la camara se obtiene aca
    ActivityResultLauncher<Intent> startActivityIntent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    vm.respuetaDeCamara(result);
                }
            });



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
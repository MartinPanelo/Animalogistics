package com.finallabtres.animalogistics.UI.refugio.crear;

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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.finallabtres.animalogistics.R;
import com.finallabtres.animalogistics.UI.animal.crear.AgregarAnimalViewModel;
import com.finallabtres.animalogistics.databinding.FragmentCrearRefugioBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

public class CrearRefugioFragment extends Fragment {

    private CrearRefugioViewModel vm;
    private FragmentCrearRefugioBinding binding;

    private FusedLocationProviderClient fusedLocationClient;

    public static CrearRefugioFragment newInstance() {
        return new CrearRefugioFragment();
    }

    @SuppressLint({"MissingPermission", "ClickableViewAccessibility"})
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentCrearRefugioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm = new ViewModelProvider(this).get(CrearRefugioViewModel.class);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity());


        /*-----------------------BINDINGs----------------------------*/

        binding.BTNRegistrarRefugio.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (isAdded()) {

                    vm.registrarRefugio(view,
                            binding.TIETNombreRefugio.getText().toString(),
                            binding.TIETDireccionRefugio.getText().toString(),
                            binding.TIETTelefonoRefugio.getText().toString(),
                            binding.TIETDescripcionRefugio.getText().toString(),
                            binding.TIETRangoRefugio.getText().toString(),
                            binding.IMGFotoRefugio);


                }
            }
        });


        // boton para abrir la camara y capturar una imagen del animalito
        binding.CDFotoRefugio.setOnClickListener(new View.OnClickListener() {
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
                binding.IMGFotoRefugio.setImageBitmap(bitmap);
            }
        });

        binding.BTNPosicionActualParaRefugio.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {


                fusedLocationClient.getLastLocation()
                        .addOnSuccessListener(requireActivity(), new OnSuccessListener<Location>() {
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


        vm.getMapa().observe(requireActivity(), new Observer<CrearRefugioViewModel.MapaActual>() {
            @Override
            public void onChanged(CrearRefugioViewModel.MapaActual mapaActual) {
                if (isAdded()) {

                    SupportMapFragment SMF = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapaRegistroRefugio);

                    SMF.getMapAsync(mapaActual);
                }
            }
        });

        binding.transparentImage.setOnTouchListener(new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        binding.SVRegistrarRefugio.requestDisallowInterceptTouchEvent(true);
                        // Disable touch on transparent view
                        return false;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        binding.SVRegistrarRefugio.requestDisallowInterceptTouchEvent(false);
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        binding.SVRegistrarRefugio.requestDisallowInterceptTouchEvent(true);
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


                    vm.ObtenerMapa(location);
                    Snackbar.make(requireView(), "Latitud: " + location.getLatitude() + "\nLongitud: " + location.getLongitude(), Snackbar.LENGTH_LONG).show();
                }
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

































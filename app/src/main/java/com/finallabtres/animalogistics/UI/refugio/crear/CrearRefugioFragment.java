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
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.finallabtres.animalogistics.API.API;
import com.finallabtres.animalogistics.MODELO.Refugio;
import com.finallabtres.animalogistics.MODELO.ToastUtils;
import com.finallabtres.animalogistics.R;
import com.finallabtres.animalogistics.UI.animal.crear.AgregarAnimalViewModel;
import com.finallabtres.animalogistics.databinding.FragmentCrearRefugioBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
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



        binding.IformularioCrearRefugio.BTNRegistrarRefugio.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (isAdded()) {

                    vm.registrarRefugio(
                            binding.IformularioCrearRefugio.TIETNombreRefugio.getText().toString(),
                            binding.IformularioCrearRefugio.TIETDireccionRefugio.getText().toString(),
                            binding.IformularioCrearRefugio.TIETTelefonoRefugio.getText().toString(),
                            binding.IformularioCrearRefugio.TIETDescripcionRefugio.getText().toString(),
                            binding.IformularioCrearRefugio.TIETRangoRefugio.getText().toString(),
                            binding.IformularioCrearRefugio.IMGFotoRefugio);


                }
            }
        });


        // boton para abrir la camara y capturar una imagen del refugio
        binding.IformularioCrearRefugio.CDFotoRefugio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivityForResult es otra forma de iniciar una activity, pero esperando desde donde la llamé un resultado
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
                    startActivityIntent.launch(takePictureIntent);
                }

            }
        });



        binding.IformularioCrearRefugio.BTNPosicionActualParaRefugio.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {


                fusedLocationClient.getLastLocation()
                        .addOnSuccessListener(requireActivity(), new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                if (location != null) {
                                 //   vm.ObtenerMapa(location);

                                    vm.ObtenerMapa(new LatLng(location.getLatitude(), location.getLongitude()));
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

        binding.IformularioCrearRefugio.transparentImage.setOnTouchListener(new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        binding.IformularioCrearRefugio.SVRegistrarRefugio.requestDisallowInterceptTouchEvent(true);
                        // Disable touch on transparent view
                        return false;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        binding.IformularioCrearRefugio.SVRegistrarRefugio.requestDisallowInterceptTouchEvent(false);
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        binding.IformularioCrearRefugio.SVRegistrarRefugio.requestDisallowInterceptTouchEvent(true);
                        return false;

                    default:
                        return true;
                }
            }
        });


        vm.getFoto().observe(getViewLifecycleOwner(), new Observer<Drawable>() {
            @Override
            public void onChanged(Drawable Drawable) {

                Glide.with(root)
                        .load(Drawable)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .fitCenter()
                        .override(200,200)
                        .into(binding.IformularioCrearRefugio.IMGFotoRefugio);

             //   binding.IformularioCrearRefugio.IMGFotoRefugio.setImageBitmap(bitmap);
            }
        });





        /*-----------------------EDITAR----------------------------*/


        vm.getRefugioM().observe(getViewLifecycleOwner(), new Observer<Refugio>() {
            @Override
            public void onChanged(Refugio refugio) {

                binding.IformularioCrearRefugio.TIETNombreRefugio.setText(refugio.getNombre());
               binding.IformularioCrearRefugio.TIETDireccionRefugio.setText(refugio.getDireccion());
                binding.IformularioCrearRefugio.TIETTelefonoRefugio.setText(refugio.getTelefono());
                binding.IformularioCrearRefugio.TIETDescripcionRefugio.setText(refugio.getDescripcion());
                binding.IformularioCrearRefugio.TIETRangoRefugio.setText(String.valueOf(refugio.getRango()));



                Glide.with(root)
                        .load(API.URLBASE + refugio.getBannerUrl())
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .fitCenter()
                        .override(200,200)
                        .into(binding.IformularioCrearRefugio.IMGFotoRefugio);

                vm.ObtenerMapa(new LatLng(refugio.getGpsx(), refugio.getGpsy()));


            }
        });


        vm.getnavegarArefugioM().observe(getViewLifecycleOwner(), new Observer<Bundle>() {
            @Override
            public void onChanged(Bundle bundle) {



                Navigation.findNavController(root).popBackStack(R.id.crearRefugioFragment, true);

                Navigation.findNavController(root).navigate(R.id.gestionRefugioFragment,bundle);
            }
        });


        Bundle bundle = this.getArguments();

        if(bundle != null){

            vm.editarRefugio(bundle);

        }else{
            fusedLocationClient.getLastLocation().addOnSuccessListener(requireActivity(), new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {


                        vm.ObtenerMapa(new LatLng(location.getLatitude(), location.getLongitude()));
                    }
                }
            });
        }





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

































package com.finallabtres.animalogistics.UI.animal.gestion.agregarRefugio;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.finallabtres.animalogistics.API.API;
import com.finallabtres.animalogistics.MODELO.Animal;
import com.finallabtres.animalogistics.MODELO.Refugio;
import com.finallabtres.animalogistics.R;
import com.finallabtres.animalogistics.UI.animal.listar.ListarAnimalViewModel;
import com.finallabtres.animalogistics.UI.refugio.listar.ListarRefugioFragment;
import com.finallabtres.animalogistics.UI.refugio.listar.ListarRefugioViewModel;
import com.finallabtres.animalogistics.databinding.FragmentAgregarAnimalRefugioBinding;
import com.finallabtres.animalogistics.databinding.FragmentListarAnimalBinding;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class AgregarAnimalRefugioFragment extends Fragment {

    private AgregarAnimalRefugioViewModel vm;

    private FragmentAgregarAnimalRefugioBinding binding;

    View viewperfilanimal;

    BottomSheetDialog bottomSheetDialog;


    public static AgregarAnimalRefugioFragment newInstance() {
        return new AgregarAnimalRefugioFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        binding = FragmentAgregarAnimalRefugioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        vm = new ViewModelProvider(this).get(AgregarAnimalRefugioViewModel.class);



        vm.getMapaDeAnimales().observe(getViewLifecycleOwner(), new Observer<AgregarAnimalRefugioViewModel.MapaActual>() {
            @Override
            public void onChanged(AgregarAnimalRefugioViewModel.MapaActual mapaActual) {
                SupportMapFragment SMF = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapaAgregarAnimalRefugio);

                SMF.getMapAsync(mapaActual);

            }
        });


        vm.getMarker().observe(getViewLifecycleOwner(), new Observer<Marker>() {

            @Override
            public void onChanged(Marker marker) {

                vm.cargarPerfilAnimal(marker);

            }
        });

        vm.getPerfilAnimalM().observe(getViewLifecycleOwner(), new Observer<Animal>() {
            @Override
            public void onChanged(Animal animal) {


                viewperfilanimal = LayoutInflater.from(AgregarAnimalRefugioFragment.this.getContext()).inflate(R.layout.perfilanimal, null);

                if(bottomSheetDialog == null){
                    bottomSheetDialog = new BottomSheetDialog(AgregarAnimalRefugioFragment.this.requireContext());
                }
                bottomSheetDialog.setContentView(viewperfilanimal);


                ShapeableImageView SIVBanner = viewperfilanimal.findViewById(R.id.IMGFotoAnimal);
                Glide.with(requireActivity())
                        .load(API.URLBASE +  animal.getFotoUrl())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .fitCenter()
                        .override(210,238)
                        .into(SIVBanner);




                TextView tipo = viewperfilanimal.findViewById(R.id.TVTipoAnimal);
                tipo.setText(animal.getTipo());

                TextView tamano = viewperfilanimal.findViewById(R.id.TVTamanoAnimal);
                tamano.setText(animal.getTamano());

                TextView genero = viewperfilanimal.findViewById(R.id.TVGeneroAnimal);
                genero.setText(animal.getGenero());

                TextView comentarios = viewperfilanimal.findViewById(R.id.TVComentaiosAnimal);
                comentarios.setText(animal.getComentarios());



                Button BTN = viewperfilanimal.findViewById(R.id.BTNAgregarAnimalRefugio);
                BTN.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        bottomSheetDialog.dismiss();

                       vm.agregarAnimalRefugio(animal);
                    }
                });

                if(!bottomSheetDialog.isShowing()){
                    bottomSheetDialog.show();
                }
                // bottomSheetDialog.show();

            }
        });

        vm.getErrorM().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String error) {

                Snackbar.make(root, error, Snackbar.LENGTH_LONG).show();
            }
        });


        vm.getRefugioM().observe(getViewLifecycleOwner(), new Observer<Refugio>() {
            @Override
            public void onChanged(Refugio refugio) {
                vm.ObtenerMapa();
            }
        });

        vm.getOperationSuccessful().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean b) {
                Navigation.findNavController(root).popBackStack(R.id.agregarAnimalRefugioFragment, true);
            }
        });


        Bundle bundle = this.getArguments();
        if(bundle != null){
            vm.CargarDatos(bundle);

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
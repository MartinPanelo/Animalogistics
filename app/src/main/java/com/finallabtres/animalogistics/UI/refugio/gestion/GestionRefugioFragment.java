package com.finallabtres.animalogistics.UI.refugio.gestion;

import androidx.activity.OnBackPressedCallback;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.finallabtres.animalogistics.MODELO.Animal;
import com.finallabtres.animalogistics.MODELO.Noticia;
import com.finallabtres.animalogistics.MODELO.Tarea;
import com.finallabtres.animalogistics.R;
import com.finallabtres.animalogistics.databinding.FragmentGestionRefugioBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;


import java.util.List;

public class GestionRefugioFragment extends Fragment {

    private GestionRefugioViewModel vm;

    private FragmentGestionRefugioBinding binding;

    private Boolean TipoDeVista=false;
    private int idRefugio=0;


    public static GestionRefugioFragment newInstance() {
        return new GestionRefugioFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        binding = FragmentGestionRefugioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        vm = new ViewModelProvider(this).get(GestionRefugioViewModel.class);


        vm.getErrorM().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String error) {

                Snackbar.make(root, error, Snackbar.LENGTH_LONG).show();
            }
        });


        // duenio o veterinario
        vm.getTipoDeVistaM().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                TipoDeVista=aBoolean;
            }
        });


        vm.getlistaTareasM().observe(getViewLifecycleOwner(), new Observer<List<Tarea>>() {
            @Override
            public void onChanged(List<Tarea> listaTareas) {


                GridLayoutManager glm=new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);
                binding.RVTNARefugio.setLayoutManager(glm);

                TareaGestionAdapter tareaGestionAdapter=new TareaGestionAdapter(listaTareas,TipoDeVista,getContext(),getLayoutInflater()/*,getActivity()*/);
                binding.RVTNARefugio.setAdapter(tareaGestionAdapter);
            }
        });

        vm.getlistaNoticiasM().observe(getViewLifecycleOwner(), new Observer<List<Noticia>>() {
            @Override
            public void onChanged(List<Noticia> listaNoticias) {


                GridLayoutManager glm=new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);
                binding.RVTNARefugio.setLayoutManager(glm);

                NoticiaGestionAdapter noticiaGestionAdapter = new NoticiaGestionAdapter(listaNoticias,getContext(),getLayoutInflater()/*,getActivity()*/);
                binding.RVTNARefugio.setAdapter(noticiaGestionAdapter);
            }
        });

        vm.getlistaAnimalesM().observe(getViewLifecycleOwner(), new Observer<List<Animal>>() {
            @Override
            public void onChanged(List<Animal> listaAnimales) {



                GridLayoutManager glm=new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);
                binding.RVTNARefugio.setLayoutManager(glm);

                AnimalGestionAdapter animalGestionAdapter = new AnimalGestionAdapter(listaAnimales,getContext(),getLayoutInflater()/*,getActivity()*/);
                binding.RVTNARefugio.setAdapter(animalGestionAdapter);
            }
        });








        // por defecto primero se cargan los voluntariados
        Bundle bundle = this.getArguments();

        if(bundle != null){
            vm.cargarDatosDeVoluntariados(bundle);
        }

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getPosition()) {
                    case 0:
                        binding.BTNAgregarVNA.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Navigation.findNavController(getView()).navigate(R.id.agregarTareaFragment,bundle);
                            }
                        });
                        if(bundle != null) {
                            vm.cargarDatosDeVoluntariados(bundle);
                        }

                        break;
                    case 1:
                        binding.BTNAgregarVNA.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Navigation.findNavController(getView()).navigate(R.id.agregarNoticiaFragment,bundle);
                            }
                        });
                        if(bundle != null) {
                            vm.cargarDatosDeNoticias(bundle);
                        }

                        break;
                    case 2:
                        binding.BTNAgregarVNA.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Navigation.findNavController(getView()).navigate(R.id.agregarAnimalRefugioFragment,bundle);
                            }
                        });
                        if(bundle != null) {
                            vm.cargarDatosDeAnimales(bundle);
                        }

                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        binding.BTNAgregarVNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(R.id.agregarTareaFragment,bundle);
            }
        });

        binding.BTNeditarRefugio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vm.editarRefugio(bundle,v);

              //  Navigation.findNavController(getView()).navigate(R.id.crearRefugioFragment,bundle);
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
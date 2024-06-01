package com.finallabtres.animalogistics.UI.animal.listar;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.finallabtres.animalogistics.MODELO.Animal;
import com.finallabtres.animalogistics.R;
import com.finallabtres.animalogistics.databinding.FragmentListarAnimalBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class ListarAnimalFragment extends Fragment {

    private ListarAnimalViewModel vm;

    private FragmentListarAnimalBinding binding;

    public static ListarAnimalFragment newInstance() {
        return new ListarAnimalFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
     //   return inflater.inflate(R.layout.fragment_listar_animal, container, false);


        binding = FragmentListarAnimalBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        vm = new ViewModelProvider(this).get(ListarAnimalViewModel.class);




        vm.getListaAnimalesM().observe(getViewLifecycleOwner(), new Observer<List<Animal>>() {
            @Override
            public void onChanged(List<Animal> listaAnimales) {

                GridLayoutManager glm=new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);
                binding.RVAnimalesRegistrados.setLayoutManager(glm);

                AnimalAdapter animalAdapter=new AnimalAdapter(listaAnimales,getContext(),getLayoutInflater()/*,getActivity()*/);

                binding.RVAnimalesRegistrados.setAdapter(animalAdapter);
            }
        });

        vm.getErrorM().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String error) {
                Log.d("error",error);
            }
        });


        vm.cargarDatos();


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
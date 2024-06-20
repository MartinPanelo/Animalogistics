package com.finallabtres.animalogistics.UI.animal.detalle.PorRefugio;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.finallabtres.animalogistics.MODELO.Animal;
import com.finallabtres.animalogistics.R;
import com.finallabtres.animalogistics.databinding.FragmentDetalleAnimalPorRefugioBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.carousel.CarouselLayoutManager;
import com.google.android.material.carousel.CarouselSnapHelper;
import com.google.android.material.carousel.FullScreenCarouselStrategy;

import java.util.List;

public class DetalleAnimalPorRefugioFragment extends Fragment {

    private DetalleAnimalPorRefugioViewModel vm;

    private FragmentDetalleAnimalPorRefugioBinding binding;

    SnapHelper snapHelper;

    public static DetalleAnimalPorRefugioFragment newInstance() {
        return new DetalleAnimalPorRefugioFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentDetalleAnimalPorRefugioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm = new ViewModelProvider(this).get(DetalleAnimalPorRefugioViewModel.class);


        vm.getErrorM().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String error) {
                Toast.makeText( getActivity(), error, Toast.LENGTH_SHORT).show();
            }
        });



       vm.getlistaAnimalesDisponiblesParaAdoptarM().observe(getViewLifecycleOwner(), new Observer<List<Animal>>() {
           @Override
           public void onChanged(List<Animal> animals) {


               //ACA EL CAROUSEL que muetra los animales del refuigo

               RecyclerView recyclerView = root.findViewById(R.id.RVCarouselAnimales);

               CarouselLayoutManager layoutManager = new CarouselLayoutManager(new FullScreenCarouselStrategy(),RecyclerView.HORIZONTAL);

               layoutManager.setCarouselAlignment(CarouselLayoutManager.ALIGNMENT_CENTER);

               recyclerView.setLayoutManager(layoutManager);

               if (snapHelper == null) {
                   snapHelper = new CarouselSnapHelper(false);
               }

               snapHelper.attachToRecyclerView(recyclerView);

               AnimalParaAdoptarPorRefugioAdapter adapter = new AnimalParaAdoptarPorRefugioAdapter(getContext(),animals);

               recyclerView.setAdapter(adapter);

           }
       });



        Bundle bundle = this.getArguments();

        if(bundle != null){
            vm.cargarAnimalesParaAdoptar(bundle);
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
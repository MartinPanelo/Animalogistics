package com.finallabtres.animalogistics.UI.refugio.detalle.usuario;

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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.finallabtres.animalogistics.MODELO.Carousel;
import com.finallabtres.animalogistics.MODELO.Refugio;
import com.finallabtres.animalogistics.R;
import com.finallabtres.animalogistics.databinding.FragmentDetalleRefugioBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.carousel.CarouselLayoutManager;
import com.google.android.material.carousel.CarouselSnapHelper;
import com.google.android.material.carousel.HeroCarouselStrategy;

public class DetalleRefugioFragment extends Fragment {

    DetalleRefugioViewModel vm;
    FragmentDetalleRefugioBinding binding;

    SnapHelper snapHelper;

    public static DetalleRefugioFragment newInstance() {
        return new DetalleRefugioFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
      //  return inflater.inflate(R.layout.fragment_detalle_refugio, container, false);


        binding = FragmentDetalleRefugioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm = new ViewModelProvider(this).get(DetalleRefugioViewModel.class);





        vm.getRefugioM().observe(getViewLifecycleOwner(), new Observer<Refugio>() {
            @Override
            public void onChanged(Refugio refugio) {


                Glide.with(getActivity())
                        .load(/*API.URLBASE + */refugio.getBannerUrl())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .fitCenter()
                        .override(150, 150)
                        .into(binding.shapeableImageView2);

                binding.textView3.setText(refugio.getNombre());
                binding.textView4.setText(refugio.getDescripcion());



                //ACA EL CAROUSEL de menu

                RecyclerView recyclerView = root.findViewById(R.id.RVCarousel);

                CarouselLayoutManager layoutManager = new CarouselLayoutManager(new HeroCarouselStrategy(),RecyclerView.VERTICAL);


                layoutManager.setCarouselAlignment(CarouselLayoutManager.ALIGNMENT_CENTER);


                recyclerView.setLayoutManager(layoutManager);


                if (snapHelper == null) {
                    snapHelper = new CarouselSnapHelper(false);
                }

                snapHelper.attachToRecyclerView(recyclerView);

               // refugio.setCarousel(new Carousel());

                Carousel carousel = new Carousel(refugio.getId());


                CarouselAdapter adapter = new CarouselAdapter(getContext(),carousel);

                
                recyclerView.setAdapter(adapter);

            }
        });





        Bundle bundle = this.getArguments();

        if(bundle != null){
            vm.CargarRefugio(bundle);
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
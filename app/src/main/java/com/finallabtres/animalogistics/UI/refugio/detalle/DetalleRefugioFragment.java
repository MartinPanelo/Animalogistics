package com.finallabtres.animalogistics.UI.refugio.detalle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.finallabtres.animalogistics.MODELO.Noticia;
import com.finallabtres.animalogistics.MODELO.Refugio;
import com.finallabtres.animalogistics.R;
import com.finallabtres.animalogistics.UI.noticia.listar.ListarNoticiaViewModel;
import com.finallabtres.animalogistics.UI.noticia.listar.NoticiaAdapter;
import com.finallabtres.animalogistics.databinding.FragmentDetalleNoticiaBinding;
import com.finallabtres.animalogistics.databinding.FragmentDetalleRefugioBinding;
import com.finallabtres.animalogistics.databinding.FragmentListarNoticiaBinding;
import com.google.android.material.carousel.CarouselLayoutManager;
import com.google.android.material.carousel.CarouselSnapHelper;
import com.google.android.material.carousel.FullScreenCarouselStrategy;
import com.google.android.material.carousel.HeroCarouselStrategy;

import java.util.ArrayList;
import java.util.List;

public class DetalleRefugioFragment extends Fragment {

    DetalleRefugioViewModel vm;
    FragmentDetalleRefugioBinding binding;



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





                recyclerView.setLayoutManager(layoutManager);


                SnapHelper snapHelper = new CarouselSnapHelper(false);

                snapHelper.attachToRecyclerView(recyclerView);

                snapHelper.attachToRecyclerView(recyclerView);

                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add("https://random.imagecdn.app/201/200");
                arrayList.add("https://random.imagecdn.app/202/200");
                arrayList.add("https://random.imagecdn.app/203/200");
                arrayList.add("https://random.imagecdn.app/204/200");
                arrayList.add("https://random.imagecdn.app/205/200");

                CarouselAdapter adapter = new CarouselAdapter(getContext(),arrayList);
                
                recyclerView.setAdapter(adapter);

            }
        });





        Bundle bundle = this.getArguments();

        if(bundle != null){
            vm.CargarRefugio(bundle);
        }


        return root;
    }



}
package com.finallabtres.animalogistics.UI.noticia.detalle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.finallabtres.animalogistics.API.API;
import com.finallabtres.animalogistics.MODELO.Noticia;
import com.finallabtres.animalogistics.R;
import com.finallabtres.animalogistics.UI.noticia.listar.ListarNoticiaViewModel;
import com.finallabtres.animalogistics.databinding.FragmentDetalleNoticiaBinding;
import com.finallabtres.animalogistics.databinding.FragmentListarNoticiaBinding;

public class DetalleNoticiaFragment extends Fragment {

    DetalleNoticiaViewModel vm;

    FragmentDetalleNoticiaBinding binding;

    public static DetalleNoticiaFragment newInstance() {
        return new DetalleNoticiaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_detalle_noticia, container, false);

        binding = FragmentDetalleNoticiaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm = new ViewModelProvider(this).get(DetalleNoticiaViewModel.class);



        vm.getNoticiaM().observe(getActivity(), new Observer<Noticia>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(Noticia noticia) {

                Glide.with(getActivity())
                        .load(/*API.URLBASE +*/ noticia.getBannerUrl())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .fitCenter()
                        .override(300,200)
                        .into(binding.IMGFotoNoticia);

                binding.TVCategoriaNoticia.setText(noticia.getCategoria());

                binding.TVAutorNoticia.setText(noticia.getUsuario().getApellido()+" "+noticia.getUsuario().getNombre());

                binding.TVTituloNoticia.setText(noticia.getTitulo());

                binding.TVContenidoNoticia.setText(noticia.getContenido());


            }
        });



     // todo el bundle de la noticia a mostrar

        Bundle bundle = this.getArguments();

        if(bundle != null){
            vm.CargarNoticia(bundle);
        }






















        return root;
    }

/*    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetalleNoticiaViewModel.class);
        // TODO: Use the ViewModel
    }*/

}
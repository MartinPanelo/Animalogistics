package com.finallabtres.animalogistics.UI.noticia.listar;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Binder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.finallabtres.animalogistics.MODELO.Noticia;
import com.finallabtres.animalogistics.R;
import com.finallabtres.animalogistics.databinding.FragmentListarNoticiaBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ListarNoticiaFragment extends Fragment {

    ListarNoticiaViewModel vm;
     FragmentListarNoticiaBinding binding;



   /* public static ListarNoticiaFragment newInstance() {
        return new ListarNoticiaFragment();
    }*/

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
     //   return inflater.inflate(R.layout.fragment_listar_noticia, container, false);
        binding = FragmentListarNoticiaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm = new ViewModelProvider(this).get(ListarNoticiaViewModel.class);



        /* ----------------cargo las caterogias--------------------*/


        vm.getListaCategoriasM().observe(getViewLifecycleOwner(), new Observer<Set<String>>() {
            @Override
            public void onChanged(Set<String> categorias) {
                // Limpiar el toggleButton
               // binding.toggleButton.clearChecked();
                binding.toggleButton.removeAllViews();




                // Crear y agregar el botón "Todas"
                addButton(root.getContext(),"todas", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vm.cargarDatos();

                    }
                });

                // Crear y agregar los botones de categoría
                for (String categoria : categorias) {
                    addButton(root.getContext(), categoria, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            vm.cargarNoticiasPorCategorias(categoria);
                        }
                    });
                }


            }
        });






        /* ----------------cargo todas las noticias--------------------*/

        vm.getErrorM().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String error) {

                Snackbar.make(root, error, Snackbar.LENGTH_LONG).show();
            }
        });


        vm.getListaNoticiasM().observe(getViewLifecycleOwner(), new Observer<List<Noticia>>() {
            @Override
            public void onChanged(List<Noticia> listaNoticias) {

                GridLayoutManager glm=new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);
                binding.RVNoticias.setLayoutManager(glm);

                NoticiaAdapter noticiaAdapter=new NoticiaAdapter(listaNoticias,getContext(),getLayoutInflater()/*,getActivity()*/);
                binding.RVNoticias.setAdapter(noticiaAdapter);
            }
        });

        vm.cargarDatos();







    return root;
    }


    private void addButton(Context context, String text, View.OnClickListener listener) {
        MaterialButton button = new MaterialButton(context, null, com.google.android.material.R.attr.materialButtonOutlinedStyle);
        button.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        button.setText(text);
        button.setTextColor(context.getResources().getColor(R.color.black));
        button.setStrokeColorResource(R.color.orange);
        button.setStrokeWidth(4);
        button.setOnClickListener(listener);
        binding.toggleButton.addView(button);
        if(text.equals("todas")){
            button.setChecked(true);
        }
    }

}
/*
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ListarNoticiaViewModel.class);
        // TODO: Use the ViewModel
    }*/

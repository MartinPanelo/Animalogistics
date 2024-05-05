package com.finallabtres.animalogistics.UI.noticia.listar;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.finallabtres.animalogistics.R;
import com.finallabtres.animalogistics.databinding.FragmentListarNoticiaBinding;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

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



        // Lista de strings
        List<String> listaDeChips = new ArrayList<>();
        listaDeChips.add("Chip 1");
        listaDeChips.add("Chip 2");
        listaDeChips.add("Chip 3");
        listaDeChips.add("Chip 4");
        listaDeChips.add("Chip 5");
        listaDeChips.add("Chip 6");
        listaDeChips.add("Chip 7");
        listaDeChips.add("Chip 8");
        listaDeChips.add("Chip 9");


        ChipGroup chipGroup = binding.chipGroup;

        for (String chip : listaDeChips) {
            Chip chips = new Chip(this.getContext());

            chips.setText(chip);
            chips.setCheckable(true);
            chipGroup.addView(chips);
            chips.setOnCheckedChangeListener((buttonView, isChecked) -> {

                // aca me voy al vm y le mando la categoria y traigo las noticias y inflo el recycler
            });
        }






/*        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vm.cargarNoticiasPorCategoria("");
            }
        });*/
/*
        MaterialButtonToggleGroup toggleButton = binding.toggleButton;



        toggleButton.addOnButtonCheckedListener((group, checkedId, isChecked) -> {




            String message = "";

            if(checkedId == R.id.button1){
                message = "Button 1" + (isChecked ? " checked" : " unchecked");
                Snackbar.make(root, message, Snackbar.LENGTH_SHORT).show();
            } else if (checkedId == R.id.button2){
                message = "Button 2" + (isChecked ? " checked" : " unchecked");
            }else if (checkedId == R.id.button3){
                message = "Button 3" + (isChecked ? " checked" : " unchecked");
            }


        });
*/




    return root;
    }


}
/*
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ListarNoticiaViewModel.class);
        // TODO: Use the ViewModel
    }*/

package com.finallabtres.animalogistics.UI.animal.listar;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.finallabtres.animalogistics.R;
import com.finallabtres.animalogistics.databinding.FragmentListarAnimalBinding;

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


        ListarAnimalViewModel vm =
                new ViewModelProvider(this).get(ListarAnimalViewModel.class);


        return root;

    }





}
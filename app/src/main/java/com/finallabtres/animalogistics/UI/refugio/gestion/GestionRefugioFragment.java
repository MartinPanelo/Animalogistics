package com.finallabtres.animalogistics.UI.refugio.gestion;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.finallabtres.animalogistics.R;
import com.finallabtres.animalogistics.UI.animal.crear.AgregarAnimalViewModel;
import com.finallabtres.animalogistics.databinding.FragmentAgregarAnimalBinding;
import com.finallabtres.animalogistics.databinding.FragmentGestionRefugioBinding;

public class GestionRefugioFragment extends Fragment {

    private GestionRefugioViewModel vm;

    private FragmentGestionRefugioBinding binding;


    public static GestionRefugioFragment newInstance() {
        return new GestionRefugioFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        binding = FragmentGestionRefugioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        vm = new ViewModelProvider(this).get(GestionRefugioViewModel.class);











        return root;
    }



}
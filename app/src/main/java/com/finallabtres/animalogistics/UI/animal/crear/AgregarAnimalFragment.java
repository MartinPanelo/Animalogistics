package com.finallabtres.animalogistics.UI.animal.crear;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.finallabtres.animalogistics.R;
import com.finallabtres.animalogistics.UI.animal.listar.ListarAnimalFragment;
import com.finallabtres.animalogistics.UI.animal.listar.ListarAnimalViewModel;
import com.finallabtres.animalogistics.databinding.FragmentAgregarAnimalBinding;
import com.finallabtres.animalogistics.databinding.FragmentListarAnimalBinding;

public class AgregarAnimalFragment extends Fragment {



    public static AgregarAnimalFragment newInstance() {
        return new AgregarAnimalFragment();
    }


    private AgregarAnimalViewModel mViewModel;
    private FragmentAgregarAnimalBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //   return inflater.inflate(R.layout.fragment_listar_animal, container, false);


        binding = FragmentAgregarAnimalBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        AgregarAnimalViewModel vm =
                new ViewModelProvider(this).get(AgregarAnimalViewModel.class);


        return root;

    }

}
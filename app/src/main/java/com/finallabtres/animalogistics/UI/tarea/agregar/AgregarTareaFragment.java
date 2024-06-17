package com.finallabtres.animalogistics.UI.tarea.agregar;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.finallabtres.animalogistics.R;
import com.finallabtres.animalogistics.UI.refugio.gestion.GestionRefugioViewModel;
import com.finallabtres.animalogistics.databinding.FragmentAgregarTareaBinding;
import com.finallabtres.animalogistics.databinding.FragmentGestionRefugioBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

public class AgregarTareaFragment extends Fragment {

    private AgregarTareaViewModel vm;

    private FragmentAgregarTareaBinding binding;

    public static AgregarTareaFragment newInstance() {
        return new AgregarTareaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentAgregarTareaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm = new ViewModelProvider(this).get(AgregarTareaViewModel .class);


        binding.IFormularioTarea.CBLiberarTarea.setVisibility(View.GONE);
        binding.IFormularioTarea.TVUsuarioTarea.setVisibility(View.GONE);
        binding.IFormularioTarea.TVTituloUsuarioTarea.setVisibility(View.GONE);

        vm.getErrorM().observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(String error) {

                        Snackbar.make(root, error, Snackbar.LENGTH_SHORT).show();
                    }
                });

        binding.IFormularioTarea.BTNGuardarTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vm.AgregarTarea(
                        v,
                        binding.IFormularioTarea.TILActividadTarea.getEditText().getText().toString(),
                        binding.IFormularioTarea.TILDescripcionTarea.getEditText().getText().toString());
            }
        });


        Bundle bundle = this.getArguments();

        if(bundle != null){
            vm.setIdRefugioTarea(bundle);
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
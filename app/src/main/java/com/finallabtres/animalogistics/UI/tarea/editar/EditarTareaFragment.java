package com.finallabtres.animalogistics.UI.tarea.editar;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.finallabtres.animalogistics.MODELO.Tarea;
import com.finallabtres.animalogistics.R;
import com.finallabtres.animalogistics.UI.refugio.gestion.GestionRefugioViewModel;
import com.finallabtres.animalogistics.UI.tarea.ListarTareasDisponiblesPorRefugioViewModel;
import com.finallabtres.animalogistics.databinding.FragmentEditarTareaBinding;
import com.finallabtres.animalogistics.databinding.FragmentGestionRefugioBinding;
import com.finallabtres.animalogistics.databinding.FragmentListarTareasDisponiblesPorRefugioBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

public class EditarTareaFragment extends Fragment {

    private EditarTareaViewModel vm;

    private FragmentEditarTareaBinding binding;


    public static EditarTareaFragment newInstance() {
        return new EditarTareaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentEditarTareaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm = new ViewModelProvider(this).get(EditarTareaViewModel.class);


        vm.getErrorM().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String error) {
                Snackbar.make(binding.getRoot(), error , Snackbar.LENGTH_LONG).show();
            }
        });
        vm.getEditFormM().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.Iformulariotarea.TIETActividadTarea.setEnabled(aBoolean);
                binding.Iformulariotarea.TIETDescripcionTarea.setEnabled(aBoolean);
            }
        });

        vm.setCheckBoxM().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

                binding.Iformulariotarea.CBLiberarTarea.setChecked(aBoolean);
            }
        });

        vm.getTareaM().observe(getViewLifecycleOwner(), new Observer<Tarea>() {
            @Override
            public void onChanged(Tarea tarea) {


                binding.Iformulariotarea.TIETActividadTarea.setText(tarea.getActividad());
                binding.Iformulariotarea.TIETDescripcionTarea.setText(tarea.getDescripcion());

                if(tarea.getUsuario() != null){
                    binding.Iformulariotarea.TVUsuarioTarea.setText(tarea.getUsuario().getNombre() + " " + tarea.getUsuario().getApellido());

                }else{
                    binding.Iformulariotarea.TVUsuarioTarea.setText("No asignado");
                    binding.Iformulariotarea.CBLiberarTarea.setEnabled(false);
                }
            }
        });


        binding.Iformulariotarea.BTNGuardarTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.actualizarTarea(
                        binding.Iformulariotarea.TIETActividadTarea.getText().toString(),
                        binding.Iformulariotarea.TIETDescripcionTarea.getText().toString(),
                        binding.Iformulariotarea.CBLiberarTarea.isChecked()
                );
            }
        });



        Bundle bundle = this.getArguments();

        if(bundle != null){

            vm.cargarTarea(bundle);
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
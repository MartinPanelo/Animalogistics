package com.finallabtres.animalogistics.UI.refugio.voluntario.listar;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.finallabtres.animalogistics.MODELO.Tarea;
import com.finallabtres.animalogistics.MODELO.Voluntario;
import com.finallabtres.animalogistics.R;
import com.finallabtres.animalogistics.databinding.FragmentListarVoluntariadosDisponiblesBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListarVoluntariadosDisponiblesFragment extends Fragment {

    private ListarVoluntariadosDisponiblesViewModel vm;

    private FragmentListarVoluntariadosDisponiblesBinding binding;

    private int selectedPosition = -1; // Variable para almacenar la posición seleccionada

    public static ListarVoluntariadosDisponiblesFragment newInstance() {
        return new ListarVoluntariadosDisponiblesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentListarVoluntariadosDisponiblesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm = new ViewModelProvider(this).get(ListarVoluntariadosDisponiblesViewModel.class);

        vm.getlistaTareasDisponibleM().observe(getViewLifecycleOwner(), new Observer<List<Tarea>>() {
            @Override
            public void onChanged(List<Tarea> tareas) {

                List<String> actividades = tareas.stream()
                        .map(Tarea::getActividad)
                        .collect(Collectors.toList());

                ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, actividades);

                binding.CBTareas.setAdapter(adapter);

                // Establecer el primer elemento por defecto si la lista no está vacía
                if (!tareas.isEmpty()) {
                    binding.CBTareas.setText(actividades.get(0), false); // false evita que se muestre el dropdown
                    selectedPosition = 0;
                    // Establecer el texto en el TextView al primer elemento
                    binding.TVDetalleTarea.setText(tareas.get(0).getDescripcion());
                }
            }
        });

        binding.BNTInscribirse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vm.inscribirse(v,selectedPosition);
            }
        });

        vm.getTareaDisponibleM().observe(getViewLifecycleOwner(), new Observer<Tarea>() {
            @Override
            public void onChanged(Tarea tarea) {
                binding.TVDetalleTarea.setText(tarea.getDescripcion());
            }
        });

      /*  vm.getTareaElegidaM().observe(getViewLifecycleOwner(), new Observer<Tarea>() {
            @Override
            public void onChanged(Tarea tarea) {
                Toast.makeText(getActivity(), "el id es" + tarea.getId(), Toast.LENGTH_LONG).show();
            }
        });*/


        vm.getErrorM().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String error) {
                Toast.makeText( getActivity(), error, Toast.LENGTH_SHORT).show();
            }
        });

        binding.CBTareas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;
                vm.mostrarTarea(position);
            }
        });



        Bundle bundle = this.getArguments();

        if(bundle != null){
            vm.cargarTareasDisponibles(bundle);
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
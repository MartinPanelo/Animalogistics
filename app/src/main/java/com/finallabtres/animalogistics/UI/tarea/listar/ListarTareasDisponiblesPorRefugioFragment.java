package com.finallabtres.animalogistics.UI.tarea.listar;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.finallabtres.animalogistics.MODELO.Tarea;
import com.finallabtres.animalogistics.R;
import com.finallabtres.animalogistics.databinding.FragmentListarTareasDisponiblesPorRefugioBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import java.util.stream.Collectors;

public class ListarTareasDisponiblesPorRefugioFragment extends Fragment {

    private ListarTareasDisponiblesPorRefugioViewModel vm;

    private FragmentListarTareasDisponiblesPorRefugioBinding binding;

    private int selectedPosition = -1; // Variable para almacenar la posición seleccionada

    public static ListarTareasDisponiblesPorRefugioFragment newInstance() {
        return new ListarTareasDisponiblesPorRefugioFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentListarTareasDisponiblesPorRefugioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm = new ViewModelProvider(this).get(ListarTareasDisponiblesPorRefugioViewModel.class);


        vm.getlistaTareasDisponibleM().observe(getViewLifecycleOwner(), new Observer<List<Tarea>>() {
            @Override
            public void onChanged(List<Tarea> tareas) {

                List<String> actividades = tareas.stream()
                        .map(Tarea::getActividad)
                        .collect(Collectors.toList());


                // Crear un ArrayAdapter para mostrar los elementos de la lista
                ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, actividades);

                binding.CBTareas.setAdapter(adapter);

                // Establecer el primer elemento por defecto si la lista no está vacía
                if (!actividades.isEmpty()) {
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

                vm.inscribirse(selectedPosition);
            }
        });

        vm.getTareaDisponibleM().observe(getViewLifecycleOwner(), new Observer<Tarea>() {
            @Override
            public void onChanged(Tarea tarea) {
                binding.TVDetalleTarea.setText(tarea.getDescripcion());
            }
        });

        vm.getErrorM().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String error) {
                Toast.makeText( getActivity(), error, Toast.LENGTH_SHORT).show();
            }
        });

        vm.getOperationSuccessful().observe(getViewLifecycleOwner(), new Observer<Bundle>() {
            @Override
            public void onChanged(Bundle bundle) {

                Navigation.findNavController(root).popBackStack(R.id.item_refugios, true);
                
                Navigation.findNavController(root).navigate(R.id.gestionRefugioFragment,bundle);

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
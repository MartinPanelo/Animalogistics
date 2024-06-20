package com.finallabtres.animalogistics.UI.refugio.listar;

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
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.finallabtres.animalogistics.API.API;
import com.finallabtres.animalogistics.MODELO.Refugio;
import com.finallabtres.animalogistics.R;
import com.finallabtres.animalogistics.databinding.FragmentListarRefugioBinding;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ListarRefugioFragment extends Fragment {

    ListarRefugioViewModel vm;
    FragmentListarRefugioBinding binding;

    View viewperfilrefugio;

    BottomSheetDialog bottomSheetDialog;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentListarRefugioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm = new ViewModelProvider(this).get(ListarRefugioViewModel.class);




        vm.getMapaDeRefugios().observe(getViewLifecycleOwner(), new Observer<ListarRefugioViewModel.MapaActual>() {
            @Override
            public void onChanged(ListarRefugioViewModel.MapaActual mapaActual) {
                SupportMapFragment SMF = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

                SMF.getMapAsync(mapaActual);

            }
        });


        vm.getMarker().observe(getViewLifecycleOwner(), new Observer<Marker>() {

            @Override
            public void onChanged(Marker marker) {

              vm.cargarPerfilRefugio(marker);


            }
        });

        vm.getPerfilRefugioM().observe(getViewLifecycleOwner(), new Observer<Refugio>() {
            @Override
            public void onChanged(Refugio refugio) {




                viewperfilrefugio = LayoutInflater.from(ListarRefugioFragment.this.getContext()).inflate(R.layout.perfilrefugio, null);

                if(bottomSheetDialog == null){
                    bottomSheetDialog = new BottomSheetDialog(ListarRefugioFragment.this.requireContext());
                }
                bottomSheetDialog.setContentView(viewperfilrefugio);


                ShapeableImageView SIVBanner = viewperfilrefugio.findViewById(R.id.IMGFotoRefugio);


                Glide.with(requireActivity())
                        .load(API.URLBASE + refugio.getBannerUrl())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .fitCenter()
                        .override(380,150)
                        .into(SIVBanner);

                TextView nombrerefugio = viewperfilrefugio.findViewById(R.id.TVNombreRefugio);
                nombrerefugio.setText(refugio.getNombre());

                TextView direccionrefugio = viewperfilrefugio.findViewById(R.id.TVDireccion);
                direccionrefugio.setText(refugio.getDireccion());

                TextView telefonorefugio = viewperfilrefugio.findViewById(R.id.TVTelefono);
                telefonorefugio.setText("Telefono: "+ refugio.getTelefono());

                Button BTN = viewperfilrefugio.findViewById(R.id.BTNDetalleRefugio);


                BTN.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        bottomSheetDialog.dismiss();

                        Bundle bundle = new Bundle();

                        bundle.putSerializable("itemrefugio", refugio);

                    //    Snackbar.make(view, "En construccion"+refugio.getId(), Snackbar.LENGTH_LONG).show();


                        Navigation.findNavController(root).
                                navigate(R.id.detalleRefugioFragment, bundle);
                    }
                });
                bottomSheetDialog.show();
               /* if(!bottomSheetDialog.isShowing()){
                    bottomSheetDialog.show();
                }else{
                    bottomSheetDialog.dismiss();
                }*/

            }
        });

        vm.getErrorM().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String error) {

                Snackbar.make(root, error, Snackbar.LENGTH_LONG).show();
            }
        });



        vm.getListaRefugioM().observe(getViewLifecycleOwner(), new Observer<List<Refugio>>() {
            @Override
            public void onChanged(List<Refugio> refugios) {
                vm.ObtenerMapa();
            }
        });



            vm.CargarRefugios();


        return root;
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
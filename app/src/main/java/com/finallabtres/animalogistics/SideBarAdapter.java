package com.finallabtres.animalogistics;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.finallabtres.animalogistics.MODELO.Noticia;
import com.finallabtres.animalogistics.MODELO.Refugio;


import java.util.List;

public class SideBarAdapter extends RecyclerView.Adapter<SideBarAdapter.ViewHolder> {


    private List<Refugio> listaRefugios;
    private NavController navController;
    private Boolean TipoDeVista;

    private DrawerLayout drawer;
    private LayoutInflater li;


    // si TipoDeVista es true es para los refugios de los que el usuario es dueno y si es false es para los refugios de los que el usuario es voluntario
    public SideBarAdapter(List<Refugio> listaRefugios, Boolean TipoDeVista , DrawerLayout drawer, NavController navController, LayoutInflater li) {
        this.listaRefugios = listaRefugios;
        this.navController = navController;
        this.li = li;
        this.TipoDeVista = TipoDeVista;
        this.drawer = drawer;


    }

    @NonNull
    @Override
    public SideBarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=li.inflate(R.layout.item_btn_menu,parent,false);

        return new SideBarAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull SideBarAdapter.ViewHolder holder, int position) {


        holder.nombreRefugio.setText(listaRefugios.get(position).getNombre());

    }



    @Override
    public int getItemCount() {
        return listaRefugios.size();
    }

    public void updateList(List<Refugio> newList) {
        listaRefugios.clear();
        listaRefugios = newList;

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nombreRefugio;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombreRefugio = itemView.findViewById(R.id.TVNombreRefugioSideBar);




           itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        drawer.closeDrawer(GravityCompat.START,true);
                        int posicion = getAdapterPosition();
                        int refugioId = listaRefugios.get(posicion).getId();

                        Bundle bundle = new Bundle();



                        bundle.putSerializable("refugioId", String.valueOf(refugioId));
                        bundle.putBoolean("TipoDeVista", TipoDeVista);

                        navController.navigate(R.id.gestionRefugioFragment, bundle);

                       /* Navigation.findNavController(view).
                                navigate(R.id.gestionRefugioFragment, bundle);*/
                    }
            });

        }
    }

}











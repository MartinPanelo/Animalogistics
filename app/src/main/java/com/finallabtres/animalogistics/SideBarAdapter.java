package com.finallabtres.animalogistics;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
    private LayoutInflater li;


    // si TipoDeVista es true es para los refugios de los que el usuario es dueno y si es false es para los refugios de los que el usuario es voluntario
    public SideBarAdapter(List<Refugio> listaRefugios, NavController navController, LayoutInflater li) {
        this.listaRefugios = listaRefugios;
        this.navController = navController;
        this.li = li;


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


/*        holder.titulo.setText(listaRefugios.get(position).getTitulo());
        holder.autor.setText(listaRefugios.get(position).getCategoria());*/



/*

        Glide.with(context)
                .load(*/
/*API.URLBASE + *//*
listaRefugios.get(position).getBannerUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .override(150, 150)
                .into(holder.imagen);
*/

    }



    @Override
    public int getItemCount() {
        return listaRefugios.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nombreRefugio;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombreRefugio = itemView.findViewById(R.id.TVNombreRefugioSideBar);




           itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        int posicion = getAdapterPosition();
                        int refugioId = listaRefugios.get(posicion).getId();

                        Bundle bundle = new Bundle();

                        bundle.putSerializable("itemRefugio", refugioId);

                        navController.navigate(R.id.gestionRefugioFragment, bundle);

                       /* Navigation.findNavController(view).
                                navigate(R.id.gestionRefugioFragment, bundle);*/
                    }
            });

        }
    }

}











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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.finallabtres.animalogistics.MODELO.Noticia;
import com.finallabtres.animalogistics.MODELO.Refugio;


import java.util.List;

public class SideBarAdapter extends RecyclerView.Adapter<SideBarAdapter.ViewHolder> {


    private List<Refugio> listaRefugios;
    private Boolean TipoDeVista;
    private Context context;
    private LayoutInflater li;


    // si TipoDeVista es true es para los refugios de los que el usuario es dueno y si es false es para los refugios de los que el usuario es voluntario
    public SideBarAdapter(List<Refugio> listaRefugios,Boolean TipoDeVista, Context context, LayoutInflater li) {
        this.listaRefugios = listaRefugios;
        this.TipoDeVista = TipoDeVista;
        this.context = context;
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

        private Button nombreRefugio;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombreRefugio = itemView.findViewById(R.id.BTNRefugio);





/*
          si tipodevista es true es para los refugios de los que el usuario es dueno y si es false es para los refugios de los que el usuario es voluntario
*/
           itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        int posicion = getAdapterPosition();
                        Refugio refugio = listaRefugios.get(posicion);

                        Bundle bundle = new Bundle();

                        bundle.putSerializable("itemRefugio", refugio);
                        bundle.putBoolean("TipoDeVista", TipoDeVista);

                        Navigation.findNavController(view).
                                navigate(R.id.editarRefugioFragment, bundle);
                    }
            });

        }
    }

}











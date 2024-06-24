package com.finallabtres.animalogistics.UI.animal.listar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.finallabtres.animalogistics.API.API;
import com.finallabtres.animalogistics.MODELO.Animal;
import com.finallabtres.animalogistics.R;
import com.finallabtres.animalogistics.UI.dialogos.Dialogos;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;


import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.ViewHolder> {


    private List<Animal> listaAnimales;
    private Context context;
    private LayoutInflater li;


    public AnimalAdapter(List<Animal> listaAnimales, Context context, LayoutInflater li) {
        this.listaAnimales = listaAnimales;
        this.context = context;
        this.li = li;

    }

    @NonNull
    @Override
    public AnimalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=li.inflate(R.layout.item_animal,parent,false);

        return new AnimalAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull AnimalAdapter.ViewHolder holder, int position) {

        if (listaAnimales.get(position).getNombre() != null && !listaAnimales.get(position).getNombre().isEmpty()) {
            holder.titulo.setText(listaAnimales.get(position).getNombre());
        }
        if(listaAnimales.get(position).getTamano() != null && !listaAnimales.get(position).getTamano().isEmpty()) {
            holder.detalles.setText(listaAnimales.get(position).getTipo() + " - " + listaAnimales.get(position).getTamano());
        }
        if(listaAnimales.get(position).getComentarios() != null && !listaAnimales.get(position).getComentarios().isEmpty()) {
            holder.comentarios.setText(listaAnimales.get(position).getComentarios());
        }



        Glide.with(context)
                .load(API.URLBASE + listaAnimales.get(position).getFotoUrl())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .fitCenter()
                .override(150, 150)
                .into(holder.imagen);

    }



    @Override
    public int getItemCount() {
        return listaAnimales.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{


        private TextView titulo;

        private TextView detalles;

        private TextView comentarios;

        private  ShapeableImageView imagen;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.TVTitulo);

            detalles = itemView.findViewById(R.id.TVDetalles);

            comentarios = itemView.findViewById(R.id.TVComentarios);


            imagen = itemView.findViewById(R.id.ACIVImagen);


            MaterialButton BTNBorrar = itemView.findViewById(R.id.BTNBorrarRegistroAnimal);


            BTNBorrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Dialogos.DialogoBorrarAnimal(view, listaAnimales.get(getAdapterPosition()),R.id.listarAnimalFragment);


                }
            });



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int posicion = getAdapterPosition();
                    Animal animal = listaAnimales.get(posicion);

                    Bundle bundle = new Bundle();

                    bundle.putSerializable("itemAnimal", animal);




                    Navigation.findNavController(view).
                            navigate(R.id.detalleAnimalPorUsuarioFragment, bundle);
                   /* navigate(R.id.detalleAnimalPorUsuarioFragment, bundle);*/
                }
            });

        }
    }

}


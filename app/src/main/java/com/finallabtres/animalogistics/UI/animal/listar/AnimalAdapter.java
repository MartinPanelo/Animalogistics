package com.finallabtres.animalogistics.UI.animal.listar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.finallabtres.animalogistics.MODELO.Animal;
import com.finallabtres.animalogistics.R;
import com.finallabtres.animalogistics.UI.dialogos.Dialogos;
import com.google.android.material.button.MaterialButton;


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

        holder.titulo.setText(listaAnimales.get(position).getNombre());




/*
        Glide.with(context)
                .load(*//*API.URLBASE + *//*listaNoticias.get(position).getBannerUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .override(150, 150)
                .into(holder.imagen);*/

    }



    @Override
    public int getItemCount() {
        return listaAnimales.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{


        private TextView titulo;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.TVTitulo);


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


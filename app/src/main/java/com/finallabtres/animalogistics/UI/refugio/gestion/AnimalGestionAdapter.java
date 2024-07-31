package com.finallabtres.animalogistics.UI.refugio.gestion;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.finallabtres.animalogistics.API.API;
import com.finallabtres.animalogistics.MODELO.Animal;
import com.finallabtres.animalogistics.MODELO.Noticia;
import com.finallabtres.animalogistics.R;
import com.finallabtres.animalogistics.UI.dialogos.Dialogos;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class AnimalGestionAdapter extends RecyclerView.Adapter<AnimalGestionAdapter.ViewHolder> {


    private List<Animal> listaAnimales;
    private Context context;

    private LayoutInflater li;


    public AnimalGestionAdapter(List<Animal> listaAnimales, Context context, LayoutInflater li) {
        this.listaAnimales = listaAnimales;
        this.context = context;
        this.li = li;

    }

    @NonNull
    @Override
    public AnimalGestionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=li.inflate(R.layout.item_animal_gestion,parent,false);

        return new AnimalGestionAdapter.ViewHolder(view);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AnimalGestionAdapter.ViewHolder holder, int position) {

        holder.nombre.setText(listaAnimales.get(position).getNombre());
        holder.estado.setText(listaAnimales.get(position).getEstado());
        holder.comentarios.setText(listaAnimales.get(position).getComentarios());
      //  holder.comentarios.setText(listaAnimales.get(position).getComentarios().substring(0, 50) + "...");



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
        private TextView nombre;
        private TextView estado;
        private TextView comentarios;
        private ImageView imagen;





        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.TVAnimalNombre);

            estado = itemView.findViewById(R.id.TVAnimalEstado);

            comentarios = itemView.findViewById(R.id.TVAnimalComentarios);

            imagen = itemView.findViewById(R.id.IMGAnimalFoto);


            MaterialButton BTNBorrar = itemView.findViewById(R.id.BTNAnimalEliminar);

            MaterialButton BTNEditar = itemView.findViewById(R.id.BTNAnimalEditar);


            BTNBorrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Dialogos.DialogoBorrarAnimal(view, listaAnimales.get(getAdapterPosition()), R.id.gestionRefugioFragment);

                }
            });

            BTNEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Bundle bundle = new Bundle();

                    bundle.putSerializable("itemAnimal", listaAnimales.get(getAdapterPosition()));

                    Navigation.findNavController(v).navigate(R.id.detalleAnimalPorUsuarioFragment, bundle);
                }
            });


        }
    }

}



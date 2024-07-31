package com.finallabtres.animalogistics.UI.animal.detalle.PorRefugio;

import static com.finallabtres.animalogistics.API.API.URLBASE;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.finallabtres.animalogistics.MODELO.Animal;
import com.finallabtres.animalogistics.R;

import java.util.List;

public class AnimalParaAdoptarPorRefugioAdapter extends RecyclerView.Adapter<AnimalParaAdoptarPorRefugioAdapter.ViewHolder> {

    private Context context;

    private static List<Animal> animales;



    public AnimalParaAdoptarPorRefugioAdapter(Context context, List<Animal> animales) {
        this.context = context;
        this.animales = animales;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.carousel_animal,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //  Glide.with(context).load(carousel.getImagenes().get(position)).into(holder.imageView);

        Glide.with(context)
                .load( URLBASE + animales.get(position).getFotoUrl())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .fitCenter()
                .override(210,238)
                .into(holder.imageView);

        holder.nombre.setText(animales.get(position).getNombre());

        holder.Comentarios.setText(animales.get(position).getComentarios());

        holder.edad.setText(animales.get(position).getEdad());

        holder.tamano.setText(animales.get(position).getTamano());

        holder.genero.setText(animales.get(position).getGenero());

        if(animales.get(position).getCollar()){
            holder.collar.setText("Si");
        }else{
            holder.collar.setText("No");
        }
        holder.estado.setText(animales.get(position).getEstado());

        holder.tipo.setText(animales.get(position).getTipo());




    }

    @Override
    public int getItemCount() {
        return animales.size();
    }

    public static class  ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView nombre;
        TextView Comentarios;
        TextView edad;
        TextView tamano;
        TextView genero;
        TextView collar;
        TextView estado;
        TextView tipo;

        int vista;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.IMGAnimalParaAdoptar);

            nombre = itemView.findViewById(R.id.TVNombreAnimal);

            Comentarios = itemView.findViewById(R.id.TVComentarioAnimal);

            edad = itemView.findViewById(R.id.TVEdadParaAdoptar);

            tamano = itemView.findViewById(R.id.TVTamanoParaAdoptar);

            genero = itemView.findViewById(R.id.TVGeneroParaAdoptar);

            collar = itemView.findViewById(R.id.TVCollarParaAdoptar);

            estado = itemView.findViewById(R.id.TVEstadoParaAdoptar);

            tipo = itemView.findViewById(R.id.TVTipoParaAdoptar);







        }
    }


}

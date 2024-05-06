package com.finallabtres.animalogistics.UI.noticia.listar;

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
import com.finallabtres.animalogistics.MODELO.Noticia;
import com.finallabtres.animalogistics.R;

import java.util.List;

public class NoticiaAdapter extends RecyclerView.Adapter<NoticiaAdapter.ViewHolder> {


    private List<Noticia> listaNoticias;
    private Context context;

    private LayoutInflater li;


    public NoticiaAdapter(List<Noticia> listaNoticias, Context context, LayoutInflater li) {
        this.listaNoticias = listaNoticias;
        this.context = context;
        this.li = li;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=li.inflate(R.layout.item_noticia,parent,false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.titulo.setText(listaNoticias.get(position).getTitulo());
        holder.autor.setText(listaNoticias.get(position).getCategoria());




        Glide.with(context)
                .load(/*API.URLBASE + */listaNoticias.get(position).getBannerUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .override(210,238)
                .into(holder.imagen);

    }



    @Override
    public int getItemCount() {
        return listaNoticias.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imagen;
        private TextView titulo;
        private TextView autor;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imagen = itemView.findViewById(R.id.IMGFotoNoticia);

            titulo = itemView.findViewById(R.id.TVTitulo);
            autor = itemView.findViewById(R.id.TVAutor);




     /*       itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int posicion = getAdapterPosition();
                    Noticia noticia = listaNoticias.get(posicion);

                    Bundle bundle = new Bundle();

                    bundle.putSerializable("itemnoticia", noticia);

                    Navigation.findNavController(view).
                            navigate(R.id.ACAELFRAGMETQUEVAALANOTICIA, bundle);
                }
            });*/

        }
    }

}

































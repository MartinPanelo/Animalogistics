package com.finallabtres.animalogistics.UI.refugio.detalle;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.finallabtres.animalogistics.MODELO.Carousel;
import com.finallabtres.animalogistics.MODELO.Refugio;
import com.finallabtres.animalogistics.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class CarouselAdapter extends RecyclerView.Adapter<CarouselAdapter.ViewHolder> {

    private Context context;

    private static Carousel carousel;



    public CarouselAdapter(Context context, Carousel carousel) {
        this.context = context;
        this.carousel = carousel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.opcionescarousel,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(carousel.getImagenes().get(position)).into(holder.imageView);

        holder.descripcionCarousel.setText(carousel.getDescripciones().get(position));




    }

    @Override
    public int getItemCount() {
        return carousel.getDescripciones().size();
    }

    public static class  ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;

        TextView descripcionCarousel;

        int vista;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);

            descripcionCarousel = itemView.findViewById(R.id.TVDescripcionCarousel);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                //    int p = getAdapterPosition();

                    Bundle bundle = new Bundle();

                    Snackbar.make(v, String.valueOf(carousel.getIdRefugio()), Snackbar.LENGTH_LONG).show();

                    // DESDE ACA ME VOY A LAS VISTAS PARTICULARIZANDO LA INFORMACION A UN REFUGIO
                 //   bundle.putSerializable("item", refugio);


                }
            });


        }
    }


}

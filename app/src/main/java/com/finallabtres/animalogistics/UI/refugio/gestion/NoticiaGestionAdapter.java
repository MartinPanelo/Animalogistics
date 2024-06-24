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
import com.finallabtres.animalogistics.MODELO.Noticia;
import com.finallabtres.animalogistics.MODELO.Tarea;
import com.finallabtres.animalogistics.MODELO.ToastUtils;
import com.finallabtres.animalogistics.R;
import com.finallabtres.animalogistics.UI.dialogos.Dialogos;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NoticiaGestionAdapter extends RecyclerView.Adapter<NoticiaGestionAdapter.ViewHolder> {


    private List<Noticia> listaNoticias;
    private Context context;

    private LayoutInflater li;

    public NoticiaGestionAdapter(List<Noticia> listaNoticias, Context context, LayoutInflater li) {
        this.listaNoticias = listaNoticias;
        this.context = context;
        this.li = li;

    }

    @NonNull
    @Override
    public NoticiaGestionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=li.inflate(R.layout.item_noticia_gestion,parent,false);

        return new NoticiaGestionAdapter.ViewHolder(view);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull NoticiaGestionAdapter.ViewHolder holder, int position) {

        holder.titulo.setText(listaNoticias.get(position).getTitulo());
        holder.autor.setText(listaNoticias.get(position).getUsuario().getApellido()+", "+listaNoticias.get(position).getUsuario().getNombre());
        holder.contenido.setText(listaNoticias.get(position).getContenido());
      /*  holder.contenido.setText(listaNoticias.get(position).getContenido().substring(0, 50) + "...");*/



        Glide.with(context)
                .load(API.URLBASE + listaNoticias.get(position).getBannerUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .override(150, 150)
                .into(holder.imagen);

    }



    @Override
    public int getItemCount() {
        return listaNoticias.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView autor;
        private TextView titulo;
        private TextView contenido;
        private ShapeableImageView imagen;





        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.TVNoticiaTitulo);

            autor = itemView.findViewById(R.id.TVNoticiaAutor);

            contenido = itemView.findViewById(R.id.TVNoticiaContenido);

            imagen = itemView.findViewById(R.id.IMGNoticiaFoto);

            MaterialButton BTNBorrar = itemView.findViewById(R.id.BTNNoticiaEliminar);

            MaterialButton BTNEditar = itemView.findViewById(R.id.BTNNoticiaEditar);

            BTNBorrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Dialogos.DialogoBorrarNoticia(v, listaNoticias.get(getAdapterPosition()));
                }
            });




            BTNEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String token = API.LeerToken(context);

                    API.ApiAnimalogistics API_A = API.getApi();


                    Call<Noticia> call = API_A.noticiaPodId(token, listaNoticias.get(getAdapterPosition()).getId());


                    call.enqueue(new Callback<Noticia>() {
                        @Override
                        public void onResponse(Call<Noticia> call, Response<Noticia> response) {

                            if(response.isSuccessful()){

                                Bundle bundle = new Bundle();


                                bundle.putSerializable("ObjNoticia", response.body());

                                Navigation.findNavController(view).navigate(R.id.editarNoticiaFragment, bundle);

                            }else{

                                if (response.code() == 404) {

                                    ToastUtils.showToast(context, context.getString(R.string.no_puede_editar_esta_noticia), R.color.toast_error,R.drawable.error);

                                  /*  Snackbar.make(view, "No puede editar esta noticia", Snackbar.LENGTH_LONG).show();*/

                                } else {

                                    Snackbar.make(view, "Se produjo el siguiente fallo: " + response.code(), Snackbar.LENGTH_LONG).show();
                                }
                            }


                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {

                            Snackbar.make(view, t.getMessage(), Snackbar.LENGTH_LONG).show();
                        }
                    });


                }
            });





        }
    }



}

































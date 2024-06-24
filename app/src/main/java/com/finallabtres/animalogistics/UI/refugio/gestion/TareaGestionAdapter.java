package com.finallabtres.animalogistics.UI.refugio.gestion;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.finallabtres.animalogistics.API.API;
import com.finallabtres.animalogistics.MODELO.Refugio;
import com.finallabtres.animalogistics.MODELO.Tarea;
import com.finallabtres.animalogistics.MODELO.ToastUtils;
import com.finallabtres.animalogistics.R;
import com.finallabtres.animalogistics.UI.dialogos.Dialogos;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;


import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TareaGestionAdapter extends RecyclerView.Adapter<TareaGestionAdapter.ViewHolder> {


    private List<Tarea> listaTareas;
    private Context context;
    private LayoutInflater li;
    private Boolean TipoDeVista;

    public TareaGestionAdapter(List<Tarea> listaTareas, Boolean TipoDeVista, Context context, LayoutInflater li) {
        this.listaTareas = listaTareas;
        this.context = context;
        this.li = li;
        this.TipoDeVista = TipoDeVista;
    }

    @NonNull
    @Override
    public TareaGestionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=li.inflate(R.layout.item_tarea_gestion,parent,false);

        return new TareaGestionAdapter.ViewHolder(view);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TareaGestionAdapter.ViewHolder holder, int position) {

        holder.actividad.setText(listaTareas.get(position).getActividad());
        holder.descripcion.setText(listaTareas.get(position).getDescripcion());

        if(listaTareas.get(position).getUsuario() == null){

            holder.usuario.setText(context.getString(R.string.a_cargo_de));
        }else {
            holder.usuario.setText(context.getString(R.string.a_cargo_de) + listaTareas.get(position).getUsuario().getApellido() + " " + listaTareas.get(position).getUsuario().getNombre());
        }




    }



    @Override
    public int getItemCount() {
        return listaTareas.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView actividad;
        private TextView descripcion;
        private TextView usuario;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            actividad = itemView.findViewById(R.id.TVTareaAvtividad);
            descripcion = itemView.findViewById(R.id.TVTareaDescripcion);
            usuario = itemView.findViewById(R.id.TVTareaUsuario);



            MaterialButton BTNBorrar = itemView.findViewById(R.id.BTNTareaEliminar);

            MaterialButton BTNEditar = itemView.findViewById(R.id.BTNTareaEditar);


            BTNBorrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Dialogos.DialogoBorrarTarea(view, listaTareas.get(getAdapterPosition()));

                }
            });

            BTNEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String token = API.LeerToken(context);

                    API.ApiAnimalogistics API_A = API.getApi();


                    Call<Tarea> call = API_A.tareaPorId(token, listaTareas.get(getAdapterPosition()).getId());


                    call.enqueue(new Callback<Tarea>() {
                        @Override
                        public void onResponse(Call<Tarea> call, Response<Tarea> response) {

                            if(response.isSuccessful()){

                                Bundle bundle = new Bundle();
                                bundle.putBoolean("TipoDeVista", TipoDeVista);
                            //    bundle.putSerializable("ObjTarea", listaTareas.get(getAdapterPosition())); Aca va la respuesta no la lista
                                bundle.putSerializable("ObjTarea", response.body());
                                Navigation.findNavController(view).navigate(R.id.editarTareaFragment, bundle);

                            }else{

                                if (response.code() == 404) {

                                    ToastUtils.showToast(context, context.getString(R.string.no_puede_editar_esta_tarea), R.color.toast_error,R.drawable.error);

                                } else {

                                    ToastUtils.showToast(context, context.getString(R.string.se_prudujo_un_error) + response.code(), R.color.toast_error,R.drawable.error);

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















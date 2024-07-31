package com.finallabtres.animalogistics.UI.refugio.detalle.usuario;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.finallabtres.animalogistics.MODELO.Noticia;
import com.finallabtres.animalogistics.MODELO.Refugio;
import com.finallabtres.animalogistics.MODELO.ToastUtils;
import com.finallabtres.animalogistics.R;
import com.google.android.material.snackbar.Snackbar;

public class DetalleRefugioViewModel extends AndroidViewModel {

    private Context context;

    private Refugio refugio;

    private MutableLiveData<Refugio> refugioM;

    public DetalleRefugioViewModel(@NonNull Application application) {
        super(application);
        context=application;
    }


    public LiveData<Refugio> getRefugioM(){
        if(refugioM==null){

            refugioM=new MutableLiveData<>();
        }
        return refugioM;

    }

    public void CargarRefugio(Bundle bundle) {

        refugio = (Refugio) bundle.getSerializable("itemrefugio");

        refugioM.postValue(refugio);

    }

    public void LlamarRefugio() {


        if(refugio.getTelefono() != null){
            Intent intent = new Intent(Intent.ACTION_DIAL);

            intent.setData(Uri.parse("tel:" + refugio.getTelefono()));

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(intent);

        }else{
            ToastUtils.showToast(context, context.getString(R.string.refugio_sin_telefono), R.color.yellow, R.drawable.phone_svgrepo_com);

        }




    }
















}
package com.finallabtres.animalogistics.UI.noticia.detalle;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.finallabtres.animalogistics.MODELO.Noticia;

public class DetalleNoticiaViewModel extends AndroidViewModel {

    private Context context;

    private Noticia noticia;

    private MutableLiveData<Noticia> noticiaM;

    public DetalleNoticiaViewModel(@NonNull Application application) {
        super(application);
        context=application;
    }


    public LiveData<Noticia> getNoticiaM(){
        if(noticiaM==null){

            noticiaM=new MutableLiveData<>();
        }
        return noticiaM;

    }

    public void CargarNoticia(Bundle bundle) {

        noticia = (Noticia) bundle.getSerializable("itemnoticia");

        noticiaM.postValue(noticia);

    }
}
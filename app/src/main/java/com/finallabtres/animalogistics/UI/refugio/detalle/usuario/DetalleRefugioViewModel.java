package com.finallabtres.animalogistics.UI.refugio.detalle;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.finallabtres.animalogistics.MODELO.Noticia;
import com.finallabtres.animalogistics.MODELO.Refugio;

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
}
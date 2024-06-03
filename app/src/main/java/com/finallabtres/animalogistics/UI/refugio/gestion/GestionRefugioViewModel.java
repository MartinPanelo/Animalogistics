package com.finallabtres.animalogistics.UI.refugio.gestion;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

public class GestionRefugioViewModel extends AndroidViewModel {

    private Context context;

    public GestionRefugioViewModel(@NonNull Application application) {
        super(application);
        this.context=application.getApplicationContext();
    }

}
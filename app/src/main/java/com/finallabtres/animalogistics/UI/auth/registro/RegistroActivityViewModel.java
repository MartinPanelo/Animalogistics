package com.finallabtres.animalogistics.UI.auth.registro;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class RegistroActivityViewModel extends AndroidViewModel {

    private Context context;

    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
    }
}

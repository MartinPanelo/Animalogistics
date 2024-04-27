package com.finallabtres.animalogistics.UI.auth.login;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class LoginActivityViewModel extends AndroidViewModel{

        private Context context;

        public LoginActivityViewModel(@NonNull Application application) {
            super(application);
            context = application;
        }
}

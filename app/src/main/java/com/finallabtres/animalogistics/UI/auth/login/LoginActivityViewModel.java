package com.finallabtres.animalogistics.UI.auth.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.finallabtres.animalogistics.UI.auth.registro.RegistroActivity;

public class LoginActivityViewModel extends AndroidViewModel{

        private Context context;

        public LoginActivityViewModel(@NonNull Application application) {
            super(application);
            this.context = application;
        }


        public void registrar() {

            Intent intent = new Intent(context, RegistroActivity.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(intent);
        }

}

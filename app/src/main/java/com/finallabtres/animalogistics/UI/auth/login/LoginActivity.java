package com.finallabtres.animalogistics.UI.auth.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.finallabtres.animalogistics.MainActivityViewModel;
import com.finallabtres.animalogistics.R;
import com.finallabtres.animalogistics.databinding.ActivityLoginBinding;
import com.finallabtres.animalogistics.databinding.ActivityMainBinding;

public class LoginActivity extends AppCompatActivity {


    private ActivityLoginBinding binding;
    private LoginActivityViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginActivityViewModel.class);


        /*-------------------------binding-------------------------------*/
        binding.BTNRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.registrar();
            }
        });

        binding.BTNIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.ingresar(binding.TIETUsuario.getText().toString(), binding.TIETContraseA.getText().toString());
            }
        });


        /*-------------------------Observers-------------------------------*/

        vm.getCorreoM().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean s) {
                binding.TILUsuario.setError(s ? null : getString(R.string.campo_obligatorio_Correo));
            }
        });

        vm.getContraseñaM().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean s) {
                binding.TILContraseA.setError(s ? null : getString(R.string.campo_obligatorio_Contrasena));
            }
        });


    }
}


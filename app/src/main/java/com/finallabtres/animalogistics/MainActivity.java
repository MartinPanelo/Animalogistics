package com.finallabtres.animalogistics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.finallabtres.animalogistics.API.API;
/*
import com.finallabtres.animalogistics.MODELO.IdiomaUtils;
*/
import com.finallabtres.animalogistics.MODELO.IdiomaUtils;
import com.finallabtres.animalogistics.MODELO.Refugio;
import com.finallabtres.animalogistics.MODELO.Usuario;
import com.finallabtres.animalogistics.UI.auth.login.LoginActivity;
import com.finallabtres.animalogistics.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import com.finallabtres.animalogistics.UI.dialogos.Dialogos;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;
    private MainActivityViewModel vm;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        vm = new ViewModelProvider(this).get(MainActivityViewModel.class);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        vm.cargarDatosNavSide();
        vm.cargarDatosUsuario();

       /* ---------------Defino la navegacion----------------*/

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = binding.navigationContainer;
        BottomNavigationView bottomNavigationView = binding.bottomNavigation;
        NavigationView navigationView = binding.NVSideBar;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.item_noticias, R.id.item_refugios, R.id.item_registrar_animal)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);


        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        NavigationUI.setupWithNavController(navigationView, navController);








        binding.navigationContainer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                vm.cargarDatosNavSide();

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {


            }
        });


        binding.include2.BTNMisRegistros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.listarAnimalFragment);
                cerrarDrawer(drawer);
            }
        });

        binding.include2.BTNEditarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.editarPerfilFragment);
                cerrarDrawer(drawer);
            }
        });
        binding.include2.BTNAgregarRefugio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.crearRefugioFragment);
                cerrarDrawer(drawer);
            }
        });




     /* -------------------  los observers----------------------*/
        vm.getErrorM().observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {

                        Snackbar.make(findViewById(android.R.id.content), s, Snackbar.LENGTH_LONG).show();
                    }
                }
        );
        vm.getListaRefugiosM().observe(this, new Observer<List<Refugio>>() {
            @Override
            public void onChanged(List<Refugio> listaRefugios) {

                RecyclerView RV = binding.include2.RVRefugiosDuenoo;

                if (RV.getAdapter() == null) {
                    GridLayoutManager glm = new GridLayoutManager(getApplicationContext(), 1, GridLayoutManager.VERTICAL, false);
                    RV.setLayoutManager(glm);

                    SideBarAdapter sideBarAdapter = new SideBarAdapter(listaRefugios, true,drawer, navController, getLayoutInflater());// true = duenio
                    RV.setAdapter(sideBarAdapter);
                } else {
                    ((SideBarAdapter) RV.getAdapter()).updateList(listaRefugios);
                    RV.getAdapter().notifyDataSetChanged();
                }

            }
        });

        vm.getListaRefugiosVoluntarioM().observe(this, new Observer<List<Refugio>>() {
            @Override
            public void onChanged(List<Refugio> listaRefugios) {

                RecyclerView RV = binding.include2.RVRefugiosVoluntario;

                if (RV.getAdapter() == null) {
                    GridLayoutManager glm = new GridLayoutManager(getApplicationContext(), 1, GridLayoutManager.VERTICAL, false);
                    RV.setLayoutManager(glm);

                    SideBarAdapter sideBarAdapter = new SideBarAdapter(listaRefugios, false,drawer, navController, getLayoutInflater());// false = voluntario
                    RV.setAdapter(sideBarAdapter);
                } else {
                    ((SideBarAdapter) RV.getAdapter()).updateList(listaRefugios);
                    RV.getAdapter().notifyDataSetChanged();
                }

            }
        });

        vm.getUsuarioM().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                binding.include2.TVNombreUsuario.setText(usuario.getNombre() + "  " + usuario.getApellido());
                binding.include2.TVCorreoUsuario.setText(usuario.getCorreo());

                Glide.with(getApplicationContext())
                        .load(API.URLBASE + usuario.getFotoUrl())
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .fitCenter()
                        .override(210,238)
                        .into(binding.include2.IMSFotoPerfilUsuario);



            }
        });

        binding.include2.FABOpciones.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               IdiomaUtils.mostrarOpcionesIdioma(MainActivity.this);
           }
        });



        binding.include2.BTNCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialogos.DialogoSalir(MainActivity.this);
            }
        });



    }


    public void cerrarDrawer(DrawerLayout drawer) {
        drawer.closeDrawer(GravityCompat.START,true);
    }

    @Override
    public boolean onSupportNavigateUp() {

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
      //  BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }



}
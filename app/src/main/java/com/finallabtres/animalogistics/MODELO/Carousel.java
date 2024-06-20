package com.finallabtres.animalogistics.MODELO;


import android.graphics.drawable.Drawable;

import com.finallabtres.animalogistics.R;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Carousel implements Serializable {

    private int idRefugio;
    private List<Integer> imagenes;

    private List<String> descripciones;

    private List<Integer> vistas;

    public Carousel(List<Integer> imagenes, List<String> descripciones, List<Integer> vistas) {
        this.imagenes = imagenes;
        this.descripciones = descripciones;
        this.vistas = vistas;
    }

    public Carousel(int idRefugio) {
        this.imagenes = Arrays.asList(
                R.drawable.noticiasrefugio,
                R.drawable.carouselnoticia,
                R.drawable.adopcionesrefugio);

        this.descripciones = Arrays.asList(
                "Noticias.",
                "Voluntariados.",
                "Adopciónes."
        );
        this.vistas = Arrays.asList(
                R.id.listarNoticiaPorRefugioFragment,
                R.id.listarTareasDisponiblesPorRefugioFragment,
                R.id.detalleAnimalPorRefugioFragment

        );
        this.idRefugio = idRefugio;

    }

    public int getIdRefugio() {
        return idRefugio;
    }

    public void setIdRefugio(int idRefugio) {
        this.idRefugio = idRefugio;
    }

    public List<Integer> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<Integer> imagenes) {
        this.imagenes = imagenes;
    }

    public List<String> getDescripciones() {
        return descripciones;
    }

    public void setDescripciones(List<String> descripciones) {
        this.descripciones = descripciones;
    }

    public List<Integer> getVistas() {
        return vistas;
    }

    public void setVistas(List<Integer> vistas) {
        this.vistas = vistas;
    }
}

package com.finallabtres.animalogistics.MODELO;

import com.finallabtres.animalogistics.R;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Carousel implements Serializable {

    private int idRefugio;
    private List<String> imagenes;

    private List<String> descripciones;

    private List<Integer> vistas;

    public Carousel(List<String> imagenes, List<String> descripciones, List<Integer> vistas) {
        this.imagenes = imagenes;
        this.descripciones = descripciones;
        this.vistas = vistas;
    }

    public Carousel(int idRefugio) {
        this.imagenes = Arrays.asList(
                "https://random.imagecdn.app/201/200",
                "https://random.imagecdn.app/202/200",
                "https://random.imagecdn.app/203/200",
                "https://random.imagecdn.app/204/200"
        );
        this.descripciones = Arrays.asList(
                "Noticias",
                "Anotarse Como Voluntario",
                "Adopciónes",
                "Eventos"
        );
        this.vistas = Arrays.asList(
                R.id.detalleNoticiaFragment,
                R.id.detalleNoticiaFragment,
                R.id.detalleNoticiaFragment,
                R.id.detalleNoticiaFragment
        );
        this.idRefugio = idRefugio;

    }

    public int getIdRefugio() {
        return idRefugio;
    }

    public void setIdRefugio(int idRefugio) {
        this.idRefugio = idRefugio;
    }

    public List<String> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<String> imagenes) {
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

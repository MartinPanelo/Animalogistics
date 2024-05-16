package com.finallabtres.animalogistics.MODELO;

import java.io.Serializable;



public class Noticia implements Serializable {

    private int id;
    private Voluntario voluntario;
    private String categoria;
    private String titulo;
    private String contenido;
    private String bannerUrl;


    public Noticia() {};

    public Noticia(int id, Voluntario voluntario, String categoria, String titulo, String contenido, String bannerUrl) {
        this.id = id;
        this.voluntario = voluntario;
        this.categoria = categoria;
        this.titulo = titulo;
        this.contenido = contenido;
        this.bannerUrl = bannerUrl;
    }

    public Noticia(Voluntario voluntario, String categoria, String titulo, String contenido, String bannerUrl) {
        this.voluntario = voluntario;
        this.categoria = categoria;
        this.titulo = titulo;
        this.contenido = contenido;
        this.bannerUrl = bannerUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Voluntario getVoluntario() {
        return voluntario;
    }

    public void setVoluntario(Voluntario voluntario) {
        this.voluntario = voluntario;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }
}

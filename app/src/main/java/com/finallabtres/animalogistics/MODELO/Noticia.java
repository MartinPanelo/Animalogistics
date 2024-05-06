package com.finallabtres.animalogistics.MODELO;

import java.io.Serializable;



public class Noticia implements Serializable {

    private int id;
    private Refugio refugio;
    private Voluntario voluntario;
    private String categoria;
    private String titulo;
    private String contenido;
    private String bannerUrl;


    public Noticia() {};

    public Noticia(int id, Refugio refugio, Voluntario voluntario, String categoria, String titulo, String contenido, String bannerUrl) {
        this.id = id;
        this.refugio = refugio;
        this.voluntario = voluntario;
        this.categoria = categoria;
        this.titulo = titulo;
        this.contenido = contenido;
        this.bannerUrl = bannerUrl;
    }

    public Noticia(Refugio refugio, Voluntario voluntario, String categoria, String titulo, String contenido, String bannerUrl) {
        this.refugio = refugio;
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

    public Refugio getRefugio() {
        return refugio;
    }

    public void setRefugio(Refugio refugio) {
        this.refugio = refugio;
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

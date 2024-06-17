package com.finallabtres.animalogistics.MODELO;

import java.io.Serializable;



public class Noticia implements Serializable {

    private int id;
    private Usuario usuario;
    private Refugio refugio;
    private String categoria;
    private String titulo;
    private String contenido;
    private String bannerUrl;


    public Noticia() {};

    public Noticia(int id, Usuario usuario, Refugio refugio, String categoria, String titulo, String contenido, String bannerUrl) {
        this.id = id;
        this.usuario = usuario;
        this.refugio = refugio;
        this.categoria = categoria;
        this.titulo = titulo;
        this.contenido = contenido;
        this.bannerUrl = bannerUrl;
    }

    public Noticia(Usuario usuario, Refugio refugio, String categoria, String titulo, String contenido, String bannerUrl) {
        this.usuario = usuario;
        this.refugio = refugio;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public Refugio getRefugio() {
        return refugio;
    }

    public void setRefugio(Refugio refugio) {
        this.refugio = refugio;
    }
}

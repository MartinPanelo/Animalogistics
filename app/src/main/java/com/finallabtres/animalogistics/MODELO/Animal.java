package com.finallabtres.animalogistics.MODELO;

import java.io.Serializable;
import java.math.BigDecimal;

public class Animal implements Serializable {

    private int id;
    private Refugio refugio;
    private Usuario usuario;
    private String nombre;
    private String edad;
    private String tipo;
    private String tamano;
    private Boolean collar;
    private String genero;
    private String comentarios;
    private Double gpsy;
    private Double  gpsx;
    private String estado;
    private String fotoUrl;

    public Animal() {
    }

    public Animal(int id, Refugio refugio, Usuario usuario, String nombre, String edad, String tipo, String tamano, Boolean collar, String genero, String comentarios, Double gpsy, Double gpsx, String estado, String fotoUrl) {
        this.id = id;
        this.refugio = refugio;
        this.usuario = usuario;
        this.nombre = nombre;
        this.edad = edad;
        this.tipo = tipo;
        this.tamano = tamano;
        this.collar = collar;
        this.genero = genero;
        this.comentarios = comentarios;
        this.gpsy = gpsy;
        this.gpsx = gpsx;
        this.estado = estado;
        this.fotoUrl = fotoUrl;
    }

    public Animal(String nombre, String edad, String tipo, String tamano, Boolean collar, String genero, String comentarios, Double gpsy, Double gpsx, String estado) {
        this.nombre = nombre;
        this.edad = edad;
        this.tipo = tipo;
        this.tamano = tamano;
        this.collar = collar;
        this.genero = genero;
        this.comentarios = comentarios;
        this.gpsy = gpsy;
        this.gpsx = gpsx;
        this.estado = estado;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public Boolean getCollar() {
        return collar;
    }

    public void setCollar(Boolean collar) {
        this.collar = collar;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Double getGpsy() {
        return gpsy;
    }

    public void setGpsy(Double gpsy) {
        this.gpsy = gpsy;
    }

    public Double getGpsx() {
        return gpsx;
    }

    public void setGpsx(Double gpsx) {
        this.gpsx = gpsx;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

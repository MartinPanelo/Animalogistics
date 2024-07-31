package com.finallabtres.animalogistics.MODELO;

import java.io.Serializable;

public class Tarea  implements Serializable {

    private int id;

    private Usuario usuario;
    private Refugio refugio;
    private String actividad;
    private String descripcion;


    public Tarea() {
    }

    public Tarea(int id, Usuario usuario, Refugio refugio, String actividad, String descripcion) {
        this.id = id;
        this.usuario = usuario;
        this.refugio = refugio;
        this.actividad = actividad;
        this.descripcion = descripcion;
    }

    public Tarea(String actividad,Usuario usuario,Refugio refugio, String descripcion) {
        this.usuario = usuario;
        this.refugio = refugio;
        this.actividad = actividad;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Refugio getRefugio() {
        return refugio;
    }

    public void setRefugio(Refugio refugio) {
        this.refugio = refugio;
    }
}

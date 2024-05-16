package com.finallabtres.animalogistics.MODELO;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.lang.String;

public class Voluntario implements Serializable {

    private int id;
    private Usuario usuario;
    private Refugio refugio;
    private String tarea;
    private String descripcion;

    public Voluntario() {
    }

    public Voluntario(int id, Usuario usuario, Refugio refugio, String tarea, String descripcion) {
        this.id = id;
        this.usuario = usuario;
        this.refugio = refugio;
        this.tarea = tarea;
        this.descripcion = Voluntario.this.descripcion;
    }

    public Voluntario(Usuario usuario, Refugio refugio, String tarea, String descripcion) {
        this.usuario = usuario;
        this.refugio = refugio;
        this.tarea = tarea;
        this.descripcion = Voluntario.this.descripcion;
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

    public Refugio getRefugio() {
        return refugio;
    }

    public void setRefugio(Refugio refugio) {
        this.refugio = refugio;
    }

    public String getHorarioDesde() {
        return tarea;
    }

    public void setHorarioDesde(String tarea) {
        this.tarea = tarea;
    }

    public String getHorarioHasta() {
        return descripcion;
    }

    public void setHorarioHasta(String descripcion) {
        this.descripcion = Voluntario.this.descripcion;
    }

    @NonNull
    @Override
    public String toString() {
        return "Usuario {"/* +
                "nombre='" + Usuario.getNombre() + '\'' +
                ", apellido='" + Usuario.getApellido() + '\'' +
                '}'*/;
    }
}

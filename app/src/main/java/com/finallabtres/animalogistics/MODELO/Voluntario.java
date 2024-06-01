package com.finallabtres.animalogistics.MODELO;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.lang.String;

public class Voluntario implements Serializable {

    private int id;
    private Usuario usuario;
    private Refugio refugio;

    private Tarea tarea;

    public Voluntario() {
    }

    public Voluntario(int id, Usuario usuario, Refugio refugio, Tarea tarea) {
        this.id = id;
        this.usuario = usuario;
        this.refugio = refugio;
        this.tarea = tarea;
    }

    public Voluntario(Usuario usuario, Refugio refugio, Tarea tarea) {
        this.usuario = usuario;
        this.refugio = refugio;
        this.tarea = tarea;
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

    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
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

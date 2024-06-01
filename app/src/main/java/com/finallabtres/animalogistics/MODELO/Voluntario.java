package com.finallabtres.animalogistics.MODELO;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.lang.String;

public class Voluntario implements Serializable {

    private int id;
    private Usuario usuario;
    private Refugio refugio;

    public Voluntario() {
    }

    public Voluntario(int id, Usuario usuario, Refugio refugio) {
        this.id = id;
        this.usuario = usuario;
        this.refugio = refugio;
    }

    public Voluntario(Usuario usuario, Refugio refugio) {
        this.usuario = usuario;
        this.refugio = refugio;
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


    @NonNull
    @Override
    public String toString() {
        return "Usuario {"/* +
                "nombre='" + Usuario.getNombre() + '\'' +
                ", apellido='" + Usuario.getApellido() + '\'' +
                '}'*/;
    }
}

package com.finallabtres.animalogistics.MODELO;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.lang.String;

public class Voluntario implements Serializable {

    private int id;
    private Usuario usuario;
    private Refugio refugio;
    private String cargo;
    public String horarioDesde;
    public String horarioHasta;


    public Voluntario() {
    }

    public Voluntario(int id, Usuario usuario, Refugio refugio, String cargo, String horarioDesde, String horarioHasta) {
        this.id = id;
        this.usuario = usuario;
        this.refugio = refugio;
        this.cargo = cargo;
        this.horarioDesde = horarioDesde;
        this.horarioHasta = horarioHasta;
    }

    public Voluntario(Usuario usuario, Refugio refugio, String cargo, String horarioDesde, String horarioHasta) {
        this.usuario = usuario;
        this.refugio = refugio;
        this.cargo = cargo;
        this.horarioDesde = horarioDesde;
        this.horarioHasta = horarioHasta;
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getHorarioDesde() {
        return horarioDesde;
    }

    public void setHorarioDesde(String horarioDesde) {
        this.horarioDesde = horarioDesde;
    }

    public String getHorarioHasta() {
        return horarioHasta;
    }

    public void setHorarioHasta(String horarioHasta) {
        this.horarioHasta = horarioHasta;
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

package com.finallabtres.animalogistics.MODELO;

import java.io.Serializable;

public class Tarea  implements Serializable {

    private int id;
    private Voluntario voluntario;
    private String actividad;
    private String descripcion;

    public Tarea(int id, Voluntario voluntario, String actividad, String descripcion) {
        this.id = id;
        this.voluntario = voluntario;
        this.actividad = actividad;
        this.descripcion = descripcion;
    }

    public Tarea(Voluntario voluntario, String actividad, String descripcion) {
        this.voluntario = voluntario;
        this.actividad = actividad;
        this.descripcion = descripcion;
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
}

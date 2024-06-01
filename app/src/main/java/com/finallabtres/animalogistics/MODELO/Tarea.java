package com.finallabtres.animalogistics.MODELO;

import java.io.Serializable;

public class Tarea  implements Serializable {

    private int id;

    private String actividad;
    private String descripcion;

    public Tarea(int id, String actividad, String descripcion) {
        this.id = id;

        this.actividad = actividad;
        this.descripcion = descripcion;
    }

    public Tarea(String actividad, String descripcion) {

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
}

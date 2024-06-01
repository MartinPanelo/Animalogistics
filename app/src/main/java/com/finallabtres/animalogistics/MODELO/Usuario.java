package com.finallabtres.animalogistics.MODELO;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable {

    private int id;
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String contrasena;
    private String correo;
    private String fotoUrl;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String apellido, String dni, String telefono, String contrasena, String correo, String fotoUrl) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.contrasena = contrasena;
        this.correo = correo;
        this.fotoUrl = fotoUrl;
    }
    public Usuario(String nombre, String apellido, String dni, String telefono, String contrasena, String correo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.contrasena = contrasena;
        this.correo = correo;

    }

    public Usuario(String nombre, String apellido, String dni, String telefono, String contrasena, String correo, String fotoUrl) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.contrasena = contrasena;
        this.correo = correo;
        this.fotoUrl = fotoUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        correo = correo;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Usuario that = (Usuario) obj;
        return id == that.id;
    }

    @NonNull
    @Override
    public String toString() {
        return "Usuario {" +
                "nombre='" + getNombre() + '\'' +
                ", apellido='" + getApellido() + '\'' +
                '}';
    }
}

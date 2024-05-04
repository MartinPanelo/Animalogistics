package com.finallabtres.animalogistics.MODELO;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable {

    private int Id;
    private String Nombre;
    private String Apellido;
    private String DNI;
    private String Telefono;
    private String Contrasena;
    private String Correo;
    private String FotoUrl;

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String DNI, String telefono, String contrasena, String correo) {

        this.Nombre = nombre;
        this.Apellido = apellido;
        this.DNI = DNI;
        this.Telefono = telefono;
        this.Contrasena = contrasena;
        this.Correo = correo;
    }
    public Usuario(int id, String nombre, String apellido, String DNI, String telefono, String contrasena, String correo, String fotoUrl) {
        this.Id = id;
        this.Nombre = nombre;
        this.Apellido = apellido;
        this.DNI = DNI;
        this.Telefono = telefono;
        this.Contrasena = contrasena;
        this.Correo = correo;
        this.FotoUrl = fotoUrl;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String contrasena) {
        Contrasena = contrasena;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getFotoUrl() {
        return FotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        FotoUrl = fotoUrl;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.Id);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Usuario that = (Usuario) obj;
        return Id == that.Id;
    }

    @NonNull
    @Override
    public String toString() {
        return "Usuario {" +
                "nombre='" + Nombre + '\'' +
                ", apellido='" + Apellido + '\'' +
                '}';
    }
}

package com.finallabtres.animalogistics.MODELO;

import java.io.Serializable;

public class UsuarioLogin implements Serializable {

    private String Correo;

    private String Contrasena;

    public UsuarioLogin() {};


    public UsuarioLogin(String correo, String contrasena) {
        Correo = correo;
        Contrasena = contrasena;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String contrasena) {
        Contrasena = contrasena;
    }

    @Override
    public String toString() {
        return "UsuarioLogin{" +
                "Correo='" + Correo + '\'' +
                ", Contrasena='" + Contrasena + '\'' +
                '}';
    }
}

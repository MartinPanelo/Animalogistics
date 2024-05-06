package com.finallabtres.animalogistics.MODELO;

import java.io.Serializable;

public class UsuarioLogin implements Serializable {

    private String correo;

    private String contrasena;

    public UsuarioLogin() {};


    public UsuarioLogin(String correo, String contrasena) {
        correo = correo;
        contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        contrasena = contrasena;
    }

    @Override
    public String toString() {
        return "UsuarioLogin{" +
                "Correo='" + correo + '\'' +
                ", Contrasena='" + contrasena + '\'' +
                '}';
    }
}

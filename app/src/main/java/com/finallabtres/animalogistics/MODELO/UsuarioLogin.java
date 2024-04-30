package com.finallabtres.animalogistics.MODELO;

import java.io.Serializable;

public class UsuarioLogin implements Serializable {

    private String Correo;

    private String Contraseña;

    public UsuarioLogin() {};


    public UsuarioLogin(String correo, String contraseña) {
        Correo = correo;
        Contraseña = contraseña;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "UsuarioLogin{" +
                "Correo='" + Correo + '\'' +
                ", Contraseña='" + Contraseña + '\'' +
                '}';
    }
}

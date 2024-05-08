package com.finallabtres.animalogistics.MODELO;

import java.io.Serializable;

public class Refugio implements Serializable {

    private int id;
    private Usuario usuario;
    private String nombre;
    private String direccion;
    private String descripcion;
    private String telefono;
    private Double gpsy;
    private Double gpsx;
    private int gpsRango;
    private String bannerUrl;

    public Refugio() {
    }

    public Refugio(int id, Usuario usuario, String nombre, String direccion, String descripcion, String telefono, Double gpsy, Double gpsx, int gpsRango, String bannerUrl) {
        this.id = id;
        this.usuario = usuario;
        this.nombre = nombre;
        this.direccion = direccion;
        this.descripcion = descripcion;
        this.telefono = telefono;
        this.gpsy = gpsy;
        this.gpsx = gpsx;
        this.gpsRango = gpsRango;
        this.bannerUrl = bannerUrl;
    }

    public Refugio(Usuario usuario, String nombre, String direccion, String descripcion, String telefono, Double gpsy, Double gpsx, int gpsRango, String bannerUrl) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.direccion = direccion;
        this.descripcion = descripcion;
        this.telefono = telefono;
        this.gpsy = gpsy;
        this.gpsx = gpsx;
        this.gpsRango = gpsRango;
        this.bannerUrl = bannerUrl;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Double getGpsy() {
        return gpsy;
    }

    public void setGpsy(Double gpsy) {
        this.gpsy = gpsy;
    }

    public Double getGpsx() {
        return gpsx;
    }

    public void setGpsx(Double gpsx) {
        this.gpsx = gpsx;
    }

    public void setRango(int gpsRango) {
        this.gpsRango = gpsRango;
    }

    public int getRango() {
        return gpsRango;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }
}

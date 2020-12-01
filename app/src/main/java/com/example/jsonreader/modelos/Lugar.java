package com.example.jsonreader.modelos;

public class Lugar {
    private String nombre;
    private String tipo;
    private String descripcion;
    private String municipio;
    private String direccion;
    private String telefono;

    public Lugar(String nombre, String tipo, String descripcion, String municipio, String direccion, String telefono) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.municipio = municipio;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Lugar() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Lugar{" +
                "nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", municipio='" + municipio + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}

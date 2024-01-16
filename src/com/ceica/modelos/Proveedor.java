package com.ceica.modelos;

public class Proveedor {
    private int id;
    private String nombre, cif, direccion, localidad, provincia;

    public Proveedor() {
    }

    public Proveedor(String nombre, String cif) {
        this.nombre = nombre;
        this.cif = cif;
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

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return "\nProveedor{" +
                "ID=" + id +
                ", Nombre='" + nombre + '\'' +
                ", CIF='" + cif + '\'' +
                ", Dirección='" + direccion + '\'' +
                ", Localidad='" + localidad + '\'' +
                ", Provincia='" + provincia + '\'' +
                '}';
    }

}

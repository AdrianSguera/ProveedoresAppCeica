package com.ceica.modelos;

import com.ceica.bbdd.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Proveedor extends ModeloBase {
    private static int idProveedor = 0;
    private int id;
    private String nombre, cif, direccion, localidad, provincia;

    public Proveedor() {
        this.id = idProveedor++;
    }

    public Proveedor(String nombre, String cif) {
        this.id = idProveedor++;
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

    public static List<Proveedor> getProveedoresBD() {
        List<Proveedor> proveedorList = new ArrayList<>();
        List<Object> objectList = new Proveedor().leerTodos();
        for (Object obj : objectList) {
            Object[] objects = (Object[]) obj;
            Proveedor proveedor = new Proveedor();
            proveedor.setId((int) objects[0]);
            proveedor.setCif((String) objects[1]);
            proveedor.setNombre((String) objects[2]);
            proveedor.setDireccion((String) objects[3]);
            proveedor.setLocalidad((String) objects[4]);
            proveedor.setProvincia((String) objects[5]);
            proveedorList.add(proveedor);
        }
        return proveedorList;
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

    @Override
    protected String getNombreTabla() {
        return "proveedores";
    }
}

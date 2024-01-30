package com.ceica.modelos;

import com.ceica.bbdd.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Categoria extends ModeloBase {
    private int id;
    private String nombre;

    public Categoria(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Categoria() {

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

    public static List<Categoria> getCategoriasBD() {
        List<Categoria> categoriaList = new ArrayList<>();
        List<Object> objectList = new Categoria().leerTodos();
        for (Object obj : objectList) {
            Object[] objects = (Object[]) obj;
            Categoria categoria = new Categoria();
            categoria.setId((int) objects[0]);
            categoria.setNombre((String) objects[1]);
            categoriaList.add(categoria);
        }
        return categoriaList;
    }

    @Override
    public String toString() {
        return id + " " + nombre + "\n";
    }

    @Override
    protected String getNombreTabla() {
        return "categorias";
    }
}

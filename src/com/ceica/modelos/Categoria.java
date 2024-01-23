package com.ceica.modelos;

import com.ceica.bbdd.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Categoria {
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

    public static List<Categoria> getCategoriasBD(){
        List<Categoria> categoriaList = new ArrayList<>();
        Connection connection = Conexion.conectar();
        String consulta = "select * from categorias";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(consulta);
            while (resultSet.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(resultSet.getInt("id"));
                categoria.setNombre(resultSet.getString("nombre"));
                categoriaList.add(categoria);
            }
        } catch (SQLException e) {
            return categoriaList;
        }
        return categoriaList;
    }
    @Override
    public String toString() {
        return id + " " + nombre + "\n";
    }
}

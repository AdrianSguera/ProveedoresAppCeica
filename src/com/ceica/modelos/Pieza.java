package com.ceica.modelos;

import com.ceica.bbdd.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Pieza extends ModeloBase{
    private static int idPieza = 0;
    private int id;
    private String nombre, color;
    private Double precio;
    private Categoria categoria;

    public Pieza() {
        this.id = idPieza++;
    }

    public Pieza(String nombre, Color color, Double precio) {
        this.id = idPieza++;
        this.nombre = nombre;
        this.color = color.toString();
        this.precio = precio;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    public static List<Pieza> getPiezasBD(){
        List<Pieza> piezaList = new ArrayList<>();
        Connection connection = Conexion.conectar();
        String consulta = "select * from piezas inner join categorias on categorias.id=piezas.idcategoria";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(consulta);
            while (resultSet.next()) {
                Pieza pieza = new Pieza();
                Categoria categoria = new Categoria();
                pieza.setId(resultSet.getInt("id"));
                pieza.setNombre(resultSet.getString("nombre"));
                pieza.setColor(resultSet.getString("color"));
                pieza.setPrecio(resultSet.getDouble("precio"));
                categoria.setId(resultSet.getInt("idcategoria"));
                categoria.setNombre(resultSet.getString(7));
                pieza.setCategoria(categoria);
                piezaList.add(pieza);
            }
            return piezaList;
        } catch (SQLException e) {
            return piezaList;
        } finally {
            try {
                connection.close();
            } catch (SQLException ignored) {
            }
        }
    }

    @Override
    public String toString() {
        return "\nPieza{" +
                "ID=" + id +
                ", Nombre='" + nombre + '\'' +
                ", Color='" + color + '\'' +
                ", Categoría='" + categoria + '\'' +
                ", Precio=" + precio +
                '}';
    }

    @Override
    protected String getNombreTabla() {
        return "piezas";
    }
}

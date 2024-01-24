package com.ceica.modelos;

import com.ceica.bbdd.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Pieza {
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

    public static boolean insertar(Pieza pieza, int idcategoria) {
        Connection connection = Conexion.conectar();
        String consulta = "insert into piezas (nombre,color,precio,idcategoria) values (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(consulta);
            preparedStatement.setString(1, pieza.getNombre());
            preparedStatement.setString(2, pieza.getColor());
            preparedStatement.setDouble(3, pieza.getPrecio());
            preparedStatement.setInt(4, idcategoria);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException ignored) {
            }
        }
    }

    public static boolean eliminar(Pieza pieza) {
        Connection connection = Conexion.conectar();
        String consulta = "DELETE FROM piezas WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(consulta);
            preparedStatement.setInt(1, pieza.getId());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException ignored) {
            }
        }
    }

    public static boolean modificarPrecio(int id, Double dato) {
        Connection connection = Conexion.conectar();
        String consulta = "UPDATE piezas SET precio = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(consulta);
            preparedStatement.setDouble(1, dato);
            preparedStatement.setInt(2, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            return false;
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
}

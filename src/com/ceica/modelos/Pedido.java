package com.ceica.modelos;

import com.ceica.bbdd.Conexion;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido extends ModeloBase{
    private static int idPedido = 0;
    private int id, cantidad;
    private Proveedor proveedor;
    private Pieza pieza;
    private LocalDate fecha;

    public Pedido() {
        this.id = idPedido++;
    }

    public Pedido(Proveedor proveedor, Pieza pieza) {
        this.id = idPedido++;
        this.proveedor = proveedor;
        this.pieza = pieza;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Pieza getPieza() {
        return pieza;
    }

    public void setPieza(Pieza pieza) {
        this.pieza = pieza;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public static List<Pedido> getPedidosBD() {
        List<Pedido> pedidoList = new ArrayList<>();
        Connection connection = Conexion.conectar();
        String consulta = """
                select * from suministros
                inner join proveedores on proveedores.id=suministros.idproveedor
                inner join piezas on piezas.id=suministros.idpieza
                inner join categorias on piezas.idcategoria=categorias.id""";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(consulta);
            while (resultSet.next()) {
                Pedido pedido = new Pedido();
                Proveedor proveedor = new Proveedor();
                Pieza pieza = new Pieza();
                Categoria categoria = new Categoria();
                pedido.setId(resultSet.getInt("id"));
                pedido.setCantidad(resultSet.getInt("cantidad"));
                pedido.setFecha(resultSet.getDate("fecha").toLocalDate());
                pedido.setProveedor(proveedor);
                pedido.setPieza(pieza);
                proveedor.setId(resultSet.getInt("idproveedor"));
                proveedor.setNombre(resultSet.getString("nombre"));
                proveedor.setDireccion(resultSet.getString("direccion"));
                proveedor.setLocalidad(resultSet.getString("localidad"));
                proveedor.setProvincia(resultSet.getString("provincia"));
                pieza.setId(resultSet.getInt("idpieza"));
                pieza.setNombre(resultSet.getString(12));
                pieza.setColor(resultSet.getString("color"));
                pieza.setPrecio(resultSet.getDouble("precio"));
                pieza.setCategoria(categoria);
                categoria.setId(resultSet.getInt("idcategoria"));
                categoria.setNombre(resultSet.getString(17));
                pedidoList.add(pedido);
            }
            return pedidoList;
        } catch (SQLException e) {
            return pedidoList;
        } finally {
            try {
                connection.close();
            } catch (SQLException ignored) {
            }
        }
    }

    @Override
    public String toString() {
        return "\nPedido{" +
                "ID=" + id +
                ", Cantidad=" + cantidad +
                ", Proveedor=" + proveedor +
                ", Pieza=" + pieza +
                ", Fecha=" + fecha +
                '}';
    }

    @Override
    protected String getNombreTabla() {
        return "suministros";
    }
}

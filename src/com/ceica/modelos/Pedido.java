package com.ceica.modelos;

import com.ceica.bbdd.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        List<Object> objectList = new Pedido().leerTodos("""
                inner join proveedores on proveedores.id=suministros.idproveedor
                inner join piezas on piezas.id=suministros.idpieza
                inner join categorias on piezas.idcategoria=categorias.id""");
        for (Object obj : objectList) {
            Object[] objects = (Object[]) obj;
            Pieza pieza = new Pieza();
            Categoria categoria = new Categoria();
            Pedido pedido = new Pedido();
            Proveedor proveedor = new Proveedor();
            pedido.setId((int) objects[0]);
            pedido.setCantidad((int) objects[3]);
            LocalDateTime fecha = (LocalDateTime) objects[4];
            LocalDate fechaLD = fecha.toLocalDate();
            pedido.setFecha(fechaLD);
            pieza.setId((int) objects[1]);
            pieza.setNombre((String) objects[12]);
            pieza.setColor((String) objects[13]);
            pieza.setPrecio((Double) objects[14]);
            categoria.setId((int) objects[15]);
            categoria.setNombre((String) objects[17]);
            pieza.setCategoria(categoria);
            proveedor.setId((int) objects[2]);
            proveedor.setCif((String) objects[6]);
            proveedor.setNombre((String) objects[7]);
            proveedor.setDireccion((String) objects[8]);
            proveedor.setLocalidad((String) objects[9]);
            proveedor.setProvincia((String) objects[10]);
            pedido.setPieza(pieza);
            pedido.setProveedor(proveedor);
            pedidoList.add(pedido);
        }
        return pedidoList;
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

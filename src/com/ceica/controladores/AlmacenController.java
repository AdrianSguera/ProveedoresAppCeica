package com.ceica.controladores;


import com.ceica.modelos.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlmacenController {
    private List<Proveedor> proveedorList;
    private List<Pieza> piezaList;
    private List<Pedido> pedidoList;
    private List<Categoria> categoriaList;

    public AlmacenController() {
        this.proveedorList = new ArrayList<>();
        this.piezaList = new ArrayList<>();
        this.pedidoList = new ArrayList<>();
        this.categoriaList = new ArrayList<>();
        proveedorList = Proveedor.getProveedoresBD();
        piezaList = Pieza.getPiezasBD();
        pedidoList = Pedido.getPedidosBD();
        categoriaList = Categoria.getCategoriasBD();
    }

    @Override
    public String toString() {
        return "AlmacenController\n{" +
                "proveedorList=" + proveedorList + "\n" +
                "piezaList=" + piezaList + "\n" +
                "pedidoList=" + pedidoList + "\n" +
                "categoriaList=" + categoriaList +
                '}';
    }

    public boolean nuevoProveedor(String cif, String nombre, String direccion, String localidad, String provincia){
        if (new Proveedor().insertar("(cif,nombre,direccion,localidad,provincia) values (?,?,?,?,?)", cif, nombre, direccion, localidad, provincia)) {
            proveedorList = Proveedor.getProveedoresBD();
            return true;
        } else
            return false;
    }

    public boolean borrarProveedor(String cif) {
        if (new Proveedor().eliminar("cif=?", cif)) {
            proveedorList = Proveedor.getProveedoresBD();
            return true;
        } else
            return false;
    }

    private static LocalDate obtenerFecha(String fecha) {
        int dia = Integer.parseInt(fecha.substring(0, 2));
        int mes = Integer.parseInt(fecha.substring(3, 5));
        int ano = Integer.parseInt(fecha.substring(6, 10));
        return LocalDate.of(ano, mes, dia);
    }

    public boolean modificarNombreProveedor(String cif, String dato) {
        if (new Proveedor().modificar("nombre = ? WHERE cif = ?", dato, cif)) {
            proveedorList = Proveedor.getProveedoresBD();
            return true;
        } else
            return false;
    }

    public boolean modificarDireccionProveedor(String cif, String dato) {
        if (new Proveedor().modificar("direccion = ? WHERE cif = ?", dato, cif)) {
            proveedorList = Proveedor.getProveedoresBD();
            return true;
        } else
            return false;
    }

    public boolean modificarLocalidadProveedor(String cif, String dato) {
        if (new Proveedor().modificar("localidad = ? WHERE cif = ?", dato, cif)) {
            proveedorList = Proveedor.getProveedoresBD();
            return true;
        } else
            return false;
    }

    public boolean modificarProvinciaProveedor(String cif, String dato) {
        if (new Proveedor().modificar("provincia = ? WHERE cif = ?", dato, cif)) {
            proveedorList = Proveedor.getProveedoresBD();
            return true;
        } else
            return false;
    }

    public boolean nuevaPieza(String nombre, Color color, Double precio, int idcategoria) {
        if (new Pieza().insertar("(nombre,color,precio,idcategoria) values (?,?,?,?)", nombre, color, precio, idcategoria)) {
            piezaList = Pieza.getPiezasBD();
            return true;
        } else
            return false;
    }

    public boolean borrarPieza(int id) {
        if (new Pieza().eliminar("id = ?", id)) {
            piezaList = Pieza.getPiezasBD();
            return true;
        } else
            return false;
    }

    public boolean modificarPrecioPieza(int id, Double dato) {
        if (new Pieza().modificar("precio = ? WHERE id = ?", dato, id)) {
            piezaList = Pieza.getPiezasBD();
            return true;
        } else
            return false;
    }

    public String nuevoPedido(int cantidad, String cif, int idpieza) {
        Proveedor proveedor = getProveedorByCif(cif);
        if (new Pedido().insertar("(idpieza,idproveedor,cantidad) values (?,?,?)", idpieza, proveedor.getId(), cantidad)) {
            piezaList = Pieza.getPiezasBD();
            return "Operación realizada";
        } else
            return "No se pudo realizar la operación";
    }

    public boolean existeCif(String cif) {
        return proveedorList.stream().anyMatch(proveedor -> cif.equals(proveedor.getCif()));
    }

    public boolean existeId(int id) {
        return piezaList.stream().anyMatch(pieza -> id == pieza.getId());
    }

    private Pieza getPiezaById(int id) {
        return piezaList.stream()
                .filter(pieza -> id == pieza.getId())
                .findFirst().get();
    }

    public Proveedor getProveedorByCif(String cif) {
        return proveedorList.stream()
                .filter(proveedor -> cif.equals(proveedor.getCif()))
                .findFirst().get();
    }

    public Categoria getCategoriaById(int id) {
        return categoriaList.stream()
                .filter(categoria -> categoria.getId() == id)
                .findFirst().get();
    }

    public String getPedidosByPieza(int id) {
        return pedidoList.stream()
                .filter(pedido -> pedido.getPieza().getId() == id)
                .toList().toString();
    }
    public String getPedidosByProveedor(String cif) {
        return pedidoList.stream()
                .filter(pedido -> cif.equals(pedido.getProveedor().getCif()))
                .toList().toString();
    }

    public List<Proveedor> verProveedores() {
        return proveedorList;
    }

    public List<Pieza> verPiezas() {
        return piezaList;
    }
    public String mostrarCategorias(){
        return categoriaList.toString();
    }

    public boolean categoriaExiste(int idcategoria) {
        return categoriaList.stream()
                .anyMatch(categoria -> idcategoria == categoria.getId());
    }
}

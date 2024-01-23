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
        Proveedor proveedor = new Proveedor(nombre, cif);
        proveedor.setDireccion(direccion);
        proveedor.setLocalidad(localidad);
        proveedor.setProvincia(provincia);
        if (Proveedor.insertar(proveedor)) {
            proveedorList = Proveedor.getProveedoresBD();
            return true;
        } else
            return false;
    }

    public boolean borrarProveedor(String cif) {
        if (Proveedor.eliminar(getProveedorByCif(cif))) {
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
        if (Proveedor.modificarNombre(cif, dato)) {
            proveedorList = Proveedor.getProveedoresBD();
            return true;
        } else
            return false;
    }

    public boolean modificarDireccionProveedor(String cif, String dato) {
        if (Proveedor.modificarDireccion(cif, dato)) {
            proveedorList = Proveedor.getProveedoresBD();
            return true;
        } else
            return false;
    }

    public boolean modificarLocalidadProveedor(String cif, String dato) {
        if (Proveedor.modificarLocalidad(cif, dato)) {
            proveedorList = Proveedor.getProveedoresBD();
            return true;
        } else
            return false;
    }

    public boolean modificarProvinciaProveedor(String cif, String dato) {
        if (Proveedor.modificarProvincia(cif, dato)) {
            proveedorList = Proveedor.getProveedoresBD();
            return true;
        } else
            return false;
    }

    public boolean nuevaPieza(String nombre, Color color, Double precio, int idcategoria) {
        Pieza pieza = new Pieza(nombre, color, precio);
        pieza.setCategoria(getCategoriaById(idcategoria));
        if (Pieza.insertar(pieza, idcategoria)) {
            piezaList = Pieza.getPiezasBD();
            return true;
        } else
            return false;
    }

    public boolean borrarPieza(int id) {
        if (Pieza.eliminar(getPiezaById(id))) {
            piezaList = Pieza.getPiezasBD();
            return true;
        } else
            return false;
    }

    public boolean modificarNombrePieza(int id, String dato) {
        for (Pieza pieza : piezaList)
            if (id == pieza.getId()) {
                pieza.setNombre(dato);
                return true;
            }
        return false;
    }

    public boolean modificarColorPieza(int id, String dato) {
        for (Pieza pieza : piezaList)
            if (id == pieza.getId()) {
                pieza.setColor(dato);
                return true;
            }
        return false;
    }

    public boolean modificarPrecioPieza(int id, Double dato) {
        if (Pieza.modificarPrecio(id, dato)) {
            piezaList = Pieza.getPiezasBD();
            return true;
        } else
            return false;
    }

    public boolean modificarCategoriaPieza(int id) {
        for (Pieza pieza : piezaList)
            if (id == pieza.getId()) {
                pieza.setCategoria(getCategoriaById(id));
                return true;
            }
        return false;
    }

    public String nuevoPedido(int cantidad, String cif, int idpieza) {
        int idproveedor = getProveedorByCif(cif).getId();
        if (Pedido.insertar(cantidad,idproveedor,idpieza)) {
            pedidoList = Pedido.getPedidosBD();
            return "Operación realizada";
        } else
            return "No se pudo realizar la operación";
    }

    public boolean existeCif(String cif) {
        for (Proveedor proveedor : proveedorList)
            if (cif.equals(proveedor.getCif()))
                return true;
        return false;
    }

    public boolean existeId(int id) {
        for (Pieza pieza : piezaList)
            if (id == pieza.getId())
                return true;
        return false;
    }

    private Pieza getPiezaById(int id) {
        return piezaList.stream()
                .filter(pieza -> id == pieza.getId())
                .findFirst().get();
    }

    private Proveedor getProveedorByCif(String cif) {
        return proveedorList.stream()
                .filter(proveedor -> cif.equals(proveedor.getCif()))
                .findFirst().get();
    }

    private Categoria getCategoriaById(int id) {
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

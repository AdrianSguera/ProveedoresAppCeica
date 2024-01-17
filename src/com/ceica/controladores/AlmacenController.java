package com.ceica.controladores;


import com.ceica.modelos.Categoria;
import com.ceica.modelos.Pedido;
import com.ceica.modelos.Pieza;
import com.ceica.modelos.Proveedor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlmacenController {
    private List<Proveedor> proveedorList;
    private List<Pieza> piezaList;
    private List<Pedido> pedidoList;

    public AlmacenController() {
        this.proveedorList = new ArrayList<>();
        this.piezaList = new ArrayList<>();
        this.pedidoList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "AlmacenController\n{" +
                "proveedorList=" + proveedorList + "\n" +
                "piezaList=" + piezaList + "\n" +
                "pedidoList=" + pedidoList +
                '}';
    }

    public boolean nuevoProveedor(String cif, String nombre, String direccion, String localidad, String provincia){
        Proveedor proveedor = new Proveedor(nombre, cif);
        proveedor.setDireccion(direccion);
        proveedor.setLocalidad(localidad);
        proveedor.setProvincia(provincia);
        return proveedorList.add(proveedor);
    }

    public boolean borrarProveedor(String cif) {
        return proveedorList.removeIf(proveedor -> cif.equals(proveedor.getCif()));
    }

    private static LocalDate obtenerFecha(String fecha) {
        int dia = Integer.parseInt(fecha.substring(0, 2));
        int mes = Integer.parseInt(fecha.substring(3, 5));
        int ano = Integer.parseInt(fecha.substring(6, 10));
        return LocalDate.of(ano, mes, dia);
    }

    public boolean modificarNombreProveedor(String cif, String dato) {
        for (Proveedor proveedor : proveedorList) {
            if (cif.equals(proveedor.getCif())) {
                proveedor.setNombre(dato);
                return true;
            }
        }
        return false;
    }

    public boolean modificarDireccionProveedor(String cif, String dato) {
        for (Proveedor proveedor : proveedorList) {
            if (cif.equals(proveedor.getCif())) {
                proveedor.setDireccion(dato);
                return true;
            }
        }
        return false;
    }

    public boolean modificarLocalidadProveedor(String cif, String dato) {
        for (Proveedor proveedor : proveedorList) {
            if (cif.equals(proveedor.getCif())) {
                proveedor.setLocalidad(dato);
                return true;
            }
        }
        return false;
    }

    public boolean modificarProvinciaProveedor(String cif, String dato) {
        for (Proveedor proveedor : proveedorList) {
            if (cif.equals(proveedor.getCif())) {
                proveedor.setProvincia(dato);
                return true;
            }
        }
        return false;
    }

    public boolean nuevaPieza(int id, String nombre, String color, Double precio, Categoria categoria) {
        Pieza pieza = new Pieza(nombre, color, precio);
        pieza.setCategoria(categoria);
        pieza.setId(id);
        return piezaList.add(pieza);
    }

    public boolean borrarPieza(int id) {
        return piezaList.removeIf(pieza -> id == pieza.getId());
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
        for (Pieza pieza : piezaList)
            if (id == pieza.getId()) {
                pieza.setPrecio(dato);
                return true;
            }
        return false;
    }

    public boolean modificarCategoriaPieza(int id, Categoria dato) {
        for (Pieza pieza : piezaList)
            if (id == pieza.getId()) {
                pieza.setCategoria(dato);
                return true;
            }
        return false;
    }

    public boolean nuevoPedido(int id, int cantidad, String cif, int idpieza, String fecha) {
        Pedido pedido = new Pedido(buscarPorCif(cif), buscarPorId(idpieza));
        pedido.setId(id);
        pedido.setCantidad(cantidad);
        pedido.setFecha(obtenerFecha(fecha));
        pedido.setProveedor(buscarPorCif(cif));
        pedido.setPieza(buscarPorId(idpieza));
        return pedidoList.add(pedido);
    }

    public boolean existeCif(String cif) {
        for (Proveedor proveedor : proveedorList)
            if (cif.equals(proveedor.getCif()))
                return true;
        return false;
    }

    public boolean existeID(int id) {
        for (Pieza pieza : piezaList)
            if (id == pieza.getId())
                return true;
        return false;
    }

    public Proveedor buscarPorCif(String cif) {
        Proveedor prov = new Proveedor();
        for (Proveedor proveedor : proveedorList)
            if (cif.equals(proveedor.getCif())) {
                proveedor = prov;
                break;
            }
        return prov;
    }

    public Pieza buscarPorId(int id) {
        Pieza p = new Pieza();
        for (Pieza pieza : piezaList)
            if (id == pieza.getId()) {
                pieza = p;
                break;
            }
        return p;
    }

}

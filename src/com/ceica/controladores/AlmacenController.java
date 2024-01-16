package com.ceica.controladores;


import com.ceica.modelos.Pedido;
import com.ceica.modelos.Pieza;
import com.ceica.modelos.Proveedor;

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
}

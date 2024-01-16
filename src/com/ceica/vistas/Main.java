package com.ceica.vistas;

import com.ceica.controladores.AlmacenController;
import com.ceica.modelos.Proveedor;

public class Main {
    public static void main(String[] args) {
        AlmacenController almacenController = new AlmacenController();

        almacenController.nuevoProveedor("a","nom0","dir0","loc0","prov0");
        almacenController.nuevoProveedor("b","nom1","dir1","loc1","prov1");
        System.out.println(almacenController.toString());
        System.out.println(almacenController.borrarProveedor("b"));
        System.out.println(almacenController.toString());
        System.out.println(almacenController.borrarProveedor("c"));
    }
}
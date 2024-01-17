package com.ceica.vistas;

import com.ceica.controladores.AlmacenController;
import com.ceica.modelos.Categoria;
import com.ceica.modelos.Pieza;
import com.ceica.modelos.Proveedor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AlmacenController almacenController = new AlmacenController();
        Scanner leer = new Scanner(System.in);
        String op;
        do {
            System.out.println("""
                    Elija una opción:
                    1: Nuevo proveedor
                    2: Modificar datos proveedor
                    3: Eliminar proveedor
                    4: Nueva pieza
                    5: Modificar datos pieza
                    6: Eliminar pieza
                    7: Nuevo pedido
                    8: Mostrar información del almacén
                    11: Salir""");
            op = leer.nextLine();
            switch (op){
                case "1":
                    System.out.println("""
                            -------------------------------------
                                       Nuevo proveedor
                            -------------------------------------""");
                    if (nuevoProveedor(leer, almacenController))
                        System.out.println("Operación realizada\n");
                    else
                        System.out.println("No se pudo realizar la operación");
                    break;
                case "2":
                    System.out.println("""
                            -------------------------------------
                                      Modificar proveedor
                            -------------------------------------""");
                    if (modificarDatosProveedor(leer,almacenController))
                        System.out.println("Operación realizada\n");
                    else
                        System.out.println("No se pudo realizar la operación");
                    break;
                case "3":
                    System.out.println("""
                            -------------------------------------
                                      Eliminar proveedor
                            -------------------------------------""");
                    if (borrarProveedor(leer,almacenController))
                        System.out.println("Operación realizada\n");
                    else
                        System.out.println("No se pudo realizar la operación");
                    break;
                case "4":
                    System.out.println("""
                            -------------------------------------
                                         Nueva pieza
                            -------------------------------------""");
                    if (nuevaPieza(leer,almacenController))
                        System.out.println("Operación realizada\n");
                    else
                        System.out.println("No se pudo realizar la operación");
                    break;
                case "5":
                    System.out.println("""
                            -------------------------------------
                                       Modificar pieza
                            -------------------------------------""");
                    if (modificarDatosPieza(leer,almacenController))
                        System.out.println("Operación realizada\n");
                    else
                        System.out.println("No se pudo realizar la operación");
                    break;
                case "6":
                    System.out.println("""
                            -------------------------------------
                                       Eliminar pieza
                            -------------------------------------""");
                    if (borrarPieza(leer,almacenController))
                        System.out.println("Operación realizada\n");
                    else
                        System.out.println("No se pudo realizar la operación");
                    break;
                case "7":
                    System.out.println("""
                            -------------------------------------
                                        Nuevo pedido
                            -------------------------------------""");
                    if (nuevoPedido(leer,almacenController))
                        System.out.println("Operación realizada\n");
                    else
                        System.out.println("No se pudo realizar la operación");
                    break;
                case "8":
                    System.out.println("""
                            -------------------------------------
                                Mostrar información del almacén
                            -------------------------------------""");
                    System.out.println(almacenController.toString() + "\n");
                    break;
                case "11":
                    System.out.println("""
                            -------------------------------------
                                       Fin del programa
                            -------------------------------------""");
                    break;
                default:
                    System.out.println("Opción incorrecta\n");
                    break;
            }
        } while (!op.equals("11"));
    }

    private static boolean nuevoPedido(Scanner leer, AlmacenController almacenController) {
        System.out.println("Ingrese CIF del proveedor");
        String cif = leer.nextLine();
        if (almacenController.existeCif(cif)){
            System.out.println("Ingrese id de la pieza");
            int idpieza = leer.nextInt();
            leer.nextLine();
            if (almacenController.existeID(idpieza)){
                System.out.println("Ingrese id");
                int id = leer.nextInt();
                leer.nextLine();
                System.out.println("Ingrese cantidad");
                int cantidad = leer.nextInt();
                leer.nextLine();
                System.out.println("Ingrese fecha (dd-mm-yyyy");
                String fecha = leer.nextLine();
                return almacenController.nuevoPedido(id, cantidad, cif, idpieza, fecha);
            }
        }
        return false;
    }

    private static boolean borrarPieza(Scanner leer, AlmacenController almacenController) {
        System.out.println("Ingrese id de la pieza");
        int id = leer.nextInt();
        return almacenController.borrarPieza(id);
    }

    private static boolean modificarDatosPieza(Scanner leer, AlmacenController almacenController) {
        System.out.println("Ingrese id de la pieza a modificar");
        int id = leer.nextInt();
        leer.nextLine();
        if(almacenController.existeID(id)){
            System.out.println("""
                Ingrese el dato a modificar:
                1: Nombre
                2: Color
                3: Precio
                4: Categoría
                """);
            int op = leer.nextInt();
            leer.nextLine();
            switch (op){
                case 1:
                    System.out.println("Ingrese el nuevo nombre");
                    String nombre = leer.nextLine();
                    return almacenController.modificarNombrePieza(id,nombre);
                case 2:
                    System.out.println("Ingrese el nuevo color");
                    String color = leer.nextLine();
                    return almacenController.modificarColorPieza(id, color);
                case 3:
                    System.out.println("Ingrese el nuevo precio");
                    Double precio = leer.nextDouble();
                    leer.nextLine();
                    return almacenController.modificarPrecioPieza(id, precio);
                case 4:
                    System.out.println("Ingrese nuevo id de categoría");
                    int idcategoria = leer.nextInt();
                    leer.nextLine();
                    System.out.println("Ingrese nuevo nombre de categoría");
                    String nomcategoria = leer.nextLine();
                    Categoria categoria = new Categoria(idcategoria,nomcategoria);
                    return almacenController.modificarCategoriaPieza(idcategoria, categoria);
                default:
                    System.out.println("Opción incorrecta");
                    return false;
            }
        }else
            System.out.println("No existe el id de categoría");
        return false;
    }

    private static boolean nuevaPieza(Scanner leer, AlmacenController almacenController) {
        System.out.println("Ingrese id");
        int id = leer.nextInt();
        leer.nextLine();
        System.out.println("Ingrese nombre");
        String nombre = leer.nextLine();
        System.out.println("Ingrese color");
        String color = leer.nextLine();
        System.out.println("Ingrese precio");
        Double precio = leer.nextDouble();
        leer.nextLine();
        System.out.println("Ingrese id de la categoría");
        int idcategoria = leer.nextInt();
        leer.nextLine();
        System.out.println("Ingrese nombre de la categoría");
        String nomcategoria = leer.nextLine();
        Categoria categoria = new Categoria(idcategoria, nomcategoria);
        return almacenController.nuevaPieza(id, nombre, color ,precio, categoria);
    }

    private static boolean borrarProveedor(Scanner leer, AlmacenController almacenController) {
        System.out.println("Ingrese CIF del proveedor a eliminar");
        String cif = leer.nextLine();
        return almacenController.borrarProveedor(cif);
    }

    private static boolean modificarDatosProveedor(Scanner leer, AlmacenController almacenController) {
        System.out.println("Ingrese CIF del proveedor a modificar");
        String cif = leer.nextLine();
        if(almacenController.existeCif(cif)){
            System.out.println("""
                Ingrese el dato a modificar:
                1: Nombre
                2: Dirección
                3: Localidad
                4: Provincia
                """);
            int op = leer.nextInt();
            leer.nextLine();
            switch (op){
                case 1:
                    System.out.println("Ingrese el nuevo nombre");
                    String nombre = leer.nextLine();
                    return almacenController.modificarNombreProveedor(cif,nombre);
                case 2:
                    System.out.println("Ingrese nueva dirección");
                    String direccion = leer.nextLine();
                    return almacenController.modificarDireccionProveedor(cif, direccion);
                case 3:
                    System.out.println("Ingrese nueva localidad");
                    String localidad = leer.nextLine();
                    return almacenController.modificarLocalidadProveedor(cif, localidad);
                case 4:
                    System.out.println("Ingrese nueva provincia");
                    String provincia = leer.nextLine();
                    return almacenController.modificarProvinciaProveedor(cif, provincia);
                default:
                    System.out.println("Opción incorrecta");
                    return false;
            }
        }else
            System.out.println("No existe el CIF");
        return false;
    }

    private static boolean nuevoProveedor(Scanner leer, AlmacenController almacenController) {
        System.out.println("Ingrese CIF");
        String cif = leer.nextLine();
        System.out.println("Ingrese nombre");
        String nombre = leer.nextLine();
        System.out.println("Ingrese localidad");
        String localidad = leer.nextLine();
        System.out.println("Ingrese dirección");
        String direccion = leer.nextLine();
        System.out.println("Ingrese provincia");
        String provincia = leer.nextLine();
        return almacenController.nuevoProveedor(cif,nombre,direccion,localidad,provincia);
    }
}
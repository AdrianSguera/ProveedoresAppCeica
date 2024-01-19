package com.ceica.vistas;

import com.ceica.controladores.AlmacenController;
import com.ceica.modelos.Color;

import java.util.Scanner;

import static com.ceica.controladores.LoginController.login;

public class Main {
    public static void main(String[] args) {
        AlmacenController almacenController = new AlmacenController();
        Scanner leer = new Scanner(System.in);
        System.out.println("""
        ACADEMIA© app
        ......Presione enter para continuar.....""");
        leer.nextLine();
        do {
            System.out.println("Ingrese usuario");
            String usuario = leer.nextLine();
            System.out.println("Ingrese contraseña");
            String contrasenia = leer.nextLine();
            if (login(usuario,contrasenia))
                menuPrincipalAlmacen(leer,almacenController);
            else
                System.out.println("Usuario o contraseña incorrectos\n");
        } while (true);

    }

    private static void menuPrincipalAlmacen(Scanner leer, AlmacenController almacenController) {
        int op;
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
                    9: Pedidos por pieza
                    10: Pedidos por proveedor
                    11: Cerrar sesión""");
            op = leer.nextInt();
            leer.nextLine();
            switch (op){
                case 1:
                    System.out.println("""
                            -------------------------------------
                                       Nuevo proveedor
                            -------------------------------------""");
                    if (nuevoProveedor(leer, almacenController))
                        System.out.println("Operación realizada\n");
                    else
                        System.out.println("No se pudo realizar la operación\n");
                    break;
                case 2:
                    System.out.println("""
                            -------------------------------------
                                      Modificar proveedor
                            -------------------------------------""");
                    if (modificarDatosProveedor(leer,almacenController))
                        System.out.println("Operación realizada\n");
                    else
                        System.out.println("No se pudo realizar la operación\n");
                    break;
                case 3:
                    System.out.println("""
                            -------------------------------------
                                      Eliminar proveedor
                            -------------------------------------""");
                    if (borrarProveedor(leer,almacenController))
                        System.out.println("Operación realizada\n");
                    else
                        System.out.println("No se pudo realizar la operación\n");
                    break;
                case 4:
                    System.out.println("""
                            -------------------------------------
                                         Nueva pieza
                            -------------------------------------""");
                    if (nuevaPieza(leer,almacenController))
                        System.out.println("Operación realizada\n");
                    else
                        System.out.println("No se pudo realizar la operación\n");
                    break;
                case 5:
                    System.out.println("""
                            -------------------------------------
                                    Modificar precio pieza
                            -------------------------------------""");
                    if (modificarPrecioPieza(leer,almacenController))
                        System.out.println("Operación realizada\n");
                    else
                        System.out.println("No se pudo realizar la operación\n");
                    break;
                case 6:
                    System.out.println("""
                            -------------------------------------
                                       Eliminar pieza
                            -------------------------------------""");
                    if (borrarPieza(leer,almacenController))
                        System.out.println("Operación realizada\n");
                    else
                        System.out.println("No se pudo realizar la operación\n");
                    break;
                case 7:
                    System.out.println("""
                            -------------------------------------
                                        Nuevo pedido
                            -------------------------------------""");
                    System.out.println(nuevoPedido(leer,almacenController) + "\n");
                    break;
                case 8:
                    System.out.println("""
                            -------------------------------------
                                Mostrar información del almacén
                            -------------------------------------""");
                    System.out.println(almacenController.toString() + "\n");
                    break;
                case 9:
                    System.out.println("""
                            -------------------------------------
                                      Pedidos por pieza
                            -------------------------------------""");
                    System.out.println(getPedidosByPieza(leer,almacenController));
                    break;
                case 10:
                    System.out.println("""
                            -------------------------------------
                                     Pedidos por proveedor
                            -------------------------------------""");
                    System.out.println(getPedidosByProveedor(leer,almacenController));
                    break;
                case 11:
                    System.out.println("""
                            -------------------------------------
                                       Sesión cerrada
                            -------------------------------------""");
                    break;
                default:
                    System.out.println("Opción incorrecta\n");
                    break;
            }
        } while (op != 11);
    }

    private static String getPedidosByProveedor(Scanner leer, AlmacenController almacenController) {
        System.out.println("Ingrese cif del proveedor");
        String cif = leer.nextLine();
        return almacenController.getPedidosByProveedor(cif);
    }

    private static String getPedidosByPieza(Scanner leer, AlmacenController almacenController) {
        System.out.println("Ingrese id de la pieza");
        int id = leer.nextInt();
        leer.nextLine();
        return almacenController.getPedidosByPieza(id);
    }

    private static String nuevoPedido(Scanner leer, AlmacenController almacenController) {
        System.out.println("Ingrese CIF del proveedor");
        String cif = leer.nextLine();
        System.out.println("Ingrese id de la pieza");
        int idpieza = leer.nextInt();
        leer.nextLine();
        System.out.println("Ingrese cantidad");
        int cantidad = leer.nextInt();
        leer.nextLine();
        return almacenController.nuevoPedido(cantidad, cif, idpieza);
    }

    private static boolean borrarPieza(Scanner leer, AlmacenController almacenController) {
        System.out.println("Ingrese id de la pieza");
        int id = leer.nextInt();
        return almacenController.borrarPieza(id);
    }

    private static boolean modificarPrecioPieza(Scanner leer, AlmacenController almacenController) {
        System.out.println("Ingrese id de la pieza a modificar");
        int id = leer.nextInt();
        leer.nextLine();
        if (almacenController.existeId(id)) {
            System.out.println("Ingrese el nuevo precio");
            Double precio = leer.nextDouble();
            leer.nextLine();
            return almacenController.modificarPrecioPieza(id, precio);
        } else
            System.out.println("No existe el id");
        return false;
    }

    private static boolean nuevaPieza(Scanner leer, AlmacenController almacenController) {
        Color color = Color.NEGRO;
        System.out.println("Ingrese nombre");
        String nombre = leer.nextLine();
        System.out.println("""
                Ingrese color
                1:ROJO
                2:VERDE
                3:AMARILLO
                4:NEGRO
                5:BLANCO
                6:VIOLETA
                7:AZUL
                """);
        String op = leer.nextLine();
        switch (op){
            case "1":
                color = Color.ROJO;
                break;
            case "2":
                color = Color.VERDE;
                break;
            case "3":
                color = Color.AMARILLO;
                break;
            case "4":
                color = Color.NEGRO;
                break;
            case "5":
                color = Color.BLANCO;
                break;
            case "6":
                color = Color.VIOLETA;
                break;
            case "7":
                color = Color.AZUL;
                break;
            default:
                System.out.println("Opción incorrecta\n");
                break;
        }
        System.out.println("Ingrese precio");
        Double precio = leer.nextDouble();
        leer.nextLine();
        System.out.println("""
        Ingrese id de la categoría
        1: Pequeño
        2: Mediano
        3: Grande""");
        int idcategoria = leer.nextInt();
        leer.nextLine();
        return almacenController.nuevaPieza(nombre, color ,precio, idcategoria);
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
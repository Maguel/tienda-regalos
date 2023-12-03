package org.tecnologias.admin;

import org.tecnologias.db.Peluche;
import org.tecnologias.db.Provedor;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Admin {
    private String contraseña;
    private Inventario inventario = new Inventario();
    private CreadorFactura creador = null;


    public boolean agregarProducto() {
        Scanner scanner = new Scanner(System.in);
        Integer codigo;
        String nombre;
        Provedor provedor;
        String proveedor2;
        Integer cantidad;
        Float precio;

        System.out.print("Codidgo del producto: ");
        codigo = scanner.nextInt();
        System.out.print("Nombre del producto: ");
        nombre = scanner.next();
        System.out.println("Precio del producto");
        precio = scanner.nextFloat();

        for (Provedor provedor1 : Provedor.values()) {
            provedor1.mostrar();
        }
        System.out.print("Proveedor: ");
        proveedor2 = scanner.next();


        if (proveedor2.equalsIgnoreCase("Mattel")) {
            provedor = Provedor.MATTEL;
        } else if (proveedor2.equalsIgnoreCase("Hasbro")) {
            provedor = Provedor.HASBRO;
        } else {
            provedor = Provedor.MUÑELOCOS;
        }

        System.out.print("Cantidad de productos: ");
        cantidad = (Integer) scanner.nextInt();

        Peluche peluchito = new Peluche(codigo, nombre, provedor, cantidad, precio);


        return inventario.agregarProducto(peluchito);
    }

    public boolean eliminarProducto() {
        Scanner scanner = new Scanner(System.in);
        int codigo;
        imprimirInventario();
        System.out.print("Codigo del producto a eliminar: ");
        codigo = scanner.nextInt();


        return inventario.eliminarProducto(inventario.getProducto(codigo));
    }

    public void imprimirInventario() {
        inventario.getInventario();
    }

    public boolean modificarCantidad() {
        Scanner scanner = new Scanner(System.in);
        inventario.getInventario();
        System.out.print("Codigo del Peluche a modificar: ");
        int codigo = scanner.nextInt();
        System.out.print("Nueva cantidad de productos: ");
        int cantidad = scanner.nextInt();

        return inventario.modificarCantidad(inventario.getProducto(codigo), cantidad);
    }

    public void generarFactura() {
        Provedor provedor = null;
        Date fecha = new Date();
        float monto = 0;

        ArrayList<Peluche> articulos = new ArrayList<>();
        String respuesta = "Si";
        Scanner scanner = new Scanner(System.in);
        String nombre;
        Integer cantidad = 1;
        Integer codigo;
        Float precio;


        for (Provedor provedor1 : Provedor.values()) {
            provedor1.mostrar();
        }

        System.out.print("Proveedor del que quieres facturar: ");
        String opcion = scanner.next();

        if (opcion.equalsIgnoreCase("MATTEL")) {
            creador = new CreadorFacturaMattel();
            provedor = Provedor.MATTEL;
        } else if (opcion.equalsIgnoreCase("Hasbro")) {
            creador = new CreadorFacturaHasbro();
            provedor = Provedor.HASBRO;
        } else if (opcion.equalsIgnoreCase("Muñelocos")) {

            creador = new CreadorFacturaMuñelocos();
            provedor = Provedor.MUÑELOCOS;
        }

        while (respuesta.equalsIgnoreCase("Si")) {

            System.out.print("Nombre del articulo: ");
            nombre = scanner.next();
            System.out.print("Codigo del articulo: ");
            codigo = (Integer) scanner.nextInt();
            System.out.print("Candtidad del productos: ");
            cantidad = (Integer) scanner.nextInt();
            System.out.print("Precio del prodcuto: ");
            precio = (Float) scanner.nextFloat();


            Peluche peluche = new Peluche(codigo, nombre, provedor, cantidad, precio);
            articulos.add(peluche);

            System.out.print("Seguir introduciendo articulos: (Si o No) ");
            respuesta = scanner.next();

        }
        for (Peluche peluche : articulos) {
            monto += precio * cantidad;
        }


        Factura nuevaFactura = creador.crearFactura(fecha, monto, articulos);


        creador.agregarFacturas(nuevaFactura);
    }

    public void imprimirFacturas() {
        Scanner scanner = new Scanner(System.in);
        if (creador == null) {
            System.out.println("Sin facturas hechas");
        } else {
            for (Provedor provedor1 : Provedor.values()) {
                provedor1.mostrar();
            }
            System.out.print("Facturas de que proveedor quieres imprimir: ");
            String opcion = scanner.next();

            creador.imprimir(opcion);
        }

    }

    public static void adminMain() {
        Admin admin = new Admin();
        Scanner scanner = new Scanner(System.in);
        String opcion = "0";

        while(!opcion.equals("7")){
            System.out.print(
                    "1: Agregar Producto" +
                    "\n2: Eliminar Producto" +
                    "\n3: Modificar Producto" +
                    "\n4: Generar Factura" +
                    "\n5: Imprimir Factura" +
                    "\n6: Imprimir Inventario" +
                    "\n7: Cerrar sesión" +
                    "\nOpcion: "
            );

            opcion = scanner.nextLine();

            switch (opcion){
                case "1":
                    if (admin.agregarProducto())
                        System.out.println("Producto agregado con exito");
                    else
                        System.out.println("El producto no fue registrado");
                    break;
                case "2":
                    admin.eliminarProducto();
                    break;
                case "3":
                    admin.modificarCantidad();
                    break;
                case "4":
                    admin.generarFactura();
                    break;
                case "5":
                    admin.imprimirFacturas();
                    break;
                case "6":
                    admin.imprimirInventario();
                    break;
                case "7":
                    System.out.println("Cerrando sesion");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        }
    }
}

package org.tecnologias.admin;

import org.tecnologias.db.DataBase;
import org.tecnologias.db.Peluche;
import org.tecnologias.db.Provedor;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Admin {
    private Inventario inventario = new Inventario();
    private CreadorFactura creador = null;
    Scanner scanner = new Scanner(System.in);


    public boolean agregarProducto() {
        Peluche.Builder builder = new Peluche.Builder();

        System.out.println("Codidgo del producto: ");
        builder.codigo(Integer.parseInt(scanner.nextLine()));
        System.out.println("Nombre del producto: ");
        builder.nombre(scanner.nextLine());
        System.out.println("Precio del producto: ");
        builder.precio(Float.parseFloat(scanner.nextLine()));
        System.out.println("Tamaño del producto: ");
        builder.tamagno(scanner.nextLine());
        System.out.println("Clasificacion del producto: ");
        builder.clasificacion(scanner.nextLine());

        for (Provedor p : Provedor.values()) {
            p.mostrar();
        }
        System.out.print("Proveedor: ");
        builder.provedor(scanner.nextLine());

        System.out.print("Cantidad de productos: ");
        builder.cantidad(Integer.parseInt(scanner.nextLine()));

        Peluche peluchito = builder.build();

        return inventario.agregarProducto(peluchito);
    }

    public boolean eliminarProducto() {
        int codigo;
        imprimirInventario();
        System.out.print("Codigo del producto a eliminar: ");
        codigo = Integer.parseInt(scanner.nextLine());

        return inventario.eliminarProducto(inventario.getProducto(codigo));
    }

    public void imprimirInventario() {
        inventario.getInventario();
    }

    public boolean modificarCantidad() {
        inventario.getInventario();
        System.out.print("Codigo del Peluche a modificar: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        System.out.print("Nueva cantidad de productos: ");
        int cantidad = Integer.parseInt(scanner.nextLine());

        return inventario.modificarCantidad(inventario.getProducto(codigo), cantidad);
    }

    public void generarFactura() {
        float monto = 0;
        ArrayList<Peluche> articulos = new ArrayList<>();
        String respuesta = "Si";
        Peluche.Builder builder = new Peluche.Builder();


        for (Provedor p : Provedor.values()) {
            p.mostrar();
        }

        System.out.print("Proveedor del que quieres facturar: ");
        String opcion = scanner.next();

        if (opcion.equalsIgnoreCase("MATTEL")) {
            creador = new CreadorFacturaMattel();
        } else if (opcion.equalsIgnoreCase("Hasbro")) {
            creador = new CreadorFacturaHasbro();
        } else if (opcion.equalsIgnoreCase("Muñelocos")) {
            creador = new CreadorFacturaMuñelocos();
        }

        while (respuesta.equalsIgnoreCase("Si")) {
            System.out.print("Nombre del articulo: ");
            builder.nombre(scanner.nextLine());
            System.out.print("Codigo del articulo: ");
            builder.codigo(Integer.parseInt(scanner.nextLine()));
            System.out.print("Candtidad del productos: ");
            builder.cantidad(Integer.parseInt(scanner.nextLine()));
            System.out.print("Precio del prodcuto: ");
            builder.precio(Float.parseFloat(scanner.nextLine()));
            System.out.println("Tamaño del producto");

            Peluche peluche = builder.build();
            articulos.add(peluche);

            System.out.print("Seguir introduciendo articulos: (Si o No) ");
            respuesta = scanner.nextLine();

        }
        for (Peluche peluche : articulos) {
            monto += peluche.getPrecio() * peluche.getCantidad();
        }

        Factura nuevaFactura = creador.crearFactura(new Date(), monto, articulos);

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
    public void imprimirHistorialVentas() {
        DataBase.getInstance().mostrarHistorialVentas();
    }

    public static void adminMain() {
        Admin admin = new Admin();
        Scanner scanner = new Scanner(System.in);
        String opcion = "0";

        while (!opcion.equals("7")) {
            System.out.print(
                    "1: Agregar Producto" +
                            "\n2: Eliminar Producto" +
                            "\n3: Modificar Producto" +
                            "\n4: Generar Factura" +
                            "\n5: Imprimir Factura" +
                            "\n6: Imprimir Inventario" +
                            "\n7: Imprimir Historial de Ventas" +
                            "\n8: Cerrar sesión" +
                            "\nOpcion: "
            );

            opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    if (admin.agregarProducto())
                        System.out.println("Producto agregado con exito");
                    else
                        System.out.println("El producto no fue registrado");
                    break;
                case "2":
                        if (admin.eliminarProducto())
                            System.out.println("Producto eliminado con exito");
                        else
                            System.out.println("El producto no fue eliminado");
                    break;
                case "3":
                        if (admin.modificarCantidad())
                            System.out.println("Producto modificado con exito");
                        else
                            System.out.println("El producto no fue modificado");
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
                    admin.imprimirHistorialVentas();
                case "8":
                    System.out.println("Cerrando sesion");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        }
    }
}

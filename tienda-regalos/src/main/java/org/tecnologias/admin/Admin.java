package org.tecnologias.admin;

import org.tecnologias.db.Peluche;
import org.tecnologias.db.Provedor;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Admin {
    private String contraseña;
    private String nombre;
    private Inventario inventario = new Inventario();
    private CreadorFactura creador = null;

    Admin(String nombre) {
        this.nombre = nombre;
    }

    public boolean agregarProducto() {
        Scanner scanner = new Scanner(System.in);
        Integer codigo;
        String nombre;
        Provedor provedor;
        String proveedor2;
        Integer cantidad;

        System.out.print("Codidgo del producto: ");
        codigo = (Integer) scanner.nextInt();
        System.out.print("Nombre del producto: ");
        nombre = scanner.next();

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

        Peluche peluchito = new Peluche(codigo, nombre, provedor, cantidad);


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

        Date fecha = new Date();
        float monto = 0;
        Provedor provedor = null;
        ArrayList<Peluche> articulos = new ArrayList<>();
        String respuesta = "Si";
        Scanner scanner = new Scanner(System.in);
        String nombre;
        Integer cantidad = 1;
        Integer codigo;
        Integer precio = 1;


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
            precio = (Integer) scanner.nextInt();


            Peluche peluche = new Peluche(codigo, nombre, provedor, cantidad);
            articulos.add(peluche);

            System.out.print("Seguir introduciendo articulos: (Si o No) ");
            respuesta = scanner.next();

        }
        for (Peluche peluche : articulos) {
            monto += precio * cantidad;
        }

        Factura nuevaFactura = creador.crearFactura(fecha, monto, provedor, articulos);

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

    /*
    public static void main(String[] args) {
        Admin admin = new Admin("admin");
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while(opcion != 7){
            System.out.print(
                    "1: Agregar Producto" +
                    "\n2: Eliminar Producto" +
                    "\n3: Modificar Producto" +
                    "\n4: Generar Factura" +
                    "\n5: Imprimir Factura" +
                    "\n6: Imprimir Inventario" +
                    "\n7: Salir" +
                    "\nOpcion: "
            );

            opcion = scanner.nextInt();

            switch (opcion){
                case 1:
                    admin.agregarProducto();
                    break;
                case 2:
                    admin.eliminarProducto();
                    break;
                case 3:
                    admin.modificarCantidad();
                    break;
                case 4:
                    admin.generarFactura();
                    break;
                case 5:
                    admin.imprimirFacturas();
                    break;
                case 6:
                    admin.imprimirInventario();
                    break;
                case 7:
                    opcion = 7;
            }


        }

    }

     */
}

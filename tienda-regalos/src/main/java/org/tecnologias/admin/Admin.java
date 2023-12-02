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

    Admin(String nombre){
        this.nombre = nombre;
    }

    public boolean agregarProducto(){
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

        for (Provedor provedor1: Provedor.values()) {
            provedor1.mostrar();
        }
        System.out.print("Proveedor: ");
        proveedor2 = scanner.next();

        if(proveedor2.equals("Mattel")){
            provedor = Provedor.MATTEL;
        }
        else if(proveedor2.equals("Hasbro")){
            provedor = Provedor.HASBRO;
        }
        else{
            provedor = Provedor.MUÑELOCOS;
        }

        System.out.print("Cantidad de productos: ");
        cantidad = (Integer) scanner.nextInt();

        Peluche peluchito = new Peluche(codigo,nombre,provedor,cantidad);


        return inventario.agregarProducto(peluchito);
    }

    public boolean eliminarProducto(){
        Scanner scanner = new Scanner(System.in);
        int codigo;
        imprimirInventario();
        System.out.print("Codigo del producto a eliminar: ");
        codigo = scanner.nextInt();


        return inventario.eliminarProducto(inventario.getProducto(codigo));
    }

    public void imprimirInventario(){
        inventario.getInventario();
    }

    public boolean modificarCantidad(){
        Scanner scanner = new Scanner(System.in);
        inventario.getInventario();
        System.out.print("Codigo del Peluche a modificar: ");
        int codigo = scanner.nextInt();
        System.out.print("Nueva cantidad de productos: ");
        int cantidad = scanner.nextInt();

        return inventario.modificarCantidad(inventario.getProducto(codigo),cantidad);
    }

    public void generarFactura(){
        ArrayList<Peluche> articulos = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        CreadorFactura creador = null;
        Provedor provedor;
        String palabra = "Si";
        int precio = 1;

        for (Provedor provedor1: Provedor.values()) {
            provedor1.mostrar();
        }

        System.out.print("De que proveedor quieres facturas: ");
        String opcion = scanner.next();

        if(opcion.equals("MATTEL")){
            creador = new CreadorFacturaMattel();
            provedor = Provedor.MATTEL;
        }
        else if(opcion.equals("HASBRO")){
            //creador = new CreadorFacturaHasbro();
            provedor = Provedor.HASBRO;
        }
        else{
            //creador = new CreadorFacturaMuñelocos();
            provedor = Provedor.MUÑELOCOS;
        }

        Date fecha = new Date();
        float monto = 0;


        while(palabra.equals("Si")){
            System.out.print("Codigo del producto: ");
            Integer codigo = (Integer) scanner.nextInt();
            System.out.print("Nombre del producto: ");
            String nombre = scanner.next();
            System.out.print("Cantidad del producto: ");
            int cantidad = scanner.nextInt();
            System.out.print("Precio del producto: ");
            precio = scanner.nextInt();

            Peluche peluche = new Peluche(codigo,nombre,provedor,cantidad);
            articulos.add(peluche);
            System.out.print("Seguir agregando productos? (Si o No) ");
            palabra = scanner.next();
        }

        for (Peluche peluche : articulos){
            monto += peluche.getCantidad() * precio;
        }

        creador.nuevaFactura(fecha,monto,provedor,articulos);
    }

    public void imprimirFacturas(){




    }

    public static void main(String[] args) {
        Admin admin = new Admin("admin");
        admin.imprimirFacturas();
        admin.generarFactura();
        admin.imprimirFacturas();

    }
}

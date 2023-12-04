package org.tecnologias.empleado;

import org.tecnologias.db.DataBase;
import org.tecnologias.db.Peluche;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Empleado {
    private static final Scanner sc = new Scanner(System.in);
    private static final DataBase db = DataBase.getInstance();
    private static final Venta venta = new Venta();

    public void cancelarVenta() {
        venta.limpiar();
    }

    public void historial() {
        db.getHistorialVentas().add(venta);
    }

    public void cancelarProductoPorId(int idPeluche) {
        if (!venta.vacia()) {
            List<Peluche> listaPeluches = new ArrayList<>(venta.getListaPeluches().keySet());
            Map<Peluche, Integer> map = venta.getListaPeluches();
            Iterator<Peluche> iterator = listaPeluches.iterator();// Utiliza un Iterator para poder eliminar elementos durante la iteración
            while (iterator.hasNext()) {
                Peluche peluche = iterator.next();
                if (peluche.getCodigo() == idPeluche) {
                    if (map.get(peluche) == 1) {
                        iterator.remove();
                    } else {
                        map.replace(peluche, map.get(peluche), map.get(peluche) - 1);
                    }

                    System.out.println("Peluche cancelado con ID: " + idPeluche);
                }
            }
            venta.calcularTotalVenta();
        } else {
            System.out.println("No hay venta actual para eliminar el peluche.");
        }
    }

    public void realizarVenta() {
        if (!venta.vacia()) {
            venta.cocretarVenta();
            historial();
            txt();
            venta.imprimirVenta();
            venta.limpiar();
            System.out.println("La venta se realizo con exito.");
            return;
        }
        System.out.println("La venta no se realizo.");
    }

    public void agregarProducto(Peluche peluche) {
        venta.agregarpeluche(peluche);
    }

    public static void empleadoMain() {
        Empleado empleado = new Empleado();
        String opc, opc1;
        Peluche peluche;
        Integer contador = 0;
        List<Peluche> peluchesSinExistencia = new ArrayList<>();
        String str;
        do {
            System.out.println("Menu Empleado\n1.- Comenzar venta\n2.- Cerrar sesion");
            opc = sc.nextLine();
            if (opc.equals("1")) {
                while (true) {
                    System.out.println("Introduzca el codigo del producto o 'Q' para finalizar la compra");
                    str = sc.nextLine();
                    if (str.equalsIgnoreCase("q")) break;
                    peluche = db.getProducto(Integer.parseInt(str));
                    if (peluche != null) {
                        if (!peluchesSinExistencia.contains(peluche) && contador.equals(peluche.getCantidad())){
                            contador = 0;
                            peluchesSinExistencia.add(peluche);
                        }
                        if (!contador.equals(peluche.getCantidad()) && !peluchesSinExistencia.contains(peluche)) {
                            empleado.agregarProducto(peluche);
                            ++contador;
                            if (!peluchesSinExistencia.contains(peluche) && contador.equals(peluche.getCantidad())){
                                contador = 0;
                                peluchesSinExistencia.add(peluche);
                            }
                        } else {
                            System.out.println("Ya no queda ese peluche en existencia");
                        }
                    } else
                        System.out.println("El producto no existe");
                }
                do {
                    System.out.println("1.- Finalizar venta\n2.- Cancelar producto\n3.- Cancelar venta");
                    opc1 = sc.nextLine();
                    switch (opc1) {
                        case "1":
                            empleado.realizarVenta();
                            break;
                        case "2":
                            do {
                                for (Peluche p : venta.getListaPeluches().keySet()) {
                                    System.out.print(p.mostar());
                                }
                                System.out.println("\nIntroduzca el id del producto a cancelar");
                                empleado.cancelarProductoPorId(Integer.parseInt(sc.nextLine()));
                                System.out.println("Desea cancelar otro producto: si/no");
                            } while (!sc.nextLine().equalsIgnoreCase("no"));
                            break;
                        case "3":
                            empleado.cancelarVenta();
                            break;
                        default:
                            System.out.println("Opcion no valida");
                    }
                } while (opc1.equals("2"));
            } else
                System.out.println("Cerrando sesion");
        } while (!opc.equals("2"));
    }

    public void txt() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/ventas.txt", true))) {
            for (Venta v : db.getHistorialVentas()) {
                writer.write(v.toString());
                writer.newLine();
            }
            System.out.println("Historial se guardo con exito");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}


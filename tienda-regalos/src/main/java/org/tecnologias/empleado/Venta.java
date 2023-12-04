package org.tecnologias.empleado;

import org.tecnologias.db.Peluche;

import java.io.Serializable;
import java.util.*;

public class Venta implements Serializable {
    private Map<Peluche, Integer> articulos = new HashMap<>();
    private Date fecha;
    private Integer id = 1;
    private Integer cantidad;

    public void agregarpeluche(Peluche peluche) {
        if (articulos.containsKey(peluche)) {
            articulos.put(peluche, ++cantidad);
        } else {
            cantidad = 1;
            articulos.put(peluche, cantidad);
        }
    }


    public void agregarfecha() {
        this.fecha = new Date();
    }

    public void ID() {
        this.id++;
    }

    public void limpiar() {
        articulos.clear();
    }

    public void imprimirVenta() {
        String lineSeparator = "+" + new String(new char[74]).replace('\0', '-') + "+";
        System.out.println(lineSeparator);
        System.out.printf("| %-72s |\n", "Detalles de la Venta");
        System.out.println(lineSeparator);
        System.out.printf("| %-16s | %-53s |\n", "ID de Venta", id);
        System.out.println(lineSeparator);
        System.out.printf("| %-16s | %-53s |\n", "Fecha de Venta", fecha);
        System.out.println(lineSeparator);
        for (Peluche peluche : articulos.keySet()) {
            peluche.imprimir(articulos.get(peluche));
        }
        System.out.println(lineSeparator);
        System.out.printf("| %-16s | %-54s|\n", "Precio total", calcularTotalVenta());
        System.out.println(lineSeparator);
    }

    public boolean vacia() {
        return articulos.isEmpty();
    }

    public double calcularTotalVenta() {
        double total = 0.0;
        for (Peluche peluche : articulos.keySet()) {
            total += peluche.getPrecio() * articulos.get(peluche);
        }
        return total;
    }

    public Map<Peluche, Integer> getListaPeluches() {
        return articulos;
    }

}



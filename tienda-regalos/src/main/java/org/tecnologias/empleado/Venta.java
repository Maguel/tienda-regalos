package org.tecnologias.empleado;

import org.tecnologias.db.DataBase;
import org.tecnologias.db.Peluche;

import java.io.Serializable;
import java.util.*;

public class Venta implements Serializable {
    private final DataBase db = DataBase.getInstance();
    private Map<Peluche, Integer> articulos = new HashMap<>();
    private Date fecha;
    private Integer id = db.getHistorialVentas().isEmpty() ? 1 : db.getHistorialVentas().size();
    private Integer cantidad;
    private Float monto = 0.0F;

    public void agregarpeluche(Peluche peluche) {
        if (articulos.containsKey(peluche)) {
            articulos.put(peluche, ++cantidad);
        } else {
            cantidad = 1;
            articulos.put(peluche, cantidad);
        }
    }

    public Venta(Map<Peluche, Integer> articulos, Date fecha, Float monto, Integer id) {
        this.articulos = articulos;
        this.fecha = fecha;
        this.monto = monto;
        this.id = id;
    }

    public Venta() {
    }

    public void cocretarVenta() {
        this.fecha = new Date();
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
        for (Peluche peluche : articulos.keySet()) {
            monto += peluche.getPrecio() * articulos.get(peluche);
            db.modificarCantidad(peluche, peluche.getCantidad() - articulos.get(peluche));
        }
        return monto;
    }

    public Map<Peluche, Integer> getListaPeluches() {
        return articulos;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Peluche peluche : articulos.keySet()) {
            sb.append("Nombre: ").append(peluche.getNombre()).append(", ")
                    .append("Codigo: ").append(peluche.getCodigo()).append(", ")
                    .append("Cantidad: ").append(articulos.get(peluche)).append(", ");
        }
        sb.append("Fecha de venta: ").append(fecha).append(", ")
                .append("Precio total: ").append(calcularTotalVenta())
                .append(", ").append("id: ").append(id);

        return sb.toString();
    }
}



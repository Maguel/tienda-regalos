package org.tecnologias.empleado;

import org.tecnologias.db.DataBase;
import org.tecnologias.db.Peluche;

import java.io.Serializable;
import java.util.*;

public class Venta implements Serializable {
    private final DataBase db;
    private Map<Peluche, Integer> articulos = new HashMap<>();
    private Date fecha;
    private Integer id;
    private Integer cantidad;
    private Float monto;

    public void agregarpeluche(Peluche peluche) {
        if (articulos.containsKey(peluche)) {
            articulos.put(peluche, ++cantidad);
        } else {
            cantidad = 1;
            articulos.put(peluche, cantidad);
        }
    }

    public Venta(Map<Peluche, Integer> articulos, Date fecha, Float monto, Integer id) {
        db = DataBase.getInstance();
        this.articulos = articulos;
        this.fecha = fecha;
        this.monto = monto;
        this.id = id;
    }

    public Venta() {
        db = DataBase.getInstance();
        id = db.getHistorialVentas().isEmpty() ? 1 : db.getHistorialVentas().size();
    }

    public void cocretarVenta() {
        this.fecha = new Date();
        this.id++;
        calcularTotalVenta();
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
        System.out.printf("| %-16s | %-54s|\n", "Precio total", monto);
        System.out.println(lineSeparator);
    }

    public boolean vacia() {
        return articulos.isEmpty();
    }

    public void calcularTotalVenta() {
        monto = 0F;
        for (Peluche peluche : articulos.keySet()) {
            monto += peluche.getPrecio() * articulos.get(peluche);
            var delta = peluche.getCantidad() - articulos.get(peluche);
            System.out.println("Delta: " + delta);
            db.modificarCantidad(peluche, delta);
        }
    }
    public Map<Peluche, Integer> getListaPeluches() {
        return articulos;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Peluche peluche : articulos.keySet()) {
            sb.append("Nombre: ").append(peluche.getNombre()).append(", ")
                    .append("Codigo: ").append(peluche.getCodigo()).append(", ")
                    .append("Cantidad: ").append(articulos.get(peluche)).append(", ");
        }
        sb.append("Fecha de venta: ").append(fecha).append(", ")
                .append("Precio total: ").append(monto)
                .append(", ").append("id: ").append(id);

        return sb.toString();
    }
}



package org.tecnologias.empleado;
import org.tecnologias.db.Peluche;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Venta
{
    private List<Peluche> articulo = new ArrayList<>();
    private Date fecha;
    // private int id;
    private Empleado empleado = new Empleado();
    private static final AtomicLong contador = new AtomicLong(1);
    private  long id;

    // Constructor
    public Venta(List<Peluche> articulo, Date fecha, int id, Empleado empleado)
    {
        this.articulo = articulo;
        this.fecha = fecha;
        this.id = id;
        this.empleado = empleado;
    }
    public Venta()
    {
    }

    public void agregarpeluche(Peluche peluche)
    {
        articulo.add(peluche);
    }
    public void agregarempleado(Empleado empleado1)
    {
        this.empleado = empleado1;
    }
    public void agregarfecha()
    {
        Date fechaVenta = new Date();
        this.fecha =fechaVenta;
    }
    public void ID()
    {
        this.id = contador.getAndIncrement();

    }
    public void imprimirVenta()
    {
        String lineSeparator = "+" + new String(new char[74]).replace('\0', '-') + "+";
        System.out.println(lineSeparator);
        System.out.printf("| %-72s |\n", "Detalles de la Venta");
        System.out.println(lineSeparator);
        System.out.printf("| %-16s | %-53s |\n", "ID de Venta", id);
        System.out.println(lineSeparator);
        System.out.printf("| %-16s | %-53s |\n", "Fecha de Venta", fecha);
        System.out.println(lineSeparator);
        for (Peluche peluche : articulo)
        {
            peluche.imprimir();
        }
        System.out.println(lineSeparator);
        empleado.imprimiremplado();
        System.out.printf("| %-16s | %-54s|\n", "Precio total", calcularTotalVenta());
        System.out.println(lineSeparator);
    }
    public boolean vacia()
    {
        return articulo.isEmpty();
    }

    public double calcularTotalVenta()
    {
        double total = 0.0;
        for (Peluche peluche : articulo)
        {
            total += peluche.getPrecio();
        }
        return total;
    }
    public List<Peluche> getListaPeluches()
    {
        return articulo;
    }

}



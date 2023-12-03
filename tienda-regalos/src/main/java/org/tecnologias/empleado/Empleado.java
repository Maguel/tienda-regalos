package org.tecnologias.empleado;

import org.tecnologias.db.Peluche;
import org.tecnologias.db.Provedor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Date;

public class Empleado
{
    private String nombre;
    private String apellido;
    private String contrasena;
    // Constructor
    public Empleado(String nombre, String apellido, String contrasena)
    {
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasena = contrasena;
    }
    public Empleado()
    {

    }
    public void imprimiremplado()
    {
        System.out.printf("| %-16s | %-53s |\n", "Nombre: ",nombre);
        System.out.printf("| %-16s | %-53s |\n","Apellido", apellido );

    }
    public boolean cancelarProducto(Peluche peluche)
    {
        return true;
    }

    public boolean cancelarVenta(Venta venta)
    {
        venta = null;
        return true;
    }

    public boolean eliminarProductoPorId(int idPeluche, Venta ventaActual)
    {
        if (ventaActual != null)
        {
            List<Peluche> listaPeluches = ventaActual.getListaPeluches();
            Iterator<Peluche> iterator = listaPeluches.iterator();// Utiliza un Iterator para poder eliminar elementos durante la iteración
            while (iterator.hasNext())
            {
                Peluche peluche = iterator.next();
                if (peluche.getCodigo() == idPeluche)
                {
                    iterator.remove();
                    System.out.println("Peluche eliminado con ID: " + idPeluche);
                }
            }
            ventaActual.calcularTotalVenta();
            return true;
        }
        else
        {
            System.out.println("No hay venta actual para eliminar el peluche.");
            return false;
        }
    }
    public boolean realizarVenta(Peluche peluche,  Venta ventaActual)
    {
        if(!ventaActual.vacia())
        {
            ventaActual.agregarempleado(this);
            ventaActual.agregarfecha();
            ventaActual.ID();
            //obtenerVentaActual(ventaActual);
            return true;
        }
        else
        {
            return false;
        }
    }
    public void agregarProducto(Peluche peluche, Venta ventaActual)
    {
        if (ventaActual == null)
        {
            ventaActual = new Venta();
        }
        ventaActual.agregarpeluche(peluche);
    }
    public void obtenerVentaActual(Venta ventaActual)
    {
        ventaActual.imprimirVenta();
    }
    public static void empleadoMain()
    {
        Venta venta1 = new Venta();
        Empleado empleado1 = new Empleado("Juan", "Pérez","123");
        Empleado empleado2 = new Empleado("carlos", "Pérez","356");
        //Peluche peluche1 = new Peluche("Juguete", "ABC123", 1, "Marca1", "Osito", 19.99, "Pequeño");

        try (BufferedReader br = new BufferedReader(new FileReader("D:\\5 semestre\\Tecnologias emegergente\\proyecto\\peluches.txt")))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                // Dividir la línea en atributos usando algún delimitador (por ejemplo, ",")
                String[] attributes = line.split(",");

                // Crear una instancia de Peluche con los atributos y agregarlo a la lista
            /*    Peluche peluche = new Peluche(
                        attributes[0].trim(),
                        attributes[1].trim(),
                        Integer.parseInt(attributes[2].trim()),
                        attributes[3].trim(),
                        attributes[4].trim(),
                        Double.parseDouble(attributes[5].trim()),
                        attributes[6].trim()
                );
*/
                // Agregar el peluche a la venta actual del empleado
               // empleado1.agregarProducto(peluche, venta1);
               // empleado1.realizarVenta(peluche, venta1);
                // empleado1.obtenerVentaActual(venta1);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        //  empleado2.agregarProducto(peluche1, venta1);
        //empleado2.realizarVenta(peluche1, venta1);
        // Imprimir información de la venta
        //System.out.println(empleado1.cancelarVenta(venta1));
        empleado1.eliminarProductoPorId(10, venta1);
        Peluche peluche3 = new Peluche(1, "osito", Provedor.HASBRO, 7, 50F);
        empleado1.agregarProducto(peluche3, venta1);
        empleado1.realizarVenta(peluche3, venta1);
     /* Peluche peluche4 = new Peluche("Juguete", "ABC123", 12, "Marca1", "Osito", 50, "Pequeño");
      empleado1.agregarProducto(peluche4, venta1);
      empleado1.realizarVenta(peluche4, venta1);
      empleado1.eliminarProductoPorId(10, venta1);*/
        empleado1.obtenerVentaActual(venta1);

    }
}


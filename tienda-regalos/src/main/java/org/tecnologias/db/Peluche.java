package org.tecnologias.db;

public class Peluche {
    private String clasificacion;
    private final Integer codigo;
    private final String nombre;
    private final Provedor provedor;
    private Float precio;
    private Integer cantidad;
    private String tamagno;

    public Peluche(Integer codigo, String nombre, Provedor provedor, Integer cantidad, Float precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.provedor = provedor;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public Provedor getProvedor() {
        return provedor;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "{ Peluche: " + nombre + "; Codigo: " + codigo + "; Cantidad: " + cantidad + "; Precio: }" + precio +"\n";
    }
    public void imprimir()
    {
        String lineSeparator = "+" + new String(new char[74]).replace('\0', '-') + "+";
        System.out.printf("| %-16s | %-53s |\n", "Clasificacion: " , clasificacion);
        System.out.printf("| %-16s | %-53s |\n", "Codigo: " , codigo);
        System.out.printf("| %-16s | %-53s |\n", "Marca: " , provedor);
        System.out.printf("| %-16s | %-53s |\n", "Nombre: " , nombre);
        System.out.printf("| %-16s | %-53s |\n", "Precio: " , precio);
        System.out.printf("| %-16s | %-53s |\n", "Tamaño: " , tamagno);
        System.out.println(lineSeparator);
    }

    public Float getPrecio() {
        return precio;
    }
}

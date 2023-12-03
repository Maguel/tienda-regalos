package org.tecnologias.db;

public class Peluche {
    private final Integer codigo;
    private final String nombre;
    private final Provedor provedor;
    private Float precio;
    private Integer cantidad;

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
}

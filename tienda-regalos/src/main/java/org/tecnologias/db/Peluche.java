package org.tecnologias.db;

import java.beans.Introspector;

public class Peluche {
    private String clasificacion;
    private final Integer codigo;
    private final String nombre;
    private final Provedor provedor;
    private Float precio;
    private Integer cantidad;
    private String tamagno;

    public Peluche(Builder builder) {
        this.clasificacion = builder.clasificacion;
        this.codigo = builder.codigo;
        this.nombre = builder.nombre;
        this.provedor = builder.provedor;
        this.precio = builder.precio;
        this.cantidad = builder.cantidad;
        this.tamagno = builder.tamagno;
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

    public String getClasificacion() {
        return clasificacion;
    }

    public String getTamagno() {
        return tamagno;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "\n{ Peluche: " + nombre + "; Codigo: " + codigo + "; Cantidad: " + cantidad + "; Precio: " + precio + "}\n";
    }
    public String mostar() {
        return "\nPeluche: " + nombre + "Codigo: " + codigo + "Precio: " + precio;
    }

    public Float getPrecio() {
        return precio;
    }
    public void imprimir(Integer c) {
        String lineSeparator = "+" + new String(new char[74]).replace('\0', '-') + "+";
        System.out.printf("| %-16s | %-53s |\n", "Nombre: ", nombre);
        System.out.printf("| %-16s | %-53s |\n", "Codigo: ", codigo);
        System.out.printf("| %-16s | %-53s |\n", "Marca: ", provedor);
        System.out.printf("| %-16s | %-53s |\n", "Tamaño: ", tamagno);
        System.out.printf("| %-16s | %-53s |\n", "Clasificacion: ", clasificacion);
        System.out.printf("| %-16s | %-53s |\n", "Precio: ", precio);
        System.out.printf("| %-16s | %-53s |\n", "Cantidad: ", c);
        System.out.println(lineSeparator);
    }
    public static class Builder {
        private String clasificacion = "Sin clasificar";
        private  Integer codigo;
        private  String nombre = "Sin nombre";
        private  Provedor provedor;
        private Float precio;
        private Integer cantidad;
        private String tamagno = "Sin especificar";

        public Builder clasificacion(String clasificacion) {
            this.clasificacion = clasificacion;
            return this;
        }
        public Builder codigo(Integer codigo) {
            this.codigo = codigo;
            return this;
        }
        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }
        public Builder provedor(String provedor) {
            this.provedor = Provedor.getProvedor(provedor);
            return this;
        }
        public Builder precio(Float precio) {
            this.precio = precio;
            return this;
        }
        public Builder cantidad(Integer cantidad) {
            this.cantidad = cantidad;
            return this;
        }
        public Builder tamagno(String tamagno) {
            this.tamagno = tamagno;
            return this;
        }
        public Peluche build() {
            return new Peluche(this);
        }
    }
}

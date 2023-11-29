package org.tecnologias.db;

public enum Provedor {

    MATTEL("MATTEL",
            "193 Boulevard Miguel de Cervantes Saavedra; 11520 Mexico City, Distrito Federal, Mexico, Ciudad de México, México, D.F.",
            "55 5905 4100",
            "Logo de MATTEL"),
    HASBRO("Hasbro",
            "Boulevard Manuel Avila Camacho No. 32 Piso 3 Col. Lomas de Chapultepec 11000 México, D.F.",
            "55 5284 4400",
            "Logo de Hasbro"),
    MUÑELOCOS("Muñelocos",
            "México, 67110 Guadalupe, N.L., Real de San Miguel, Blvd. Acapulco 1500-l5",
            "55 4162 8900",
            "Logo de Muñelocos");

    private final String nombre;
    private final String direccion;
    private final String numero;
    private final String imagen;

    Provedor(String nombre, String direccion, String numero, String imagen) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.numero = numero;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getNumero() {
        return numero;
    }

    public String getImagen() {
        return imagen;
    }
}

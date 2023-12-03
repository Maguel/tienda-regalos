package org.tecnologias.admin;

import org.tecnologias.db.Peluche;
import org.tecnologias.db.Provedor;

import java.util.ArrayList;
import java.util.Date;

public abstract class Factura {
    protected Date fecha;
    protected float monto;
    protected Provedor provedor;
    protected ArrayList<Peluche> articulos;

    Factura(Date fecha, float monto, Provedor provedor, ArrayList<Peluche> articulos){
        this.fecha = fecha;
        this.monto = monto;
        this.provedor = provedor;
        this.articulos = articulos;
    }

    public void imprimir(){
        System.out.println(
                "Fecha: " + fecha +
                "\nMonto: " + monto +
                "\nProveedor: " + provedor.getNombre() +
                "\nDireccion: " + provedor.getDireccion() +
                "\nTelefono: " + provedor.getNumero() +
                "\nArticulos: "
        );

        for(Peluche peluche: articulos){
            System.out.println(
                    peluche.getNombre() + "\n"
            );
        }
    }

    public Provedor getProvedor(){
        return provedor;
    }
}

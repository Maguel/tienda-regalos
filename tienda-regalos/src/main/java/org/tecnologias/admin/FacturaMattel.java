package org.tecnologias.admin;

import org.tecnologias.db.Peluche;
import org.tecnologias.db.Provedor;

import java.util.ArrayList;
import java.util.Date;

public class FacturaMattel extends Factura{

    FacturaMattel(Date fecha, float monto, Provedor provedor, ArrayList<Peluche> articulos){
        super(fecha,monto,provedor,articulos);
    }

    public void imprimir(){
        System.out.println(
                "Fecha: " + fecha +
                "Proveedor: " + provedor +
                "Direccion: " + provedor.getDireccion() +
                "Telefono: " + provedor.getNumero() +
                "Monto: " + monto +
                "Articulos "
        );

        for(Peluche peluche: articulos){
            System.out.println(peluche.getNombre());
        }
    }
}

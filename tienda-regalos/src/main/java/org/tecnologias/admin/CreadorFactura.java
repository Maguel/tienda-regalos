package org.tecnologias.admin;

import org.tecnologias.db.Peluche;
import org.tecnologias.db.Provedor;

import java.util.ArrayList;
import java.util.Date;

public abstract class CreadorFactura {
    protected ArrayList<Factura> facturas = new ArrayList<>();

    protected abstract Factura crearFactura(Date time, float monto, Provedor provedor, ArrayList<Peluche> articulos);
    public void nuevaFactura(Date time, float monto, Provedor provedor, ArrayList<Peluche> articulos){
        Factura factura = this.crearFactura(time, monto, provedor, articulos);
        facturas.add(factura);
    }

    public void imprimirFacturas(){
        for(Factura factura: facturas){
            factura.imprimir();
        }
    }
}

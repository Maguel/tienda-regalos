package org.tecnologias.admin;

import org.tecnologias.db.Peluche;

import java.util.ArrayList;
import java.util.Date;

public abstract class CreadorFactura {

    protected static ArrayList<Factura> facturas = new ArrayList<>();

    protected abstract Factura crearFactura(Date fecha, float monto, ArrayList<Peluche> articulos);

    protected abstract void agregarFacturas(Factura factura);

    public void imprimir(String opcion){

        if(!facturas.isEmpty()){
            for(Factura factura: facturas){
                if(factura.provedor.getNombre().equalsIgnoreCase(opcion)){
                    factura.imprimir();
                }
            }
        }
        else{
            System.out.println("Sin facturas hechas");
        }
    }

}


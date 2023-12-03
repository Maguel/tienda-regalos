package org.tecnologias.admin;

import org.tecnologias.db.Peluche;
import org.tecnologias.db.Provedor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class CreadorFactura {

    protected static ArrayList<Factura> facturas = new ArrayList<>();

    protected abstract Factura crearFactura(Date fecha, float monto, Provedor provedor, ArrayList<Peluche> articulos);

    protected abstract void agregarFacturas(Factura factura);

    public void imprimir(String opcion){

        if(!facturas.isEmpty()){
            for(Factura factura: facturas){
                if(factura.provedor.getNombre().equals(opcion)){
                    factura.imprimir();
                }
            }
        }
        else{
            System.out.println("Sin facturas hechas");
        }
    }

}


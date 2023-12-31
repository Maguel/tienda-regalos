package org.tecnologias.admin;

import org.tecnologias.db.Peluche;
import org.tecnologias.db.Provedor;

import java.util.ArrayList;
import java.util.Date;

public class CreadorFacturaMuñelocos extends CreadorFactura{

    protected Factura crearFactura(Date fecha, float monto, ArrayList<Peluche> articulos) {
        return new FacturaMuñelocos(fecha,monto,articulos);
    }


    protected void agregarFacturas(Factura factura) {
        facturas.add(factura);
    }

}

package org.tecnologias.admin;

import org.tecnologias.db.Peluche;
import org.tecnologias.db.Provedor;

import java.util.ArrayList;
import java.util.Date;

public class CreadorFacturaHasbro extends CreadorFactura{

    protected Factura crearFactura(Date fecha, float monto, Provedor provedor, ArrayList<Peluche> articulos) {
        return new FacturaHasbro(fecha, monto,provedor,articulos);
    }

    protected void agregarFacturas(Factura factura) {
        facturas.add(factura);
    }

}

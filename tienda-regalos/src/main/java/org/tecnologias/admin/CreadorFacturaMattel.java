package org.tecnologias.admin;

import org.tecnologias.db.Peluche;
import org.tecnologias.db.Provedor;

import java.util.ArrayList;
import java.util.Date;

public class CreadorFacturaMattel extends CreadorFactura{

    protected Factura crearFactura(Date fecha, float monto, Provedor provedor, ArrayList<Peluche> articulos) {
        return new  FacturaMattel(fecha, monto, provedor, articulos);
    }

}

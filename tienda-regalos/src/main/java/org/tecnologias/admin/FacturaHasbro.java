package org.tecnologias.admin;

import org.tecnologias.db.Peluche;
import org.tecnologias.db.Provedor;

import java.util.ArrayList;
import java.util.Date;

public class FacturaHasbro extends Factura{
    FacturaHasbro(Date fecha, float monto, Provedor provedor, ArrayList<Peluche> articulos){
        super(fecha,monto,provedor,articulos);
    }
}
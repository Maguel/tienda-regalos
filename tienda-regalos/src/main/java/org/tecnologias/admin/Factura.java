package org.tecnologias.admin;

import org.tecnologias.db.Peluche;
import org.tecnologias.db.Provedor;

import java.lang.reflect.Array;
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

    public abstract void imprimir();
}

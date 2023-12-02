package org.tecnologias.admin;

import org.tecnologias.db.DataBase;
import org.tecnologias.db.Peluche;

public class Inventario {
    private DataBase base = DataBase.getInstance();

    public boolean agregarProducto(Peluche peluche){
        return base.agregarProducto(peluche);
    }

    public boolean eliminarProducto(Peluche peluche){
        return base.eliminarProducto(peluche);
    }

    public boolean modificarCantidad(Peluche peluche, Integer cantidad){
        return base.modificarCantidad(peluche,cantidad);
    }

    public void getInventario(){
        base.getInventario();
    }

    public Peluche getProducto(int id){
        return base.getProducto(id);
    }
}

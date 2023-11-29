package org.tecnologias.db;

import javax.xml.crypto.Data;
import java.util.HashMap;

public class DataBase {
    private final HashMap<Integer, Peluche> inventario = new HashMap<>();

    public boolean agregarProducto(Peluche peluche) {
        return inventario.put(peluche.getCodigo(), peluche) == null;
    }

    public boolean eliminarProducto(Peluche peluche) {
        return inventario.remove(peluche.getCodigo()) == null;
    }
    private DataBase() {
    }

    public static DataBase getInstance() {
        return Holder.instance;
    }

    public void getInventario() {
        System.out.println(inventario);
    }

    private static class Holder {
        private static final DataBase instance = new DataBase();
    }
}

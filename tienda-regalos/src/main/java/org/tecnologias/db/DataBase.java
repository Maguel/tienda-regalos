package org.tecnologias.db;

import java.util.HashMap;

public class DataBase {
    private final HashMap<Integer, Peluche> inventario = new HashMap<>();

    public boolean agregarProducto(Peluche peluche) {
        return inventario.put(peluche.getCodigo(), peluche) == null;
    }
    public boolean modificarCantidad(Peluche peluche, Integer cantidad) {
        if (inventario.containsKey(peluche.getCodigo())) {
            inventario.get(peluche.getCodigo()).setCantidad(cantidad);
            return true;
        }
        return false;
    }
    public boolean eliminarProducto(Peluche peluche) {
        return inventario.remove(peluche.getCodigo()) == null;
    }
    private DataBase() {
        Peluche creeper = new Peluche(475,"Creeper",Provedor.MATTEL,5);
        inventario.put(creeper.getCodigo(),creeper);
    }

    public static DataBase getInstance() {
        return Holder.instance;
    }

    public void getInventario() {
        System.out.println(inventario);
    }

    public Peluche getProducto(int id){
        return inventario.get(id);
    }

    private static class Holder {
        private static final DataBase instance = new DataBase();
    }
}

package org.tecnologias.db;

import java.util.HashMap;

public class DataBase {
    private static final String ADMIN_PASS = "admin";
    private static final String EMPLEADO_PASS = "empleado";
    private final HashMap<Integer, Peluche> inventario = new HashMap<>();

    public static boolean validarAdmin(String pass) {
        return ADMIN_PASS.equals(pass);
    }
    public static boolean validarEmpleado(String pass) {
        return EMPLEADO_PASS.equals(pass);
    }

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

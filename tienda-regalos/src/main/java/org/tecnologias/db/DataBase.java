package org.tecnologias.db;

import java.io.*;
import java.util.HashMap;

public class DataBase {
    private static final String ADMIN_PASS = "admin";
    private static final String EMPLEADO_PASS = "empleado";
    private final HashMap<Integer, Peluche> inventario = new HashMap<>();
    ClassLoader classLoader = DataBase.class.getClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream("db.txt");

    public static boolean validarAdmin(String pass) {
        return ADMIN_PASS.equals(pass);
    }

    public static boolean validarEmpleado(String pass) {
        return EMPLEADO_PASS.equals(pass);
    }

    public boolean agregarProducto(Peluche peluche) {
        if (inventario.put(peluche.getCodigo(), peluche) == null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/db.txt", true))) {
                writer.write(peluche.getClasificacion()
                        + ", " + peluche.getCodigo()
                        + ", " + peluche.getNombre()
                        + ", " + peluche.getProvedor().name()
                        + ", " + peluche.getPrecio()
                        + ", " + peluche.getCantidad()
                        + ", " + peluche.getTamagno()
                );
                writer.newLine();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean modificarCantidad(Peluche peluche, Integer cantidad) {
        if (inventario.containsKey(peluche.getCodigo())) {
            inventario.get(peluche.getCodigo()).setCantidad(cantidad);
            return true;
        }
        return false;
    }

    public boolean eliminarProducto(Peluche peluche) {
        return inventario.remove(peluche.getCodigo()) != null;
    }

    private DataBase() {
        Peluche.Builder builder = new Peluche.Builder();
        if (inputStream != null) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = br.readLine()) != null) {
                    // Dividir la línea en atributos usando algún delimitador (por ejemplo, ",")
                    String[] attributes = line.split(",");

                    // Crear una instancia de Peluche con los atributos y agregarlo a la lista
                    Peluche peluche = builder.clasificacion(attributes[0].stripLeading()).
                            codigo(Integer.parseInt(attributes[1].stripLeading())).
                            nombre(attributes[2].stripLeading()).
                            provedor(attributes[3].stripLeading()).
                            precio(Float.parseFloat(attributes[4].stripLeading())).
                            cantidad(Integer.parseInt(attributes[5].stripLeading())).
                            tamagno(attributes[5].stripLeading()).build();
                    inventario.put(peluche.getCodigo(), peluche);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static DataBase getInstance() {
        return Holder.instance;
    }

    public void getInventario() {
        for (Peluche peluche : inventario.values()) {
            System.out.println(peluche);
        }
    }

    public Peluche getProducto(int id) {
        return inventario.get(id);
    }

    private static class Holder {
        private static final DataBase instance = new DataBase();
    }
}

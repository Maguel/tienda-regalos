package org.tecnologias.db;

import org.tecnologias.empleado.Venta;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataBase {
    private static final String ADMIN_PASS = "admin";
    private static final String EMPLEADO_PASS = "empleado";
    private final HashMap<Integer, Peluche> inventario = new HashMap<>();
    private final List<Venta> historialVentas = new ArrayList<>();
    ClassLoader classLoader = DataBase.class.getClassLoader();
    InputStream inputStreamPeluches;
    InputStream inputStreamVentas;

    public static boolean validarAdmin(String pass) {
        return ADMIN_PASS.equals(pass);
    }

    public static boolean validarEmpleado(String pass) {
        return EMPLEADO_PASS.equals(pass);
    }

    public boolean agregarProducto(Peluche peluche) {
        if (inventario.put(peluche.getCodigo(), peluche) == null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/peluches.txt", true))) {
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
            peluche.setCantidad(cantidad);
            inventario.put(peluche.getCodigo(), peluche);
            inputStreamPeluches = classLoader.getResourceAsStream("peluches.txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/peluches.txt", false));
                 BufferedReader reader = new BufferedReader(new InputStreamReader(inputStreamPeluches))) {

                List<String> contenido = new ArrayList<>();
                String line;
                int index = 0;

                while ((line = reader.readLine()) != null) {
                    contenido.add(line);
                }
                for (String str : contenido) {
                    if (str.contains(peluche.getCodigo().toString())) {
                        index = contenido.indexOf(str);
                        break;
                    }
                }
                contenido.set(index, peluche.getClasificacion()
                        + ", " + peluche.getCodigo()
                        + ", " + peluche.getNombre()
                        + ", " + peluche.getProvedor().name()
                        + ", " + peluche.getPrecio()
                        + ", " + cantidad
                        + ", " + peluche.getTamagno());
                for (String str : contenido) {
                    writer.write(str);
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        return false;
    }


    public boolean eliminarProducto(Peluche peluche) {
        return inventario.remove(peluche.getCodigo()) != null;
    }

    private DataBase() {
        Peluche.Builder builder = new Peluche.Builder();
        inputStreamPeluches = classLoader.getResourceAsStream("peluches.txt");
        if (inputStreamPeluches != null) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStreamPeluches))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] attributes = line.split(",");

                    Peluche peluche = builder.clasificacion(attributes[0].stripLeading()).
                            codigo(Integer.parseInt(attributes[1].stripLeading())).
                            nombre(attributes[2].stripLeading()).
                            provedor(attributes[3].stripLeading()).
                            precio(Float.parseFloat(attributes[4].stripLeading())).
                            cantidad(Integer.parseInt(attributes[5].stripLeading())).
                            tamagno(attributes[6].stripLeading()).build();
                    inventario.put(peluche.getCodigo(), peluche);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        inputStreamVentas = classLoader.getResourceAsStream("ventas.txt");
        if (inputStreamVentas != null) {
            String codigo = "";
            String cantidad = "";
            String fecha = "";
            String monto = "";
            String id = "";
            Date date = new Date();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStreamVentas))) {
                String line;
                List<String> ventaStr = new ArrayList<>();
                while ((line = br.readLine()) != null) {
                    String[] attributes = line.split(",");
                    ventaStr.addAll(List.of(attributes));
                }
                if (!ventaStr.isEmpty()) {
                    Map<Peluche, Integer> map = new HashMap<>();
                    for (String str : ventaStr) {
                        if (str.contains("Codigo:"))
                            codigo = str.substring(9);
                        if (str.contains("Cantidad:"))
                            cantidad = str.substring(11);
                        if (!codigo.isBlank() && !cantidad.isBlank())
                            map.put(getProducto(Integer.parseInt(codigo)), Integer.parseInt(cantidad));
                        if (str.contains("Fecha:"))
                            fecha = str.substring(8);
                        if (!fecha.isBlank()) {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                            try {
                                date = dateFormat.parse(fecha);
                            } catch (ParseException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        if (str.contains("Precio total:"))
                            monto = str.substring(15);
                        if (str.contains("id:"))
                            id = str.substring(5);
                    }
                    historialVentas.add(new Venta(map, date, Float.parseFloat(monto), Integer.parseInt(id)));
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

    public void mostrarHistorialVentas() {
        System.out.println(historialVentas);
    }

    public List<Venta> getHistorialVentas() {
        return historialVentas;
    }
}

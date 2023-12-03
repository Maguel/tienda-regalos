package org.tecnologias.db;

import org.junit.jupiter.api.*;

public class DataBaseTests {
    @Test
    public void agregarPelucheTest() {
        Assertions.assertTrue(DataBase.getInstance().agregarProducto(new Peluche(100326009, "Oso Lotso", Provedor.MATTEL, 10)));
        Assertions.assertFalse(DataBase.getInstance().agregarProducto(new Peluche(100326009, "Oso Lotso", Provedor.MATTEL, 10)));
        Assertions.assertTrue(DataBase.getInstance().agregarProducto(new Peluche(100326010, "Teddy", Provedor.MUÑELOCOS, 10)));
    }

    @Test
    public void eliminarPeluchesTest() {
        DataBase db = DataBase.getInstance();
        Assertions.assertTrue(db.agregarProducto(new Peluche(1003, "Oso Lotso", Provedor.MATTEL, 10)));
        Assertions.assertFalse(db.agregarProducto(new Peluche(1003, "Oso Lotso", Provedor.MATTEL, 10)));
        Assertions.assertTrue(db.agregarProducto(new Peluche(1005, "Teddy", Provedor.MUÑELOCOS, 10)));
        Assertions.assertTrue(db.eliminarProducto(db.getProducto(1005)));
        Assertions.assertTrue(db.eliminarProducto(db.getProducto(475)));
    }

    @AfterAll
    public static void mostrar() {
        System.out.println("------------------");
        DataBase.getInstance().getInventario();
    }
}

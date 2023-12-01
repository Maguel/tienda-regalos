package org.tecnologias.db;

import org.junit.jupiter.api.*;
import org.tecnologias.db.DataBase;
import org.tecnologias.db.Peluche;
import org.tecnologias.db.Provedor;

public class DataBaseTests {
    @Test
    public void agregarPelucheTest() {
        Assertions.assertTrue(DataBase.getInstance().agregarProducto(new Peluche(100326009, "Oso Lotso", Provedor.MATTEL, 10)));
        Assertions.assertFalse(DataBase.getInstance().agregarProducto(new Peluche(100326009, "Oso Lotso", Provedor.MATTEL, 10)));
        Assertions.assertTrue(DataBase.getInstance().agregarProducto(new Peluche(100326010, "Teddy", Provedor.MUÑELOCOS, 10)));
    }

    @AfterAll
    public static void mostrar() {
        System.out.println("------------------");
        DataBase.getInstance().getInventario();
    }
}

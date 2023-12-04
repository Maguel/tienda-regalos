package org.tecnologias.db;

import org.junit.jupiter.api.*;

public class DataBaseTests {
    DataBase db = DataBase.getInstance();
    Peluche.Builder builder = new Peluche.Builder();
    @AfterAll
    public static void dbTesting() {
        DataBase.getInstance().getInventario();
    }
    @Test
    public void agregarPeluche() {
        Assertions.assertTrue(db.agregarProducto(
                builder.precio(50.56F)
                        .tamagno("Chiquito")
                        .clasificacion("Tierno")
                        .nombre("Peluchito")
                        .codigo(123123)
                        .cantidad(60)
                        .provedor("mattel")
                        .build()
                )
        );
    }
}

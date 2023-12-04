package org.tecnologias.db;

import org.junit.jupiter.api.*;

public class DataBaseTests {
    DataBase db = DataBase.getInstance();
    Peluche.Builder builder = new Peluche.Builder();
    @Test
    public void dbTesting() {
        DataBase.getInstance();
    }
    @Test
    public void agregarPeluche() {
        Assertions.assertFalse(db.agregarProducto(
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

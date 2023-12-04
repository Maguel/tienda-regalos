package org.tecnologias.db;

import org.junit.jupiter.api.*;

public class DataBaseTests {
    private static final DataBase db = DataBase.getInstance();
    private static final Peluche.Builder builder = new Peluche.Builder();
    @Test
    public void modificarPelucheTest() {
        Assertions.assertTrue(db.modificarCantidad(db.getProducto(123456), db.getProducto(123456).getCantidad() - 1));
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

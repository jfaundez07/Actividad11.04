import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    String[][] inventario;

    @BeforeEach
    void setUp() {
        inventario = new String[10][2];
    }

    @AfterEach
    void tearDown() {
        inventario = null;
    }

    @Test
    void agregarPoducto() {
        Main.agregarPoducto(inventario, "Producto 1", "10");
        assertEquals("Producto 1", inventario[0][0]);
        assertEquals("10", inventario[0][1]);

        Main.agregarPoducto(inventario, "Producto 2", "20");
        assertEquals("Producto 2", inventario[1][0]);
        assertEquals("20", inventario[1][1]);
    }

    @Test
    void venderProducto() {
        Main.agregarPoducto(inventario, "Producto 1", "10");
        Main.agregarPoducto(inventario, "Producto 2", "20");

        Main.venderProducto(inventario, 0, 5);
        assertEquals("5", inventario[0][1]);

        Main.venderProducto(inventario, 1, 10);
        assertEquals("10", inventario[1][1]);

    }

    @Test
    void buscarProducto() {
        Main.agregarPoducto(inventario, "Producto 1", "10");
        Main.agregarPoducto(inventario, "Producto 2", "20");

        assertEquals(0, Main.buscarProducto(inventario, "Producto 1"));
        assertEquals(1, Main.buscarProducto(inventario, "Producto 2"));

        assertEquals(-1, Main.buscarProducto(inventario, "Producto 4"));
        assertEquals(-1, Main.buscarProducto(inventario, "Producto 5"));
    }
}
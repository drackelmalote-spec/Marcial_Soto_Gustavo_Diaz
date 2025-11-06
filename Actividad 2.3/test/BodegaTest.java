import Controlador.Bodega;
import org.junit.Test;
import static org.junit.Assert.*;

public class BodegaTest {

    @Test
    public void testAgregar() {
        Bodega b = new Bodega();
        b.agregarProducto("A1", "Pan", 7);
        try {
            b.Agregar("A1", 5);
            assertTrue(true); // Si llegamos aquí, no hubo excepción
        } catch (Exception e) {
            fail("No debería lanzar excepción");
        }
    }

    @Test
    public void testRestar() {
        Bodega b = new Bodega();
        b.agregarProducto("B1", "Leche", 10);
        try {
            b.restar("B1", 3);
            assertTrue(true); // Si llegamos aquí, no hubo excepción
        } catch (Exception e) {
            fail("No debería lanzar excepción");
        }
    }

    @Test
    public void testNoPermiteStockNegativo() {
        Bodega b = new Bodega();
        b.agregarProducto("C1", "Arroz", 5);
        try {
            b.restar("C1", 10);
            fail("Debería lanzar una excepción cuando el stock queda negativo");
        } catch (IllegalArgumentException e) {
            assertTrue(true); // Si llegamos aquí, la excepción fue lanzada como se esperaba
        }
    }
}
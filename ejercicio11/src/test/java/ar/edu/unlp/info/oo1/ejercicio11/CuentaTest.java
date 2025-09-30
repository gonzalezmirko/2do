package ar.edu.unlp.info.oo1.ejercicio11;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CuentaTest {

    @Test
    public void testDepositoCajaDeAhorroConCosto() {
        CajaDeAhorro caja = new CajaDeAhorro();
        caja.depositar(1000);
        assertEquals(980, caja.getSaldo(), 0.01); // 2% menos
    }

    @Test
    public void testExtraccionCajaDeAhorroConCosto() {
        CajaDeAhorro caja = new CajaDeAhorro();
        caja.depositar(1000);
        assertTrue(caja.extraer(500)); // necesita 500 + 10
        assertEquals(470, caja.getSaldo(), 0.01);
    }

    @Test
    public void testTransferenciaCajaDeAhorro() {
        CajaDeAhorro caja = new CajaDeAhorro();
        caja.depositar(1000);
        CuentaCorriente cc = new CuentaCorriente(500);

        assertTrue(caja.transferirACuenta(200, cc));
        assertEquals(776, caja.getSaldo(), 0.01); // 200 + 4 de costo
        assertEquals(200, cc.getSaldo(), 0.01);
    }

    @Test
    public void testCuentaCorrienteDescubierto() {
        CuentaCorriente cc = new CuentaCorriente(500);
        cc.depositar(100);
        assertTrue(cc.extraer(500)); // saldo 100 + 500 descubierto >= 500
        assertEquals(-400, cc.getSaldo(), 0.01);
    }

    @Test
    public void testCuentaCorrienteSinDescubierto() {
        CuentaCorriente cc = new CuentaCorriente();
        assertFalse(cc.extraer(100)); // descubierto 0
    }
}

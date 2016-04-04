package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import negocio.Cliente;
import to.ContaTO;

public class ContaTOTest {
	ContaTO to;

	@Before
	public void setUp() throws Exception {
		to = new ContaTO();
		to.setConta(999);
		to.setAgencia(9999);
		to.setSaldo(1000);
		to.setCliente(new Cliente("Xampson", 2));
	}

	@Test
	public void testGets() {
		assertEquals("getConta", to.getConta(), 999);
		assertEquals("getAgencia", to.getAgencia(), 9999);
		assertEquals("getSaldo", to.getSaldo(), 1000, 0);
		assertEquals("getCliente", to.getCliente(), new Cliente("Xampson", 2));
	}

	@Test
	public void testEquals() {
		ContaTO copia = new ContaTO();
		copia.setConta(to.getConta());
		copia.setAgencia(to.getAgencia());
		copia.setSaldo(to.getSaldo());
		copia.setCliente(to.getCliente());
		assertEquals("teste to igual a copia", to, copia);
	}

}
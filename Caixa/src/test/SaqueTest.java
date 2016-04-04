package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import negocio.Conta;
import negocio.Dispenser;
import negocio.Saque;

public class SaqueTest {
	Saque saque;

	@Before
	public void setUp() throws Exception {
		saque = new Saque("21/3/2016", new Conta(99, 999, 1000, null), new Dispenser(), 78.0);
	}

	@Test
	public void testSacar() {
		assertEquals("testa sacar", saque.sacar(), "Valor invalido");
	}

}

package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import negocio.Conta;
import to.ExtratoTO;

public class ExtratoTOTest {
	ExtratoTO to;

	@Before
	public void setUp() throws Exception {
		to = new ExtratoTO();
		to.setId(9);
		to.setData("20/3/2016");
		to.setOperacao("Saque");
		to.setValor(100);
		to.setConta(new Conta(99, 9999, 1000, null));
	}

	@Test
	public void testGets() {
		assertEquals("getId", to.getId(), 9);
		assertEquals("getData", to.getData(), "20/3/2016");
		assertEquals("getOperacao", to.getOperacao(), "Saque");
		assertEquals("getValor", to.getValor(), 100, 0);
		assertEquals("getConta", to.getConta(), new Conta(99, 9999, 1000, null));
	}

	@Test
	public void testEquals() {
		ExtratoTO copia = new ExtratoTO();
		copia.setId(to.getId());
		copia.setData(to.getData());
		copia.setOperacao(to.getOperacao());
		copia.setValor(to.getValor());
		copia.setConta(to.getConta());
		assertEquals("teste to igual a copia", to, copia);
	}
}

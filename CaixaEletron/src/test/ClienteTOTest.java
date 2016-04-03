package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import to.ClienteTO;

public class ClienteTOTest {
	ClienteTO to;

	@Before
	public void setUp() throws Exception {
		to = new ClienteTO();
		to.setId(3);
		to.setNome("Bela Lugosi");
		to.setTipo(2);
	}

	@Test
	public void testGets() {
		assertEquals("getNome", to.getNome(), "Bela Lugosi");
		assertEquals("getFone", to.getTipo(), 2);
		assertEquals("getId", to.getId(), 3);
	}

	@Test
	public void testEquals() {
		ClienteTO copia = new ClienteTO();
		copia.setTipo(to.getTipo());
		copia.setNome(to.getNome());
		copia.setId(to.getId());
		assertEquals("teste to igual a copia", to, copia);
	}

}

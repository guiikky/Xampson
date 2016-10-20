package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import negocio.Cliente;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClienteTest {
	Cliente cliente, copia;
	static int id;

	@Before
	public void setUp() throws Exception {
		cliente = new Cliente("Bela Lugosi", 2);
		copia = new Cliente("Bela Lugosi", 2);
	}

	@Test
	public void test00Carregar() throws IOException {
		Cliente fixture = new Cliente(1, "Han Solo", 1);
		Cliente novo = new Cliente(1, null, 0);
		novo.carregar();
		assertEquals("testa carregar", novo, fixture);
	}

	@Test
	public void test01Criar() throws IOException {
		cliente.criar();
		copia.setId(cliente.getId());
		id = cliente.getId();
		assertEquals("testa criar", cliente, copia);
	}

	@Test
	public void test02Atualizar() throws IOException {
		cliente.setId(id);
		cliente.setTipo(1);
		copia.setId(id);
		copia.setTipo(1);
		cliente.atualizar();
		assertEquals("testa atualizar", cliente, copia);
	}

	@Test
	public void test03Excluir() throws IOException {
		cliente.setId(id);
		cliente.setNome(null);
		cliente.setTipo(0);
		copia.setId(id);
		copia.setNome(null);
		copia.setTipo(0);
		cliente.excluir();
		assertEquals("testa excluir", cliente, copia);
	}
}
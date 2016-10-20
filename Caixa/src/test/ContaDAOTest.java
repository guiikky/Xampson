package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import dao.ContaDAO;
import negocio.Cliente;
import to.ContaTO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ContaDAOTest {
	ContaDAO dao;
	ContaTO to;
	static int id;

	@Before
	public void setUp() throws Exception {
		dao = new ContaDAO();
		to = new ContaTO();
		to.setConta(999);
		to.setAgencia(9999);
		to.setSaldo(1000);
		to.setCliente(new Cliente("Xampson", 2));
	}

	@Test
	public void test00Carregar() throws IOException {
		ContaTO fixture = new ContaTO();
		fixture.setConta(2145987);
		fixture.setAgencia(9999);
		fixture.setSaldo(737000);
		fixture.setCliente(new Cliente(4, "Walter White", 1));
		ContaTO novo = dao.carregar(2145987);
		novo.setConta(2145987);
		assertEquals("testa carregar", novo, fixture);
	}

	@Test
	public void test01Inserir() throws IOException {
		dao.incluir(to);
		id = to.getCliente().getId();
		ContaTO novo = dao.carregar(to.getConta());
		novo.setConta(to.getConta());
		assertEquals("testa inserir", novo, to);
	}

	@Test
	public void test02Atualizar() throws IOException {
		to.getCliente().setId(id);
		to.setAgencia(99);
		to.setSaldo(1);
		dao.atualizar(to);
		ContaTO novo = dao.carregar(to.getConta());
		novo.setConta(to.getConta());
		to.getCliente().setId(novo.getCliente().getId());
		assertEquals("testa atualizar", novo, to);
	}

	@Test
	public void test03Excluir() throws IOException {
		to.setAgencia(0);
		to.setSaldo(0);
		to.getCliente().setId(id);
		dao.excluir(to);
		to.setCliente(null);
		ContaTO novo = dao.carregar(to.getConta());
		novo.setConta(to.getConta());
		assertEquals("testa excluir", novo, to);
	}
}
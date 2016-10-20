package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import dao.ClienteDAO;
import to.ClienteTO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClienteDAOTest {
	ClienteDAO dao;
	ClienteTO to;
	static int id;

	@Before
	public void setUp() throws Exception {
		dao = new ClienteDAO();
		to = new ClienteTO();
		to.setNome("Bela Lugosi");
		to.setTipo(2);
	}

	@Test
	public void test00Carregar() throws IOException {
		ClienteTO fixture = new ClienteTO();
		fixture.setId(1);
		fixture.setNome("Han Solo");
		fixture.setTipo(1);
		ClienteTO novo = dao.carregar(1);
		novo.setId(1);
		assertEquals("testa carregar", novo, fixture);
	}

	@Test
	public void test01Inserir() throws IOException {
		dao.incluir(to);
		to.setId(to.getId());
		id = to.getId();
		ClienteTO novo = dao.carregar(to.getId());
		novo.setId(to.getId());
		assertEquals("testa inserir", novo, to);
	}

	@Test
	public void test02Atualizar() throws IOException {
		to.setId(id);
		to.setTipo(1);
		dao.atualizar(to);
		ClienteTO novo = dao.carregar(to.getId());
		novo.setId(to.getId());
		assertEquals("testa atualizar", novo, to);
	}

	@Test
	public void test03Excluir() throws IOException {
		to.setId(id);
		to.setNome(null);
		to.setTipo(0);
		dao.excluir(to);
		ClienteTO novo = dao.carregar(to.getId());
		novo.setId(to.getId());
		assertEquals("testa excluir", novo, to);
	}
}

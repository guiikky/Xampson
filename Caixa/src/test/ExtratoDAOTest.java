package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import dao.ExtratoDAO;
import negocio.Conta;
import negocio.Extrato;
import to.ExtratoTO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExtratoDAOTest {
	ExtratoDAO dao;
	ExtratoTO to;
	static int id;

	@Before
	public void setUp() throws Exception {
		dao = new ExtratoDAO();
		to = new ExtratoTO();
		to.setData("20/3/2016");
		to.setOperacao("Saque");
		to.setValor(50.0);
		to.setConta(new Conta(99, 9999, 0, null));
	}

	@Test
	public void test00Carregar() throws IOException {
		Extrato fixture = new Extrato(1, "20/3/2016", "Saque", 50.0, new Conta(2398468, 9999, 0, null));
		Extrato novo = new Extrato(1, null, null, 0, null);
		novo.carregar();
		assertEquals("testa carregar", novo, fixture);
	}

	@Test
	public void test01Inserir() throws IOException {
		dao.incluir(to);
		to.setId(to.getId());
		ExtratoTO novo = dao.carregar(to.getId());
		novo.setId(to.getId());
		id = to.getId();
		assertEquals("testa inserir", to, novo);
	}

	@Test
	public void test02Atualizar() throws IOException {
		to.setId(id);
		to.setValor(100);
		dao.atualizar(to);
		ExtratoTO novo = dao.carregar(id);
		novo.setId(id);
		assertEquals("testa atualizar", to, novo);
	}

	@Test
	public void test03Excluir() throws IOException {
		to.setId(id);
		to.setData(null);
		to.setOperacao(null);
		to.setValor(0);
		to.setConta(null);
		dao.excluir(to);
		ExtratoTO novo = dao.carregar(id);
		novo.setId(id);
		assertEquals("testa excluir", to, novo);
	}
}

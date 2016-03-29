package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
		to.setConta(new Conta(99, 9999, 1000, null));
	}

	@Test
	public void test00Carregar() {
		String fixture = "20/3/2016Saque50.0";
		String aux[][] = Extrato.carregar(2398468);
		String novo = aux[0][0] + aux[0][1] + aux[0][2];
		assertEquals("testa carregar", novo, fixture);
	}

	@Test
	public void test01Inserir() {
		ExtratoTO aux = dao.incluir(to);
		to.setId(aux.getId());
		id = to.getId();
		assertEquals("testa inserir", aux, to);
	}

	@Test
	public void test02Atualizar() {
		to.setId(id);
		to.setValor(100);
		dao.atualizar(to);
		String dados = to.getData() + to.getOperacao() + to.getValor();
		String aux[][] = Extrato.carregar(99);
		String novo = aux[0][0] + aux[0][1] + aux[0][2];
		assertEquals("testa atualizar", dados, novo);
	}

	@Test
	public void test03Excluir() {
		to.setId(id);
		to.setData(null);
		to.setOperacao(null);
		to.setValor(0);
		to.setConta(null);
		dao.excluir(to);
		assertNotEquals("testa excluir", to, null);
	}
}

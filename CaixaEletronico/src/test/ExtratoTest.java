package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import negocio.Conta;
import negocio.Extrato;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExtratoTest {
	Extrato extrato, copia;
	static int id;

	@Before
	public void setUp() throws Exception {
		extrato = new Extrato("20/3/2016", "Saque", 100, new Conta(99, 9999, 1000, null));
		copia = new Extrato("20/3/2016", "Saque", 100, new Conta(99, 9999, 1000, null));
	}

	@Test
	public void test00Carregar() {
		String fixture = "20/3/2016Saque50.0";
		String aux[][] = Extrato.carregar(2398468);
		String novo = aux[0][0] + aux[0][1] + aux[0][2];
		assertEquals("testa carregar", novo, fixture);

	}

	@Test
	public void test01Criar() {
		extrato.incluir();
		id = extrato.getId();
		copia.setId(id);
		assertEquals("testa criar", extrato, copia);
	}

	@Test
	public void test02Atualizar() {
		extrato.setValor(100.00);
		copia.setValor(100.00);
		extrato.atualizar();
		assertEquals("testa atualizar", extrato, copia);
	}

	@Test
	public void test03Excluir() {
		extrato.setId(id);
		extrato.setData(null);
		extrato.setOperacao(null);
		extrato.setValor(0);
		extrato.setConta(null);
		copia.setId(id);
		copia.setData(null);
		copia.setOperacao(null);
		copia.setValor(0);
		copia.setConta(null);
		extrato.excluir();
		assertEquals("testa excluir", extrato, copia);
	}
}

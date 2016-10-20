package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

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
	public void test00Carregar() throws IOException {
		Extrato fixture = new Extrato(1, "20/3/2016", "Saque", 50.0, new Conta(2398468, 9999, 0, null));
		Extrato novo = new Extrato(1, null, null, 0, null);
		novo.carregar();
		assertEquals("testa carregar", novo, fixture);

	}

	@Test
	public void test01Criar() throws IOException {
		extrato.incluir();
		id = extrato.getId();
		copia.setId(id);
		assertEquals("testa criar", extrato, copia);
	}

	@Test
	public void test02Atualizar() throws IOException {
		extrato.setValor(100.00);
		copia.setValor(100.00);
		extrato.atualizar();
		assertEquals("testa atualizar", extrato, copia);
	}

	@Test
	public void test03Excluir() throws IOException {
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

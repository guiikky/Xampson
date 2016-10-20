package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import negocio.Cliente;
import negocio.Conta;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ContaTest {
	Conta conta, copia;
	static int id;

	@Before
	public void setUp() throws Exception {
		conta = new Conta(999, 99, 1000, new Cliente("Xampson", 2));
		copia = new Conta(999, 99, 1000, new Cliente("Xampson", 2));
	}

	@Test
	public void test00Carregar() throws IOException {
		Conta fixture = new Conta(2145987, 9999, 737000.00, new Cliente(4, "Walter White", 1));
		Conta novo = new Conta(2145987, 0, 0, null);
		novo.carregar();
		assertEquals("testa carregar", novo, fixture);
	}

	@Test
	public void test01Criar() throws IOException {
		conta.criar();
		id = conta.getCliente().getId();
		copia.getCliente().setId(id);
		assertEquals("testa criar", conta, copia);
	}

	@Test
	public void test02Atualizar() throws IOException {
		conta.setAgencia(1);
		copia.setAgencia(1);
		conta.atualizar();
		assertEquals("testa atualizar", conta, copia);
	}

	@Test
	public void test03Excluir() throws IOException {
		conta.getCliente().setId(id);
		conta.setAgencia(0);
		conta.setSaldo(0);
		copia.getCliente().setId(id);
		copia.setAgencia(0);
		copia.setSaldo(0);
		conta.excluir();
		assertEquals("testa excluir", conta, copia);
	}

	@Test
	public void conferirSaldo() {
		assertEquals("testa saldo", conta.conferirSaldo(0), false);
	}

}

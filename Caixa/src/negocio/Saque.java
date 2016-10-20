package negocio;

import java.io.IOException;

import to.SaqueTO;

public class Saque {
	private String data;
	private Conta conta;
	private Dispenser dispenser;
	private double valor;

	public Saque(String data, Conta conta, Dispenser dispenser, double valor) {
		this.data = data;
		this.conta = conta;
		this.dispenser = dispenser;
		this.valor = valor;
	}
	
	public Saque(String data, Conta conta, double valor) {
		this.data = data;
		this.conta = conta;
		this.dispenser = new Dispenser();
		this.valor = valor;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Dispenser getDispenser() {
		return dispenser;
	}

	public void setDispenser(Dispenser dispenser) {
		this.dispenser = dispenser;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public SaqueTO getTO() {
		SaqueTO to = new SaqueTO();
		to.setData(data);
		to.setConta(conta);
		to.setDispenser(dispenser);
		to.setValor(valor);
		return to;
	}

	public int sacar() throws IOException {
		if (conta.conferirSaldo(valor)) {
			if ((int) valor % 10 == 0) {
				if (dispenser.contar((int) valor)) {
					Extrato ex = new Extrato(data, "Saque", valor, conta);
					ex.incluir();
					conta.setSaldo(conta.getSaldo() - valor);
					conta.atualizar();
					return 0;
				} else {
					return 1;
				}
			} else {
				return 2;
			}
		} else {
			return 3;
		}

	}
}

package negocio;

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

	public String sacar() {
		if (conta.conferirSaldo(valor)) {
			if ((int) valor % 10 == 0) {
				if (dispenser.contar((int) valor)) {
					Extrato ex = new Extrato(data, "Saque", valor, conta);
					ex.incluir();
					conta.setSaldo(conta.getSaldo() - valor);
					conta.atualizar();
					return "Nao emitimos comprovante";
				} else {
					return "Notas indiponiveis";
				}
			} else {
				return "Valor invalido";
			}
		} else {
			return "Saldo invalido";
		}

	}

}

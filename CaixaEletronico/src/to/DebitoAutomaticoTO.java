package to;

import negocio.Conta;

public class DebitoAutomaticoTO {
	private int operadora;
	private int consumidor;
	private String data;
	private Conta conta;

	public int getOperadora() {
		return operadora;
	}

	public void setOperadora(int operadora) {
		this.operadora = operadora;
	}

	public int getConsumidor() {
		return consumidor;
	}

	public void setConsumidor(int consumidor) {
		this.consumidor = consumidor;
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
}

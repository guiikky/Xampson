package negocio;

import dao.DebitoAutomaticoDAO;
import to.DebitoAutomaticoTO;

public class DebitoAutomatico {
	private int operadora;
	private int consumidor;
	private String data;
	private Conta conta;

	public DebitoAutomatico(int operadora, int consumidor, String data, Conta conta) {
		this.operadora = operadora;
		this.consumidor = consumidor;
		this.data = data;
		this.conta = conta;
	}

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

	public void criar() {
		DebitoAutomaticoDAO dao = new DebitoAutomaticoDAO();
		DebitoAutomaticoTO to = new DebitoAutomaticoTO();
		Extrato ex = new Extrato(data, "DebitoAutomatico", 0, conta);
		to.setOperadora(operadora);
		to.setConsumidor(consumidor);
		to.setData(data);
		to.setConta(conta);
		dao.incluir(to);
		ex.incluir();
	}

	@Override
	public String toString() {
		return "DebitoAutomaticoTO [operadora=" + operadora + ", consumidor=" + consumidor + ", data=" + data
				+ ", conta=" + conta + "]";
	}
}
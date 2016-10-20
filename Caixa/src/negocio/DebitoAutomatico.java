package negocio;

import java.io.IOException;

import dao.DebitoAutomaticoDAO;
import to.DebitoAutomaticoTO;

public class DebitoAutomatico {
	private int id;
	private int operadora;
	private int consumidor;
	private String data;
	private double valor;
	private Conta conta;

	public DebitoAutomatico(int id, int operadora, int consumidor, String data, double valor, Conta conta) {
		this.id = id;
		this.operadora = operadora;
		this.consumidor = consumidor;
		this.data = data;
		this.valor = valor;
		this.conta = conta;
	}

	public DebitoAutomatico(int operadora, int consumidor, String data, double valor, Conta conta) {
		this.operadora = operadora;
		this.consumidor = consumidor;
		this.data = data;
		this.valor = valor;
		this.conta = conta;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public void criar() throws IOException {
		DebitoAutomaticoDAO dao = new DebitoAutomaticoDAO();
		DebitoAutomaticoTO to = getTO();
		Extrato ex = new Extrato(data, "DebitoAutomatico", valor, conta);
		dao.incluir(to);
		ex.incluir();
	}

	public DebitoAutomaticoTO getTO() {
		DebitoAutomaticoTO to = new DebitoAutomaticoTO();
		to.setOperadora(operadora);
		to.setConsumidor(consumidor);
		to.setData(data);
		to.setValor(valor);
		to.setConta(conta);
		return to;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DebitoAutomatico other = (DebitoAutomatico) obj;
		if (consumidor != other.consumidor)
			return false;
		if (conta == null) {
			if (other.conta != null)
				return false;
		} else if (!conta.equals(other.conta))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (id != other.id)
			return false;
		if (operadora != other.operadora)
			return false;
		if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
			return false;
		return true;
	}

}
package negocio;

import to.TransferenciaTO;

public class Transferencia {
	private Conta cop;
	private Conta cos;
	private String data;
	private double valor;

	public Transferencia(Conta cop, Conta cos, String data, double valor) {
		this.cop = cop;
		this.cos = cos;
		this.data = data;
		this.valor = valor;
	}

	public Conta getCop() {
		return cop;
	}

	public void setCop(Conta cop) {
		this.cop = cop;
	}

	public Conta getCos() {
		return cos;
	}

	public void setCos(Conta cos) {
		this.cos = cos;
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

	public TransferenciaTO getTO() {
		TransferenciaTO to = new TransferenciaTO();
		to.setCop(cop);
		to.setCos(cos);
		to.setData(data);
		to.setValor(valor);
		return to;
	}

	public void transferir() {
		if (cop.conferirSaldo(valor)) {
			Extrato ex = new Extrato(data, "Transferencia", valor, cop);
			cop.setSaldo(cop.getSaldo() - valor);
			cos.setSaldo(cop.getSaldo() + valor);
			cop.atualizar();
			cos.atualizar();
			ex.incluir();
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transferencia other = (Transferencia) obj;
		if (cop == null) {
			if (other.cop != null)
				return false;
		} else if (!cop.equals(other.cop))
			return false;
		if (cos == null) {
			if (other.cos != null)
				return false;
		} else if (!cos.equals(other.cos))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
			return false;
		return true;
	}

}

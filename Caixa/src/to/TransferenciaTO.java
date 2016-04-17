package to;

import negocio.Conta;

public class TransferenciaTO {
	private Conta cop;
	private Conta cos;
	private String data;
	private double valor;

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransferenciaTO other = (TransferenciaTO) obj;
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

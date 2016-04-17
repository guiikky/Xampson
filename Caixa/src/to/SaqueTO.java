package to;

import negocio.Conta;
import negocio.Dispenser;

public class SaqueTO {
	private String data;
	private Conta conta;
	private Dispenser dispenser;
	private double valor;

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SaqueTO other = (SaqueTO) obj;
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
		if (dispenser == null) {
			if (other.dispenser != null)
				return false;
		} else if (!dispenser.equals(other.dispenser))
			return false;
		if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
			return false;
		return true;
	}

}

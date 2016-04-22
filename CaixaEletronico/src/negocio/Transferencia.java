package negocio;

public class Transferencia {
	private Conta cop;
	private Conta cos;
	private String data;
	private double valor;

	public Transferencia(Conta cop, int conta, int agencia, String data, double valor) {
		this.cop = cop;
		this.cos = new Conta(conta, agencia, 0, null);
		this.data = data;
		this.valor = valor;
		cos.carregar();
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

	public void transferir() {
		Extrato ex = new Extrato(data, "Transferencia", valor, cop);
		cop.setSaldo(cop.getSaldo() - valor);
		cos.setSaldo(cop.getSaldo() + valor);
		cop.atualizar();
		cos.atualizar();
		ex.incluir();
	}

}

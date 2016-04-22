package negocio;

import dao.ContaDAO;
import to.ContaTO;

public class Conta {
	private int conta;
	private int agencia;
	private double saldo;
	private Cliente cliente;
	private static Conta instance;

	public Conta(int conta, int agencia, double saldo, Cliente cliente) {
		this.conta = conta;
		this.agencia = agencia;
		this.saldo = saldo;
		this.cliente = cliente;
	}

	public int getConta() {
		return conta;
	}

	public void setConta(int conta) {
		this.conta = conta;
	}

	public int getAgencia() {
		return agencia;
	}

	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public static void newConta(int conta, int agencia) {
		if (instance == null) {
			Conta aux = new Conta(conta, agencia, 0, null);
			aux.carregar();
			instance = aux;
		}
	}

	public static Conta getInstance() {
		return instance;
	}

	public static void destroy() {
		instance = null;
	}

	public boolean conferirSaldo(double valor) {
		return saldo >= valor && valor > 0;
	}

	public ContaTO getTO() {
		ContaTO to = new ContaTO();
		to.setConta(conta);
		to.setAgencia(agencia);
		to.setSaldo(saldo);
		to.setCliente(cliente);
		return to;
	}

	public void criar() {
		ContaDAO dao = new ContaDAO();
		ContaTO to = getTO();
		dao.incluir(to);
		cliente.setId(to.getCliente().getId());
	}

	public void atualizar() {
		ContaDAO dao = new ContaDAO();
		ContaTO to = getTO();
		dao.atualizar(to);
	}

	public void excluir() {
		ContaDAO dao = new ContaDAO();
		ContaTO to = new ContaTO();
		to.setConta(conta);
		to.setCliente(cliente);
		dao.excluir(to);
	}

	public void carregar() {
		ContaDAO dao = new ContaDAO();
		ContaTO to = dao.carregar(conta);
		agencia = to.getAgencia();
		saldo = to.getSaldo();
		cliente = to.getCliente();
	}

	@Override
	public String toString() {
		return "Conta [conta=" + conta + ", agencia=" + agencia + ", saldo=" + saldo + ", cliente=" + cliente + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		if (agencia != other.agencia)
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (conta != other.conta)
			return false;
		if (Double.doubleToLongBits(saldo) != Double.doubleToLongBits(other.saldo))
			return false;
		return true;
	}

}
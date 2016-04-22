package negocio;

import java.util.ArrayList;

import dao.ExtratoDAO;
import to.ExtratoTO;

public class Extrato {
	private int id;
	private String data;
	private String operacao;
	private double valor;
	private Conta conta;

	public Extrato(int id, String data, String operacao, double valor, Conta conta) {
		this.id = id;
		this.data = data;
		this.operacao = operacao;
		this.valor = valor;
		this.conta = conta;
	}

	public Extrato(String data, String operacao, double valor, Conta conta) {
		this.data = data;
		this.operacao = operacao;
		this.valor = valor;
		this.conta = conta;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
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

	public void incluir() {
		ExtratoDAO dao = new ExtratoDAO();
		ExtratoTO to = new ExtratoTO();
		to.setData(data);
		to.setOperacao(operacao);
		to.setValor(valor);
		to.setConta(conta);
		dao.incluir(to);
		id = to.getId();
	}

	public void atualizar() {
		ExtratoDAO dao = new ExtratoDAO();
		ExtratoTO to = new ExtratoTO();
		to.setId(id);
		to.setData(data);
		to.setOperacao(operacao);
		to.setValor(valor);
		to.setConta(conta);
		dao.atualizar(to);
	}

	public void excluir() {
		ExtratoDAO dao = new ExtratoDAO();
		ExtratoTO to = new ExtratoTO();
		to.setId(id);
		dao.excluir(to);
	}
	
	public void carregar(){
		ExtratoDAO dao = new ExtratoDAO();
		ExtratoTO to = dao.carregar(id);
		data = to.getData();
		operacao = to.getOperacao();
		valor = to.getValor();
		conta = to.getConta();
		
	}

	public static ArrayList<ExtratoTO> carregar(int id) {
		ExtratoDAO dao = new ExtratoDAO();
		ArrayList<ExtratoTO> lista = dao.carregarTudo(id);
		return lista;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Extrato other = (Extrato) obj;
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
		if (operacao == null) {
			if (other.operacao != null)
				return false;
		} else if (!operacao.equals(other.operacao))
			return false;
		if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
			return false;
		return true;
	}

}

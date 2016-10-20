package negocio;

import java.io.IOException;

import dao.ClienteDAO;
import to.ClienteTO;

public class Cliente {
	private int id;
	private String nome;
	private int tipo;

	public Cliente(int id, String nome, int tipo) {
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
	}

	public Cliente(String nome, int tipo) {
		this.nome = nome;
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public void criar() throws IOException {
		ClienteDAO dao = new ClienteDAO();
		ClienteTO to = new ClienteTO();
		to.setNome(nome);
		to.setTipo(tipo);
		dao.incluir(to);
		id = to.getId();
	}

	public void atualizar() throws IOException {
		ClienteDAO dao = new ClienteDAO();
		ClienteTO to = new ClienteTO();
		to.setId(id);
		to.setNome(nome);
		to.setTipo(tipo);
		dao.atualizar(to);
	}

	public void excluir() throws IOException {
		ClienteDAO dao = new ClienteDAO();
		ClienteTO to = new ClienteTO();
		to.setId(id);
		dao.excluir(to);
	}

	public void carregar() throws IOException {
		ClienteDAO dao = new ClienteDAO();
		ClienteTO to = dao.carregar(id);
		nome = to.getNome();
		tipo = to.getTipo();
	}

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", tipo=" + tipo + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}

}

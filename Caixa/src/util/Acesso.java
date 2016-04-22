package util;

import negocio.Conta;

public class Acesso {
	private int conta;
	private int agencia;
	private int senha;

	public Acesso(int conta, int agencia, int senha) {
		this.conta = conta;
		this.agencia = agencia;
		this.senha = senha;
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

	public int getSenha() {
		return senha;
	}

	public void setSenha(int senha) {
		this.senha = senha;
	}

	public int validar() {
		String obj = conta + "/" + senha + "/" + agencia;
		String normal[] = Dados.acesso();
		boolean status[] = status(normal);
		int posicao = buscaBinaria(normal, obj);
		// System.out.println(posicao);

		if (posicao > -1) {
			if (!status[posicao]) {
				new Dados(normal, posicao, 0);
				Conta.newConta(conta, agencia);

				// Senha nao cadastrada
				if (normal[posicao].substring(18, 21).equals("-1 ")) {
					return 0;
				}
				// Senha ja cadastrada
				else {
					return 1;
				}
				// Conta bloqueada
			} else {
				return 2;
			}
			// Conta invalida
		} else {
			return 3;
		}
	}

	// Metodo busca binaria (Requisito)
	public int buscaBinaria(String vet[], String proc) {
		int inicio = 0, fim = vet.length - 1;

		while (inicio <= fim) {
			int meio = (inicio + fim) / 2;
			if (vet[meio].substring(0, 17).equals(proc)) {
				return meio;
			} else if (vet[meio].compareTo(proc) < 0) {
				inicio = meio + 1;
			} else {
				fim = meio - 1;
			}
		}
		return -1;
	}

	// Metodo auxiliar das flags das contas
	public boolean[] status(String normal[]) {
		boolean resp[] = new boolean[normal.length];
		for (int i = 0; i < normal.length; i++) {
			if (normal[i].substring(22).equals("false")) {
				resp[i] = false;
			} else {
				resp[i] = true;
			}
		}
		return resp;
	}

}
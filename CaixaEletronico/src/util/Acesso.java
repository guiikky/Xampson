package util;

import javax.swing.JOptionPane;

import criptografia.AES;
import idioma.Idioma;
import interfaces.AcessarCodigoGUI;
import interfaces.GerarCodigoGUI;
import interfaces.ValidarUsuarioGUI;
import negocio.Conta;

public class Acesso {
	// Acesso ao txt criptografado
	public Acesso(int conta, int senha, int agencia) {
		String obj = conta + "/" + senha + "/" + agencia;
		String crip[] = Dados.acesso();
		String normal[] = AES.decrip(crip);
		boolean status[] = status(normal);
		int posicao = buscaBinaria(normal, obj);
		System.out.println(posicao);

		if (posicao > -1) {
			if (!status[posicao]) {
				// Senha nao cadastrada
				new Dados(normal, posicao);
				Conta.getInstance(conta, agencia);
				Conta.getInstance().carregar();
				if (normal[posicao].substring(18, 21).equals("-1 ")) {
					new GerarCodigoGUI();
				}
				// Senha ja cadastrada
				else {
					new AcessarCodigoGUI(0);
				}
			} else {
				JOptionPane.showMessageDialog(null, Idioma.getRb().getString("avisosCodigo3"),
						Idioma.getRb().getString("avisosAcesso2"), 2);
				new ValidarUsuarioGUI();
			}
		} else {
			JOptionPane.showMessageDialog(null, Idioma.getRb().getString("avisosAcesso1"),
					Idioma.getRb().getString("avisosAcesso2"), 2);
			new ValidarUsuarioGUI();
		}

	}

	// Metodo busca binaria (Requisito)
	public static int buscaBinaria(String vet[], String proc) {
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
	public static boolean[] status(String normal[]) {
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
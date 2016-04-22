package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Formatter;

import javax.swing.JOptionPane;

import criptografia.AES;
import idioma.Idioma;
import interfaces.AcessarCodigoGUI;
import interfaces.MenuGUI;
import interfaces.ValidarUsuarioGUI;

public class Dados {
	private static String dados[];
	private static int posicao;

	public Dados(String vet[], int i) {
		dados = vet;
		posicao = i;
	}

	public static String[] getDados() {
		return dados;
	}

	public static void setDados(String[] dados) {
		Dados.dados = dados;
	}

	public static int getPosicao() {
		return posicao;
	}

	public static void setPosicao(int posicao) {
		Dados.posicao = posicao;
	}

	public static boolean verificar(String ag, String co) {
		for (int i = 0; i < dados.length; i++) {
			if (dados[i].substring(13, 17).equals(ag) && dados[i].substring(0, 7).equals(co)) {
				return true;
			}
		}
		return false;
	}

	public static String[] acesso() {
		String result = "";
		File arquivo = new File(System.getProperty("user.dir") + "\\src\\criptografia\\arqCrip.txt");
		try (FileReader fr = new FileReader(arquivo); BufferedReader br = new BufferedReader(fr);) {
			while (br.ready()) {
				result += br.readLine() + "\n";
			}
		} catch (Exception erro) {
		}
		return result.split("\n");
	}

	public static void gerarCodigo(String x) {
		try (Formatter saida = new Formatter(System.getProperty("user.dir") + "\\src\\criptografia\\arqCrip.txt");
				Formatter saida2 = new Formatter(System.getProperty("user.dir") + "\\src\\criptografia\\arq.txt");) {

			String aux = dados[posicao];
			dados[posicao] = aux.substring(0, 17) + "/" + x + "/" + aux.substring(22);
			String quebraDeLinha = System.getProperty("line.separator");
			saida2.format("conta/senha/agencia/codAcesso/status" + quebraDeLinha + quebraDeLinha);

			String crip[] = AES.crip(dados);

			for (int i = 0; i < crip.length; i++) {
				saida.format(crip[i] + quebraDeLinha);
				saida2.format(dados[i] + quebraDeLinha);
			}
		} catch (Exception e) {
		}
		new AcessarCodigoGUI(0);
	}

	public static void acessarCodigo(String x, int qtd) {
		// Acerto o cod de acesso
		if (dados[posicao].substring(18, 21).equals(x)) {
			new MenuGUI();
		}

		// Erro 1 ou 2 vezes o cod de acesso
		else if (qtd < 2) {
			JOptionPane.showMessageDialog(null, Idioma.getRb().getString("avisosCodigo1"),
					Idioma.getRb().getString("avisosAcesso2"), 2);
			// qtd++;
			new AcessarCodigoGUI(++qtd);
		}

		// Erro 3 vezes cod de acesso, conta bloqueada (Requisito)
		else {
			try (Formatter saida = new Formatter(System.getProperty("user.dir") + "\\src\\criptografia\\arqCrip.txt");
					Formatter saida2 = new Formatter(
							System.getProperty("user.dir") + "\\src\\criptografia\\arq.txt");) {

				String aux = dados[posicao];
				dados[posicao] = aux.substring(0, 21) + "/" + true;
				String quebraDeLinha = System.getProperty("line.separator");
				saida2.format("conta/senha/agencia/codAcesso/status" + quebraDeLinha + quebraDeLinha);

				String crip[] = AES.crip(dados);

				for (int i = 0; i < crip.length; i++) {
					saida.format(crip[i] + quebraDeLinha);
					saida2.format(dados[i] + quebraDeLinha);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			JOptionPane.showMessageDialog(null, Idioma.getRb().getString("avisosCodigo3"),
					Idioma.getRb().getString("avisosAcesso2"), 2);
			new ValidarUsuarioGUI();
		}
	}

}

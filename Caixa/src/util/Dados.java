package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Formatter;
import java.util.Random;

import criptografia.AES;

public class Dados {
	private static String dados[];
	private static int posicao;
	private static int cont;
	private static String path;

	public Dados(String dados[], int posicao, int cont) {
		Dados.dados = dados;
		Dados.posicao = posicao;
		Dados.cont = cont;
	}

	public Dados(String path) {
		Dados.path = path;
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

	public static int getCont() {
		return cont;
	}

	public static void setCont(int cont) {
		Dados.cont = cont;
	}

	public static String getPath() {
		return path;
	}

	public static void setPath(String path) {
		Dados.path = path;
	}

	public static boolean verificar(String co, String ag) {
		for (int i = 0; i < dados.length; i++) {
			if (dados[i].substring(0, 7).equals(co) && dados[i].substring(13, 17).equals(ag)) {
				return true;
			}
		}
		return false;
	}

	public static int[] random() {
		Random gnt = new Random();
		int vet[] = new int[10];
		for (int i = 0; i < vet.length; i++) {
			vet[i] = -1;
		}
		for (int i = 0; i < vet.length; i++) {
			int aux = gnt.nextInt(10);
			for (int j = 0; j < vet.length; j++) {
				if (aux == vet[j]) {
					aux = gnt.nextInt(10);
					j = -1;
				}
			}
			vet[i] = aux;
		}
		return vet;
	}

	public static String[] acesso() {
		String result = "";
		File arquivo = new File(path + "\\criptografia\\arqCrip.txt");
		try (FileReader fr = new FileReader(arquivo); BufferedReader br = new BufferedReader(fr);) {
			while (br.ready()) {
				result += br.readLine() + "\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return AES.decrip(result.split("\n"));
	}

	public static void gerarCodigo(String x) {
		try (Formatter saida = new Formatter(path + "\\criptografia\\arqCrip.txt");
				Formatter saida2 = new Formatter(path + "\\criptografia\\arq.txt");) {

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
			e.printStackTrace();
		}
	}

	public static int acessarCodigo(String x) {
		// Acerto o cod de acesso
		if (dados[posicao].substring(18, 21).equals(x)) {
			return 0;
		}
		// Erro 1 ou 2 vezes o cod de acesso
		else if (cont < 2) {
			cont++;
			return 1;
		}
		// Erro 3 vezes cod de acesso, conta bloqueada (Requisito)
		else {
			try (Formatter saida = new Formatter(path + "\\criptografia\\arqCrip.txt");
					Formatter saida2 = new Formatter(path + "\\criptografia\\arq.txt");) {

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
			return 2;
		}
	}

}

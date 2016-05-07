package criptografia;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

//Classe da criptografia
public class AES {
	private static String IV = "AAAAAAAAAAAAAAAA";

	public static byte[] encrypt(String textopuro, String chaveencriptacao) throws Exception {
		Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding");
		SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("ISO-8859-1"), "AES");
		encripta.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("ISO-8859-1")));
		return encripta.doFinal(textopuro.getBytes("ISO-8859-1"));
	}

	public static String decrypt(byte[] textoencriptado, String chaveencriptacao) throws Exception {
		Cipher decripta = Cipher.getInstance("AES/CBC/PKCS5Padding");
		SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("ISO-8859-1"), "AES");
		decripta.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes("ISO-8859-1")));
		return new String(decripta.doFinal(textoencriptado), "ISO-8859-1");
	}

	public static String[] crip(String normal[]) {
		String crip[] = new String[normal.length];
		for (int i = 0; i < normal.length; i++) {
			try {
				byte aux[] = encrypt(normal[i], "aaaaaaaaaaaaaaaa");
				String x = "";
				for (int j = 0; j < aux.length; j++) {
					x += aux[j] + " ";
				}
				crip[i] = x;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return crip;
	}

	public static String[] decrip(String crip[]) {
		String normal[] = new String[crip.length];
		for (int i = 0; i < crip.length; i++) {
			String aux[] = crip[i].split(" ");
			byte novo[] = new byte[aux.length];
			for (int j = 0; j < aux.length; j++) {
				novo[j] = new Byte(aux[j]);
			}
			try {
				normal[i] = decrypt(novo, "aaaaaaaaaaaaaaaa");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return normal;
	}
}
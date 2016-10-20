package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import negocio.Cliente;
import negocio.Conta;
import negocio.Extrato;
import negocio.Saque;
import negocio.Transferencia;
import to.ExtratoTO;

public class JSon {

	public static StringBuilder montaJSon(HttpServletRequest request) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = request.getReader();
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line).append('\n');
			}
		} finally {
			reader.close();
		}
		return sb;
	}

	public static String listToJSon(ArrayList<ExtratoTO> lista) {
		JSONArray vetor = new JSONArray();
		for (ExtratoTO to : lista) {
			JSONObject object = new JSONObject();
			try {
				object.put("id", to.getId());
				object.put("data", to.getData());
				object.put("operacao", to.getOperacao());
				object.put("valor", to.getValor());
				object.put("conta", to.getConta());
				vetor.put(object);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return vetor.toString();
	}

	public static Conta jSonToConta(String json) throws IOException {
		try {
			JSONObject registro = new JSONObject(json);
			int conta = registro.getInt("conta");
			int agencia = registro.getInt("agencia");
			double saldo = registro.getDouble("saldo");
			Cliente cliente = (Cliente) registro.get("cliente");
			return new Conta(conta, agencia, saldo, cliente);
		} catch (JSONException jsone) {
			jsone.printStackTrace();
			throw new IOException(jsone);
		}
	}

	public static String contaToJSon(Conta conta) throws IOException {
		JSONObject object = new JSONObject();
		try {
			object.put("conta", conta.getConta());
			object.put("agencia", conta.getAgencia());
			object.put("saldo", conta.getSaldo());
			object.put("cliente", conta.getCliente());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return object.toString();
	}

	public static Acesso jSonToAcesso(String json) throws IOException {
		try {
			JSONObject registro = new JSONObject(json);
			int conta = registro.getInt("conta");
			int agencia = registro.getInt("agencia");
			int senha = registro.getInt("senha");
			return new Acesso(conta, agencia, senha);
		} catch (JSONException jsone) {
			jsone.printStackTrace();
			throw new IOException(jsone);
		}
	}

	public static String acessoToJSon(Acesso acesso) throws IOException {
		JSONObject object = new JSONObject();
		try {
			object.put("conta", acesso.getConta());
			object.put("agencia", acesso.getAgencia());
			object.put("senha", acesso.getSenha());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return object.toString();
	}

	public static Saque jSonToSaque(String json) throws IOException {
		try {
			JSONObject registro = new JSONObject(json);
			String data = registro.getString("data");
			int conta = registro.getInt("conta");
			double valor = registro.getDouble("valor");
			Conta cont = new Conta(conta, 0, 0, null);
			cont.carregar();
			return new Saque(data, cont, valor);
		} catch (JSONException jsone) {
			jsone.printStackTrace();
			throw new IOException(jsone);
		}
	}

	public static String saqueToJSon(Saque saque) throws IOException {
		JSONObject object = new JSONObject();
		try {
			object.put("data", saque.getData());
			object.put("conta", saque.getConta());
			object.put("valor", saque.getValor());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return object.toString();
	}

	public static Extrato jSonToExtrato(String json) throws IOException {
		try {
			JSONObject registro = new JSONObject(json);
			int id = registro.getInt("id");
			String data = registro.getString("data");
			String operacao = registro.getString("operacao");
			double valor = registro.getDouble("valor");
			Conta conta = (Conta) registro.get("conta");
			return new Extrato(id, data, operacao, valor, conta);
		} catch (JSONException jsone) {
			jsone.printStackTrace();
			throw new IOException(jsone);
		}
	}

	public static String extratoToJSon(Extrato extrato) throws IOException {
		JSONObject object = new JSONObject();
		try {
			object.put("id", extrato.getId());
			object.put("data", extrato.getData());
			object.put("operacao", extrato.getOperacao());
			object.put("valor", extrato.getValor());
			object.put("conta", extrato.getConta());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return object.toString();
	}

	public static Transferencia jSonToTransferencia(String json) throws IOException {
		try {
			JSONObject registro = new JSONObject(json);
			Conta cop = new Conta(registro.getInt("cop"), 0, 0, null);
			Conta cos = new Conta(registro.getInt("cos"), 0, 0, null);
			String data = registro.getString("data");
			double valor = registro.getDouble("valor");
			cop.carregar();
			cos.carregar();
			return new Transferencia(cop, cos, data, valor);
		} catch (JSONException jsone) {
			jsone.printStackTrace();
			throw new IOException(jsone);
		}
	}

	public static String transferenciaToJSon(Transferencia transferencia) throws IOException {
		JSONObject object = new JSONObject();
		try {
			object.put("cop", transferencia.getCop());
			object.put("cos", transferencia.getCos());
			object.put("data", transferencia.getData());
			object.put("valor", transferencia.getValor());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return object.toString();
	}

	public static String errorToJSon(Exception e) {
		JSONObject object = new JSONObject();
		try {
			object.put("error", e.toString());
		} catch (JSONException e1) {
			e.printStackTrace();
		}
		return object.toString();
	}
}

package comando;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.Conta;
import negocio.Transferencia;
import util.Dados;

public class CriarTransferencia implements Comando {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pConta = request.getParameter("conta");
		String pAgencia = request.getParameter("agencia");
		String pValor = request.getParameter("valor");
		String data;

		Calendar cldr = Calendar.getInstance();
		data = cldr.get(Calendar.DAY_OF_MONTH) + "/" + (cldr.get(Calendar.MONTH) + 1) + "/" + cldr.get(Calendar.YEAR);

		int conta;
		int agencia;
		double valor;
		try {
			conta = Integer.parseInt(pConta);
			agencia = Integer.parseInt(pAgencia);
			valor = Double.parseDouble(pValor);
		} catch (NumberFormatException e) {
			conta = agencia = -1;
			valor = -1;
			e.printStackTrace();
		}

		RequestDispatcher view = null;

		if (Dados.verificar(pConta, pAgencia)) {
			Conta contap = Conta.getInstance();
			Conta contas = new Conta(conta, agencia, 0, null);
			contas.carregar();

			Transferencia transf = new Transferencia(contap, contas, data, valor);
			request.setAttribute("transf", transf.getTO());
			view = request.getRequestDispatcher("Transf.jsp");
		} else {
			view = request.getRequestDispatcher("Transferencia.jsp");
		}

		view.forward(request, response);
	}

}

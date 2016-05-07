package controle;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocio.Conta;
import negocio.Transferencia;
import util.Dados;

/**
 * Servlet implementation class TransfControle
 */
@WebServlet("/TransfControle")
public class TransfControle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pConta = request.getParameter("conta");
		String pAgencia = request.getParameter("agencia");
		String pValor = request.getParameter("valor");
		String acao = request.getParameter("acao");
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
		HttpSession session = request.getSession();

		if (acao.equals("continuar")) {
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
		} else if (acao.equals("confirmar")) {
			Conta contap = Conta.getInstance();
			Conta contas = new Conta(conta, agencia, 0, null);
			contas.carregar();

			Transferencia transf = new Transferencia(contap, contas, data, valor);
			transf.transferir();
			session.setAttribute("Transferencia", transf.getTO());

			view = request.getRequestDispatcher("Menu.jsp");
		} else if (acao.equals("cancelar")) {
			view = request.getRequestDispatcher("Transferencia.jsp");
		}
		view.forward(request, response);
	}

}

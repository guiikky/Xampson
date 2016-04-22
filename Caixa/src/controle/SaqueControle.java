package controle;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.Conta;
import negocio.Dispenser;
import negocio.Saque;
import to.SaqueTO;

/**
 * Servlet implementation class SaqueControle
 */
@WebServlet("/SaqueControle")
public class SaqueControle extends HttpServlet {
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

		String pValor = request.getParameter("valor");
		String acao = request.getParameter("acao");
		String data;
		Calendar cldr = Calendar.getInstance();
		data = cldr.get(Calendar.DAY_OF_MONTH) + "/" + (cldr.get(Calendar.MONTH) + 1) + "/" + cldr.get(Calendar.YEAR);
		int code = -1;

		Conta conta = Conta.getInstance();
//		conta.carregar();
		SaqueTO to = new SaqueTO();
		RequestDispatcher view = null;

		if (acao.equals("sacar")) {
			double valor;
			try {
				valor = Double.parseDouble(pValor);
			} catch (NumberFormatException e) {
				valor = -1;
				e.printStackTrace();
			}
			Saque saque = new Saque(data, conta, new Dispenser(), valor);
			code = saque.sacar();
			to = saque.getTO();
			if (code == 0) {
				view = request.getRequestDispatcher("Saque.jsp");
			} else if (code == 1) {
				view = request.getRequestDispatcher("Sacar.jsp");
			} else if (code == 2) {
				view = request.getRequestDispatcher("Sacar.jsp");
			} else {
				view = request.getRequestDispatcher("Sacar.jsp");
			}
		} else {
			acao = request.getParameter("acao");
			double valor = Double.parseDouble(acao);
			Saque saque = new Saque(data, conta, new Dispenser(), valor);
			code = saque.sacar();
			to = saque.getTO();
			if (code == 0) {
				view = request.getRequestDispatcher("Saque.jsp");
			} else if (code == 1) {
				view = request.getRequestDispatcher("Sacar.jsp");
			} else if (code == 2) {
				view = request.getRequestDispatcher("Sacar.jsp");
			} else {
				view = request.getRequestDispatcher("Sacar.jsp");
			}
		}

		request.setAttribute("saque", to);
		view.forward(request, response);
	}

}

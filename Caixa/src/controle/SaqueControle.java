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
@WebServlet("/SaqueForm")
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

		String pConta = request.getParameter("conta");
		String pValor = request.getParameter("valor");
		String data;
		Calendar cldr = Calendar.getInstance();
		data = cldr.get(Calendar.DAY_OF_MONTH) + "/" + (cldr.get(Calendar.MONTH) + 1) + "/" + cldr.get(Calendar.YEAR);
		String acao = request.getParameter("acao");

		if (acao.equals("Sacar")) {

			int conta;
			double valor;
			try {
				conta = Integer.parseInt(pConta);
				valor = Double.parseDouble(pValor);
			} catch (NumberFormatException e) {
				conta = -1;
				valor = 0.0;
				e.printStackTrace();
			}

			Conta co = new Conta(conta, 0, 0, null);
			co.carregar();
			Saque saque = new Saque(data, co, new Dispenser(), valor);
			System.out.println(saque.sacar());
			
			SaqueTO to = new SaqueTO();
			to.setData(data);
			to.setConta(co);
			to.setDispenser(new Dispenser());
			to.setValor(valor);
			
			request.setAttribute("saque", to);
			RequestDispatcher view = request.getRequestDispatcher("Saque.jsp");
			view.forward(request, response);
		}
	}

}

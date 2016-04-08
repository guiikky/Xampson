package controle;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.Extrato;
import to.ExtratoTO;

/**
 * Servlet implementation class ManterExtrato
 */
@WebServlet("/ExtratoForm")
public class ExtratoControle extends HttpServlet {
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
		String acao = request.getParameter("acao");

		if (acao.equals("Consultar")) {

			int conta;
			try {
				conta = Integer.parseInt(pConta);
			} catch (NumberFormatException e) {
				conta = -1;
				e.printStackTrace();
			}

			ArrayList<ExtratoTO> to = Extrato.carregar(conta);		
			
			request.setAttribute("extrato", to);
			RequestDispatcher view = request.getRequestDispatcher("Extrato.jsp");
			view.forward(request, response);
		}
	}

}

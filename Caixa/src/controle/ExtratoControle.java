package controle;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.Conta;
import negocio.Extrato;
import to.ExtratoTO;

/**
 * Servlet implementation class ManterExtrato
 */
@WebServlet("/ExtratoControle")
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

//		String pDias = request.getParameter("conta");
		String acao = request.getParameter("acao");

		Conta conta = Conta.getInstance();
		ArrayList<ExtratoTO> lista;

		if (acao.equals("7")) {
			lista = Extrato.carregar(conta.getConta());
			request.setAttribute("lista", lista);
		} else if (acao.equals("15")) {
			lista =  Extrato.carregar(conta.getConta());
			request.setAttribute("lista", lista);
		} else {
			lista =  Extrato.carregar(conta.getConta());
			
		}
		request.setAttribute("lista", lista);
		RequestDispatcher view = request.getRequestDispatcher("Extrat.jsp");
		view.forward(request, response);
	}

}

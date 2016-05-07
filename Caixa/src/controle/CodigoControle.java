package controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Dados;

/**
 * Servlet implementation class CodigoControle
 */
@WebServlet("/CodigoControle")
public class CodigoControle extends HttpServlet {
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
		String codigo = request.getParameter("codigo");
		String acao = request.getParameter("acao");
		int code = -1;
		
		int vet[] = Dados.random();
		request.setAttribute("vet", vet);

		RequestDispatcher view = null;

		if (acao.equals("continuar1")) {
			Dados.gerarCodigo(codigo);
			view = request.getRequestDispatcher("AcessarCodigo.jsp");
		} else if (acao.equals("continuar2")) {
			code = Dados.acessarCodigo(codigo);
			if (code == 0) {
				view = request.getRequestDispatcher("Menu.jsp");
			} else if (code == 1) {
				view = request.getRequestDispatcher("AcessarCodigo.jsp");
			} else if (code == 2) {
				view = request.getRequestDispatcher("Login.jsp");
			}
		}

		view.forward(request, response);
	}

}

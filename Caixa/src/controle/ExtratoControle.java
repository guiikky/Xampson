package controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
		PrintWriter out = response.getWriter();

		if (acao.equals("Consultar")) {

			int conta;
			try {
				conta = Integer.parseInt(pConta);
			} catch (NumberFormatException e) {
				conta = -1;
				e.printStackTrace();
			}

			ArrayList<ExtratoTO> extrato = Extrato.carregar(conta);
			
			out.println("<html><head><title>Extrato</title></head><body><h3>Extrato</h3>");
			for (ExtratoTO i : extrato) {
				out.println("data: " + i.getData() + "<br>");
				out.println("operacao: " + i.getOperacao() + "<br>");
				out.println("valor: " + i.getValor() + "<br><br>");
				out.println("</body></html>");
			}
		}
	}

}

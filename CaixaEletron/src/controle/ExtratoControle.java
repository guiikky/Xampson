package controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.Conta;
import negocio.Extrato;

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

		String pId, data, operacao, pValor, pConta, pAgencia;
		Calendar cldr = Calendar.getInstance();
		data = cldr.get(Calendar.DAY_OF_MONTH) + "/" + (cldr.get(Calendar.MONTH) + 1) + "/" + cldr.get(Calendar.YEAR);
		String acao = request.getParameter("acao");
		PrintWriter out = response.getWriter();

		if (acao.equals("Inserir")) {

			operacao = request.getParameter("operacao");
			pValor = request.getParameter("valor");
			pConta = request.getParameter("conta");
			pAgencia = request.getParameter("agencia");
			acao = request.getParameter("acao");

			int conta = Integer.parseInt(pConta);
			int agencia = Integer.parseInt(pAgencia);
			double valor = Double.parseDouble(pValor);

			Extrato extrato = new Extrato(data, operacao, valor, new Conta(conta, agencia, 0, null));
			extrato.incluir();
			
			out.println("<html><head><title>Extrato Incluido</title></head><body><h3>Extrato Incluido</h3>");
			out.println("id: " + extrato.getId() + "<br>");
			out.println("data: " + extrato.getData() + "<br>");
			out.println("operacao: " + extrato.getOperacao() + "<br>");
			out.println("valor: " + extrato.getValor() + "<br>");
			out.println("conta: " + extrato.getConta().getConta() + "<br>");
			out.println("agencia: " + extrato.getConta().getAgencia() + "<br>");
			out.println("</body></html>");
			
		} else if (acao.equals("Alterar")) {
			
			pId = request.getParameter("id");
			operacao = request.getParameter("operacao");
			pValor = request.getParameter("valor");
			pConta = request.getParameter("conta");
			pAgencia = request.getParameter("agencia");
			acao = request.getParameter("acao");
			
			int id = Integer.parseInt(pId);
			int conta = Integer.parseInt(pConta);
			int agencia = Integer.parseInt(pAgencia);
			double valor = Double.parseDouble(pValor);
			
			Extrato extrato = new Extrato(id, data, operacao, valor, new Conta(conta, agencia, 0, null));
			extrato.atualizar();
			
			out.println("<html><head><title>Extrato Alterado</title></head><body><h3>Extrato Alterado</h3>");
			out.println("id: " + extrato.getId() + "<br>");
			out.println("data: " + extrato.getData() + "<br>");
			out.println("operacao: " + extrato.getOperacao() + "<br>");
			out.println("valor: " + extrato.getValor() + "<br>");
			out.println("conta: " + extrato.getConta().getConta() + "<br>");
			out.println("agencia: " + extrato.getConta().getAgencia() + "<br>");
			out.println("</body></html>");
			
		} else if (acao.equals("Excluir")) {
			
			pId = request.getParameter("id");
			int id = Integer.parseInt(pId);
			
			Extrato extrato = new Extrato(id, null, null, 0, null);
			extrato.excluir();

			out.println("<html><head><title>Extrato Excluido</title></head><body><h3>Extrato Excluido</h3>");
			out.println("id: " + extrato.getId() + "<br>");
			out.println("</body></html>");
			
		} else if (acao.equals("Carregar")) {
			
			pId = request.getParameter("id");
			int id = Integer.parseInt(pId);
			
			Extrato extrato = new Extrato(id, null, null, 0, null);
			extrato.carregar();

			out.println("<html><head><title>Extrato</title></head><body><h3>Extrato</h3>");
			out.println("id: " + extrato.getId() + "<br>");
			out.println("data: " + extrato.getData() + "<br>");
			out.println("operacao: " + extrato.getOperacao() + "<br>");
			out.println("valor: " + extrato.getValor() + "<br>");
			out.println("conta: " + extrato.getConta().getConta() + "<br>");
			out.println("agencia: " + extrato.getConta().getAgencia() + "<br>");
			out.println("</body></html>");
		}
	}

}

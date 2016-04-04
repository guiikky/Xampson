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
import negocio.Dispenser;
import negocio.Saque;

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

		String pConta, pValor, data;
		Calendar cldr = Calendar.getInstance();
		data = cldr.get(Calendar.DAY_OF_MONTH) + "/" + (cldr.get(Calendar.MONTH) + 1) + "/" + cldr.get(Calendar.YEAR);
		String acao = request.getParameter("acao");
		PrintWriter out = response.getWriter();

		if (acao.equals("Sacar")) {
			pConta = request.getParameter("conta");
			pValor = request.getParameter("valor");

			int conta = Integer.parseInt(pConta);
			double valor = Double.parseDouble(pValor);

			Conta co = new Conta(conta, 0, 0, null);
			co.carregar();
			Saque saque = new Saque(data, co, new Dispenser(), valor);
			System.out.println(saque.sacar());
			
			out.println("<html><head><title>Saque</title></head><body><h3>Saque</h3>");
			out.println("data: " + saque.getData()+ "<br>");
			out.println("valor: " + saque.getValor()+ "<br>");
			out.println("conta: " + co.getConta() + "<br>");
			out.println("agencia: " + co.getAgencia() + "<br>");
			out.println("saldo: " + co.getSaldo() + "<br>");
			out.println("</body></html>");
		}
	}

}

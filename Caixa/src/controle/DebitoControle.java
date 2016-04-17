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
import negocio.DebitoAutomatico;

/**
 * Servlet implementation class DebitoControle
 */
@WebServlet("/DebitoControle")
public class DebitoControle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String pOperadoara = request.getParameter("operadora");
		String pConsumidor = request.getParameter("consumidor");
		String pValor = request.getParameter("valor");
		String data;
		
		Calendar cldr = Calendar.getInstance();
		data = cldr.get(Calendar.DAY_OF_MONTH) + "/" + (cldr.get(Calendar.MONTH) + 1) + "/" + cldr.get(Calendar.YEAR);
		
		int operadora;
		int consumidor;
		double valor;
		try {
			operadora = Integer.parseInt(pOperadoara);
			consumidor = Integer.parseInt(pConsumidor);
			valor = Double.parseDouble(pValor);
		} catch (NumberFormatException e) {
			operadora = consumidor = -1;
			valor = -1;
			e.printStackTrace();
		}
		Conta conta = Conta.getInstance();
		conta.carregar();
		
		DebitoAutomatico debito = new DebitoAutomatico(operadora, consumidor, data, valor, conta);
		debito.criar();
		
		request.setAttribute("debito", debito.getTO());
		RequestDispatcher view = request.getRequestDispatcher("Menu.jsp");
		view.forward(request, response);
		
		
		
	}

}

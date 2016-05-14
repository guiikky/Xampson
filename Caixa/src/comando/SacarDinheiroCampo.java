package comando;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocio.Conta;
import negocio.Dispenser;
import negocio.Saque;

public class SacarDinheiroCampo implements Comando {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pValor = request.getParameter("valor");
		String data;
		Calendar cldr = Calendar.getInstance();
		data = cldr.get(Calendar.DAY_OF_MONTH) + "/" + (cldr.get(Calendar.MONTH) + 1) + "/" + cldr.get(Calendar.YEAR);

		Conta conta = Conta.getInstance();
		RequestDispatcher view = null;
		HttpSession session = request.getSession();

		double valor;
		try {
			valor = Double.parseDouble(pValor);
		} catch (NumberFormatException e) {
			valor = -1;
			e.printStackTrace();
		}
		Saque saque = new Saque(data, conta, new Dispenser(), valor);
		int code = saque.sacar();
		if (code == 0) {
			request.setAttribute("saque", saque.getTO());
			session.setAttribute("Saque", saque.getTO());
			view = request.getRequestDispatcher("Saque.jsp");
		} else if (code == 1) {
			view = request.getRequestDispatcher("Sacar.jsp");
		} else if (code == 2) {
			view = request.getRequestDispatcher("Sacar.jsp");
		} else if (code == 3) {
			view = request.getRequestDispatcher("Sacar.jsp");
		}

		view.forward(request, response);
	}

}
